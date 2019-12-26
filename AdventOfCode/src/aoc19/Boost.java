package aoc19;

public class Boost {
    public static void main(String[] args) throws InterruptedException {
        String input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
        IntComputer ic = new IntComputer(input);
        ic.run();
        while (ic.hasOutput()) {
            System.out.println(ic.getOutput());
        }
    }
}
