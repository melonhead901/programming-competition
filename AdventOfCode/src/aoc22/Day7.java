package aoc22;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final Directory root = new Directory(null);
        Directory currentDirectory = root;
        // Burn $
        in.next();
        while (in.hasNext()) {
            String command = in.next();
            switch (command) {
                case "ls" -> processLs(currentDirectory, in);
                case "cd" -> {
                    String newDir = in.next();
                    if (newDir.equals("/")) {
                        currentDirectory = root;
                    } else if (newDir.equals("..")) {
                        currentDirectory = currentDirectory.parent;
                    } else {
                        currentDirectory = currentDirectory.navigateTo(newDir);
                    }
                    // Burn $
                    in.next();
                }
                default -> throw new IllegalStateException("unknown command");
            }
        }
        int currentUsed = root.calculateSize();
        int freeSpace = 70000000 - currentUsed;
        int neededToFree = 30000000 - freeSpace;
        System.out.println(root.findSmallestLargerThan(neededToFree));
    }

    private static void processLs(Directory currentDirectory, Scanner in) {
        while (in.hasNext()) {
            String next = in.next();
            if (Character.isDigit(next.charAt(0))) {
                currentDirectory.addFile(next, in.next());
            } else if (next.equals("dir")) {
                currentDirectory.addDirectory(in.next());
            } else {
                // We just burned a $
                break;
            }
        }
    }

    public static class Directory {
        final Map<String, Integer> files;
        final Map<String, Directory> directories;
        final Directory parent;

        public Directory(Directory parent) {
            files = new HashMap<>();
            directories = new HashMap<>();
            this.parent = parent;
        }

        public Directory navigateTo(String next) {
            if (this.directories.containsKey(next)) {
                return this.directories.get(next);
            }
            throw new IllegalStateException("No child named " + next);
        }

        public void addFile(String size, String name) {
            this.files.put(name, Integer.parseInt(size));
        }

        public void addDirectory(String next) {
            this.directories.put(next, new Directory(this));
        }

        public int calculateSize() {
            int myFilesSize = files.values().stream().reduce(0, Integer::sum);
            int sum = 0;
            for (Directory d : this.directories.values()) {
                sum += d.calculateSize();
            }
            return myFilesSize + sum;
        }

        public int sumIfSizeLessThan(int max) {
            int sum = this.calculateSize();
            if (sum >= max) {
                sum = 0;
            }
            for (Directory d : this.directories.values()) {
                sum += d.sumIfSizeLessThan(max);
            }
            return sum;
        }

        public int findSmallestLargerThan(int num) {
            int mySize = calculateSize();
            int min;
            if (mySize > num) {
                min = mySize;
            } else {
                min = Integer.MAX_VALUE;
            }
            for (Directory d : this.directories.values()) {
                min = Math.min(min, d.findSmallestLargerThan(num));
            }
            return min;
        }
    }
}
