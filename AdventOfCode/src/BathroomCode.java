import java.util.Scanner;

/**
 * Created by kdonohue on 12/8/16.
 */
public class BathroomCode {
    private static final Digit SEVEN = new Digit() {
        @Override
        public Digit moveLeft() {
            return SEVEN;
        }

        @Override
        public Digit moveRight() {
            return EIGHT;
        }

        @Override
        public Digit moveUp() {
            return FOUR;
        }

        @Override
        public Digit moveDown() {
            return SEVEN;
        }

        @Override
        public String getRepresentation() {
            return "7";
        }
    };
    private static final Digit EIGHT =  new Digit() {
        @Override
        public Digit moveLeft() {
            return SEVEN;
        }

        @Override
        public Digit moveRight() {
            return NINE;
        }

        @Override
        public Digit moveUp() {
            return FIVE;
        }

        @Override
        public Digit moveDown() {
            return EIGHT;
        }

        @Override
        public String getRepresentation() {
            return "8";
        }
    };
    private static final Digit NINE =  new Digit() {
        @Override
        public Digit moveLeft() {
            return EIGHT;
        }

        @Override
        public Digit moveRight() {
            return NINE;
        }

        @Override
        public Digit moveUp() {
            return SIX;
        }

        @Override
        public Digit moveDown() {
            return NINE;
        }

        @Override
        public String getRepresentation() {
            return "9";
        }
    };
    private static final Digit SIX = new Digit() {
        @Override
        public Digit moveLeft() {
            return FIVE;
        }

        @Override
        public Digit moveRight() {
            return this;
        }

        @Override
        public Digit moveUp() {
            return THREE;
        }

        @Override
        public Digit moveDown() {
            return NINE;
        }

        @Override
        public String getRepresentation() {
            return "6";
        }
    };
    private static final Digit THREE = new Digit() {
        @Override
        public Digit moveLeft() {
            return TWO;
        }

        @Override
        public Digit moveRight() {
            return this;
        }

        @Override
        public Digit moveUp() {
            return this;
        }

        @Override
        public Digit moveDown() {
            return SIX;
        }

        @Override
        public String getRepresentation() {
            return "3";
        }
    };
    private static final Digit TWO = new Digit() {
        @Override
        public Digit moveLeft() {
            return ONE;
        }

        @Override
        public Digit moveRight() {
            return THREE;
        }

        @Override
        public Digit moveUp() {
            return this;
        }

        @Override
        public Digit moveDown() {
            return FIVE;
        }

        @Override
        public String getRepresentation() {
            return "2";
        }
    };
    private static final Digit ONE = new Digit() {
        @Override
        public Digit moveLeft() {
            return this;
        }

        @Override
        public Digit moveRight() {
            return TWO;
        }

        @Override
        public Digit moveUp() {
            return this;
        }

        @Override
        public Digit moveDown() {
            return FOUR;
        }

        @Override
        public String getRepresentation() {
            return "1";
        }
    };
    private static final Digit FOUR = new Digit() {
        @Override
        public Digit moveLeft() {
            return this;
        }

        @Override
        public Digit moveRight() {
            return FIVE;
        }

        @Override
        public Digit moveUp() {
            return ONE;
        }

        @Override
        public Digit moveDown() {
            return SEVEN;
        }

        @Override
        public String getRepresentation() {
            return "4";
        }
    };
    private static final Digit FIVE = new Digit() {
        @Override
        public Digit moveLeft() {
            return FOUR;
        }

        @Override
        public Digit moveRight() {
            return SIX;
        }

        @Override
        public Digit moveUp() {
            return TWO;
        }

        @Override
        public Digit moveDown() {
            return EIGHT;
        }

        @Override
        public String getRepresentation() {
            return "5";
        }
    };

    public BathroomCode(Digit currentDigit) {
        this.currentDigit = currentDigit;
    }

    public static BathroomCode CreateBathroomCode() {
        return new BathroomCode(FIVE);
    }

    private Digit currentDigit;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BathroomCode bc = CreateBathroomCode();
        while (in.hasNext()) {
            System.out.print(bc.processNext(in.nextLine()));
        }
    }

    private String processNext(String line) {
        for (Character c : line.toCharArray()) {
            switch (c) {
                case 'U':
                    currentDigit = currentDigit.moveUp();
                    break;
                case 'L':
                    currentDigit = currentDigit.moveLeft();
                    break;
                case 'R':
                    currentDigit = currentDigit.moveRight();
                    break;
                case 'D':
                    currentDigit = currentDigit.moveDown();
                    break;
            }
        }
        return currentDigit.getRepresentation();
    }

}

abstract class Digit {
    public abstract Digit moveLeft();

    public abstract Digit moveRight();

    public abstract Digit moveUp();

    public abstract Digit moveDown();

    public abstract String getRepresentation();
}

