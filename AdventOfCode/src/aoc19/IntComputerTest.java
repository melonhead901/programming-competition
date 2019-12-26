package aoc19;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Public test for {@link IntComputer}.
 */
public class IntComputerTest {
    @Test
    public void testQuine() throws InterruptedException {
        String input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
        IntComputer ic = new IntComputer(input);
        ic.run();
        String[] inputSplit = input.split(",");
        List<Long> expected = new ArrayList<>();
        for (String s : inputSplit) {
            expected.add(Long.parseLong(s));
        }
        List<Long> actual = new ArrayList<>();
        while (ic.hasOutput()) {
            actual.add(ic.getOutput());
        }
        assertThat(expected).containsExactlyElementsOf(actual);
    }

    @Test
    public void test16DigitNumber() throws InterruptedException {
        String input = "1102,34915192,34915192,7,4,7,99,0";
        IntComputer ic = new IntComputer(input);
        ic.run();
        long result = ic.getOutput();
        assertThat(String.valueOf(result)).hasSize(16);
    }

    @Test
    public void testLargeNumber() throws InterruptedException {
        String input = "104,1125899906842624,99";
        long expected = 1125899906842624L;
        IntComputer ic = new IntComputer(input);
        ic.run();
        long result = ic.getOutput();
        assertThat(result).isEqualTo(expected);
    }
}
