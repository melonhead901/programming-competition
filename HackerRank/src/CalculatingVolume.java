import java.io.IOException;
import java.security.Permission;
import java.util.Scanner;

/**
 * Created by kdonohue on 1/2/17.
 */
public class CalculatingVolume {
}
//Write your code here
class Calculate {
    private final Scanner in;
    final Output output;
    public Calculate() {
        in = new Scanner(System.in);
        output = new Output();
    }

    int get_int_val() throws IOException {
        int result = in.nextInt();
        if (result <= 0) {
            throw new NumberFormatException("All the values must be positive");
        }
        return result;
    }

    double get_double_val() {
        double result = in.nextDouble();
        if (result <= 0) {
            throw new NumberFormatException("All the values must be positive");
        }
        return result;
    }

    static VolumeCalculator do_calc() {
        return new VolumeCalculator();
    }

}

class Output {

    void display(double volume) {
        System.out.println(String.format("%.3f", volume));
    }

}

class VolumeCalculator {
    public double get_volume(int side) {
        return side * side * side;
    }
    public double get_volume(int l, int w, int h) {
        return l * w * h;
    }
    public double get_volume(double r) {
        // V = 4/3pi*r^3 / 2.
        return Math.PI * 2/3.0 * Math.pow(r,3);
    }
    public double get_volume(double r, double h) {
        // V = pi*r^2 * h
        return Math.PI * Math.pow(r,2) * h;
    }
}

class Solution {

    public static void main(String[] args) {
        DoNotTerminate.forbidExit();
        try {
            Calculate cal = new Calculate();
            int T = cal.get_int_val();
            while (T--> 0) {
                double volume = 0.0;
                int ch = cal.get_int_val();
                if (ch == 1) {
                    int a = cal.get_int_val();
                    volume = Calculate.do_calc().get_volume(a);
                } else if (ch == 2) {
                    int l = cal.get_int_val();
                    int b = cal.get_int_val();
                    int h = cal.get_int_val();
                    volume = Calculate.do_calc().get_volume(l, b, h);

                } else if (ch == 3) {
                    double r = cal.get_double_val();
                    volume = Calculate.do_calc().get_volume(r);

                } else if (ch == 4) {
                    double r = cal.get_double_val();
                    double h = cal.get_double_val();
                    volume = Calculate.do_calc().get_volume(r, h);

                }
                cal.output.display(volume);
            }

        } catch (NumberFormatException e) {
            System.out.print(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DoNotTerminate.ExitTrappedException e) {
            System.out.println("Unsuccessful Termination!!");
        }


    } //end of main
} //end of Solution

/**
 *This class prevents the user form using System.exit(0)
 * from terminating the program abnormally.
 *
 */
class DoNotTerminate {

    public static class ExitTrappedException extends SecurityException {}

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}