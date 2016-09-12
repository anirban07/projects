import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anirban on 9/7/2016.
 */
public class CaesarEncoderTest {
    @Test
    public void testNullDataInput() {
        assertNull(CaesarEncoder.encode(null, 1));
    }

    @Test
    public void testNegativeKeyInput() {
        assertEquals("zab", CaesarEncoder.encode("abc", -1));
    }

    @Test
    public void testZeroKey() {
        assertEquals("abc", CaesarEncoder.encode("abc", 0));
    }

    @Test
    public void testEmptyData() {
        CaesarEncoder.encode("", 1);
    }

    @Test
    public void testDataWithSpace() {
        assertEquals("fgh ijk", CaesarEncoder.encode("abc def", 5));
    }

    @Test
    public void testWithSpecialCharacters() {
        assertEquals(" f,g>h@ i'j ", CaesarEncoder.encode(" a,b>c@ d'e ", 5));
    }

    @Test
    public void testMixedCase() {
        assertEquals("dEf gHI", CaesarEncoder.encode("aBc dEF", 3));
    }

    @Test
    public void testLowerCaseWrap() {
        assertEquals("zab cde", CaesarEncoder.encode("uvw xyz", 5));
    }

    @Test
    public void testUpperCaseWrap() {
        assertEquals("ZAB CDE", CaesarEncoder.encode("UVW XYZ", 5));
    }

    @Test
    public void testKeyWrap() {
        assertEquals("zab cde", CaesarEncoder.encode("uvw xyz", 31));
    }

    @Test
    public void testLargeKey() {
        assertEquals("rst uvw", CaesarEncoder.encode("uvw xyz", Integer.MAX_VALUE));
    }

    @Test
    public void testNegativeKeyWrap() {
        assertEquals("pqr stu", CaesarEncoder.encode("uvw xyz", -31));
    }

    @Test
    public void testLargeNegativeKey() {
        assertEquals("wxy zab", CaesarEncoder.encode("uvw xyz", Integer.MIN_VALUE));
    }

    @Test
    public void testConsecutiveCipher() {
        assertEquals("abc xy", CaesarEncoder.encode(CaesarEncoder.encode("abc xy", 5), 21));
    }
}