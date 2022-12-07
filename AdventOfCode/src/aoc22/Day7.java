package aoc22;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Directory root = new Directory(null);
        Directory currentDirectory = root;
        String next = in.next();
        while (in.hasNext()) {
            String command = in.next();
            switch (command) {
                case "ls":
                    processLs(currentDirectory, in);
                    break;
                case "cd":
                    String newDir = in.next();
                    if (newDir.equals("/")) {
                        currentDirectory = root;
                    } else if (newDir.equals("..")) {
                        currentDirectory = currentDirectory.parent;
                    } else {
                        currentDirectory = currentDirectory.navigateTo(newDir);
                    }
                    in.next();
                    break;
                default:
                    throw new IllegalStateException("unknown command");
            }
        }
        System.out.println(root.sumIfSizeLessThan(100000));
    }

    private static void processLs(Directory currentDirectory, Scanner in) {
        while (in.hasNext()) {
            String next = in.next();
            if (Character.isDigit(next.charAt(0))) {
                currentDirectory.addFile(next, in.next());
            } else if (next.equals("dir")) {
                currentDirectory.addDirectory(in.next());
            } else {
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
            int sum = 0;
            if (this.calculateSize() < max) {
                sum += this.calculateSize();
            }
            for (Directory d : this.directories.values()) {
                sum += d.sumIfSizeLessThan(max);
            }
            return sum;
        }
    }
}
