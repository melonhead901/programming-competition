package aoc21;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Route> routes = getRoutes(in);
        Map<String, List<String>> processedRoutes = processRoutes(routes);
        List<Path> paths = findAllValidPaths(processedRoutes);
        System.out.println(paths);
        System.out.println(paths.size());
    }

    private static List<Path> findAllValidPaths(Map<String, List<String>> processedRoutes) {
        Path path = new Path();
        path.takeRoute("start");
        Queue<Path> paths = new LinkedList<>();
        paths.add(path);
        List<Path> successPaths = new ArrayList<>();
        while (!paths.isEmpty()) {
            Path p = paths.remove();
            if (p.isFinished()) {
                successPaths.add(p);
            }
            List<Path> nextSteps = p.validNextSteps(processedRoutes);
            paths.addAll(nextSteps);
        }
        return successPaths;
    }

    private static Map<String, List<String>> processRoutes(List<Route> routes) {
        Map<String, List<String>> processedRoutes = new HashMap<>();
        for (Route r : routes) {
            List<String> dests;
            if (processedRoutes.containsKey(r.start)) {
                dests = processedRoutes.get(r.start);
            } else {
                dests = new ArrayList<>();
                processedRoutes.put(r.start, dests);
            }
            dests.add(r.end);
        }
        for (Route r : routes) {
            List<String> dests;
            if (processedRoutes.containsKey(r.end)) {
                dests = processedRoutes.get(r.end);
            } else {
                dests = new ArrayList<>();
                processedRoutes.put(r.end, dests);
            }
            dests.add(r.start);
        }
        return processedRoutes;
    }

    private static List<Route> getRoutes(Scanner in) {
        List<Route> routes = new ArrayList<>();
        while (in.hasNextLine()) {
            routes.add(new Route(in.nextLine()));
        }
        return routes;
    }

    private static class Path {
        List<String> roomsVisited;
        Set<String> smallCaves;
        boolean usedExtraVisit = false;

        public Path() {
            this.roomsVisited = new ArrayList<>();
            this.smallCaves = new HashSet<>();
            this.usedExtraVisit = false;
        }

        public Path(List<String> roomsVisited, Set<String> smallCaves, boolean usedExtraVisit) {
            this.roomsVisited = roomsVisited;
            this.smallCaves = smallCaves;
            this.usedExtraVisit = usedExtraVisit;
        }

        private Path copyPath() {
            return new Path(new ArrayList<>(this.roomsVisited), new HashSet<>(this.smallCaves), this.usedExtraVisit);
        }

        public boolean canTakeRoute(String next) {
            return !smallCaves.contains(next) || ((!next.equals("end") & !next.equals("start")) && !usedExtraVisit);
        }

        public Path takeRoute(String next) {
            if (smallCaves.contains(next)) {
                usedExtraVisit = true;
            }
            if (Character.isLowerCase(next.charAt(0))) {
                smallCaves.add(next);
            }
            roomsVisited.add(next);
            return this;
        }

        public boolean isFinished() {
            return roomsVisited.get(roomsVisited.size() - 1).equals("end");
        }

        public List<Path> validNextSteps(Map<String, List<String>> routes) {
            String last = Iterables.getLast(roomsVisited);
            List<String> nexts = routes.getOrDefault(last, Collections.emptyList());
            nexts = nexts.stream().filter(this::canTakeRoute).collect(Collectors.toList());
            return nexts.stream().map(n -> this.copyPath().takeRoute(n)).collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return Joiner.on(",").join(roomsVisited);
        }
    }

    private static class Route {
        final String start;
        final String end;

        public Route(String nextLine) {
            String[] parts = nextLine.split("-");
            start = parts[0];
            end = parts[1];
        }
    }
}
