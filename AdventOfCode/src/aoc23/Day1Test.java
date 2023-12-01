package aoc23;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1Test {
    @Test
    public void wordProcessTest() {
        String input = "two1nine";
        String expected = "219";
        assertThat(Day1.wordProcess(input)).isEqualTo(expected);
    }
    @Test
    public void wordProcessTest2() {
        String input = "eightwothree";
        String expected = "823";
        assertThat(Day1.wordProcess(input)).isEqualTo(expected);
    }

    @Test
    public void lineProcessTest() {
        String input = "two1nine";
        assertThat(Day1.processLine(input)).isEqualTo(29);
    }

    @Test
    public void lineProcessTest2() {
        String input = "eightwothree";
        assertThat(Day1.processLine(input)).isEqualTo(83);
    }
}
