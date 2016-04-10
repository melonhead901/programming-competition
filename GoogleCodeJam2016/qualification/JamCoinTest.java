import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class JamCoinTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testIsJamCoin() {
        assertFalse(JamCoin.isJamCoin("101"));
        assertTrue(JamCoin.isJamCoin("1001"));
        assertFalse(JamCoin.isJamCoin("110111"));
        assertTrue(JamCoin.isJamCoin("10101"));
        assertTrue(JamCoin.isJamCoin("110011"));
    }

}
