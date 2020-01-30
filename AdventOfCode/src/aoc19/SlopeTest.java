package aoc19;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for Slope
 */
public class SlopeTest {

    @Test
    public void test() {
        Slope a = new Slope(4, 2);
        Slope b = new Slope(8, 4);
        assertThat(a).isEqualTo(b);
    }
}
