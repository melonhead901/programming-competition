package aoc20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Day7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Bag, Map<Bag, Integer>> graph = buildGraph(in);
        Set<Bag> targetBags = graphReverseSearch(graph);
        int bagCount = getBagCount(graph, new Bag("shiny", "gold"));
        System.out.println(bagCount - 1);
    }

    static Map<Bag, Integer> lookupTable = new HashMap<>();
    private static int getBagCount(Map<Bag, Map<Bag, Integer>> graph, Bag bag) {
        if (lookupTable.containsKey(bag)) {
            return lookupTable.get(bag);
        }
        // Start with 1 for self
        int bagCount = 1;
        for (Bag child : graph.get(bag).keySet()) {
            bagCount += (graph.get(bag).get(child) * getBagCount(graph, child));
        }
        System.err.printf("%s:%s%n",bag, bagCount );
        lookupTable.put(bag, bagCount);
        return bagCount;
    }

    private static Set<Bag> graphReverseSearch(Map<Bag, Map<Bag, Integer>> graph) {
        Set<Bag> seenBags = new HashSet<>();
        Queue<Bag> queue = new LinkedList<>();
        queue.add(new Bag("shiny", "gold"));
        while (!queue.isEmpty()) {
           Bag b = queue.poll();
           for (Bag bag : graph.keySet()) {
              if (graph.get(bag).containsKey(b)) {
                  if (!seenBags.contains(bag)) {
                      seenBags.add(bag);
                      queue.add(bag);
                  }
              }
           }
        }
        return seenBags;
    }

    private static Map<Bag, Map<Bag, Integer>> buildGraph(Scanner in) {
        Map<Bag, Map<Bag, Integer>> graph = new HashMap<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            // light red bags contain
            Scanner lineScanner = new Scanner(line);
            Bag b = new Bag(lineScanner.next(), lineScanner.next());
            // bags, contain
            lineScanner.next();
            lineScanner.next();
            Map<Bag, Integer> map = new HashMap<>();
            // light red bags contain 1 bright white bag, 2 muted yellow bags.
            while (lineScanner.hasNext()) {
                if (lineScanner.hasNextInt()) {
                    int num = lineScanner.nextInt();
                    Bag containedBag = new Bag(lineScanner.next(), lineScanner.next());
                    lineScanner.next();
                    map.put(containedBag, num);
                } else {
                    break;
                }
            }
            graph.put(b, map);
            System.out.println(map);
        }
        return graph;
    }
}

class Bag {
    final String desc;
    final String color;

    Bag(String desc, String color) {
        this.desc = desc;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bag bag = (Bag) o;

        if (desc != null ? !desc.equals(bag.desc) : bag.desc != null) return false;
        return color != null ? color.equals(bag.color) : bag.color == null;
    }

    @Override
    public int hashCode() {
        int result = desc != null ? desc.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bag{" + "desc='" + desc + '\'' + ", color='" + color + '\'' + '}';
    }
}
