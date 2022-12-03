package aoc22;

import java.util.Scanner;

public class Day2 {

    public static final String ROCK = "rock";
    public static final String PAPER = "paper";
    public static final String SCISSORS = "scissors";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalScore = 0;
        while (in.hasNext()) {
            String text = in.nextLine();
            Line line = new Line(text);
            int score = line.scoreLine();
            totalScore += score;
        }
        System.out.println(totalScore);
    }

    enum Move {
        X(ROCK),
        Y(PAPER),
        Z(SCISSORS),

        A(ROCK),
        B(PAPER),
        C(SCISSORS);

        private final String item;

        Move(String item) {
            this.item = item;
        }

        public boolean beatsOther(Move them) {
            return this.item.equals(ROCK) && them.item.equals(SCISSORS)
                    || (this.item.equals(PAPER) && them.item.equals(ROCK))
                    || (this.item.equals(SCISSORS) && them.item.equals(PAPER));
        }

        public int movePoints() {
            int val;
            switch (this.item) {
                case ROCK -> val = 1;
                case PAPER -> val = 2;
                case SCISSORS -> val = 3;
                default -> throw new IllegalStateException();
            }
            return val;
        }
    }

    enum Result {
        X(0),
        Y(3),
        Z(6);

        private final int points;

        Result(int pointsOfResult) {
            this.points = pointsOfResult;
        }
    }

    static class Line {
        final Move theirMove;
        final Move myMove;

        public Line(String str) {
            theirMove = Move.valueOf(str.charAt(0) + "");
            myMove = determineDesiredResult(theirMove, Result.valueOf(str.charAt(2) + ""));
        }

        static Move determineDesiredResult(Move theirMove, Result result) {
            for (Move possibleMove : Move.values()) {
                int score = getResultScore(theirMove, possibleMove);
                if (result.points == score) {
                    return possibleMove;
                }
            }
            throw new IllegalStateException("cannot get desired result " + result);
        }

        public int scoreLine() {
            int moveScore = myMove.movePoints();

            int resultScore = getResultScore(theirMove, myMove);

            int total = moveScore + resultScore;
            System.out.printf("%s move score, %s result score = %s total\n", moveScore, resultScore, total);
            return total;
        }

        private static int getResultScore(Move theirMove, Move myMove) {
            int resultScore;
            if (myMove.beatsOther(theirMove)) {
                resultScore = 6;
            } else if (theirMove.beatsOther(myMove)) {
                resultScore = 0;
            } else {
                resultScore = 3;
            }
            return resultScore;
        }
    }
}
