import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Anirban on 9/8/2016.
 */
public class CaesarDecoderTest {

    private CaesarDecoder decoder;
    private final String MESSAGE = "This is a sample message...";
    private final String ENCODED_23 = CaesarEncoder.encode(MESSAGE, 23);

    @Before
    public void setup() throws IOException {
        if (decoder == null) {
            decoder = new CaesarDecoder();
        }
    }

    @Test
    public void testNegativeKey() {
        assertEquals(MESSAGE, decoder.decode(ENCODED_23, -3));
    }

    @Test
    public void testKeyWrap() {
        assertEquals(MESSAGE, decoder.decode(ENCODED_23, 49));
    }

    @Test
    public void testLargeKey() {
        assertEquals(MESSAGE, decoder.decode(ENCODED_23, Integer.MAX_VALUE));
    }

    @Test
    public void testLargeNegativeKey() {
        // Integer.MIN_VALUE % 26 = 2
        assertEquals(MESSAGE, decoder.decode(CaesarEncoder.encode(MESSAGE, 2), Integer.MIN_VALUE));
    }

    @Test
    public void testNullDataWithKey() {
        assertNull(decoder.decode(null, 5));
    }

    @Test
    public void testEmptyDataWithKey() {
        assertEquals("", decoder.decode("", 5));
    }

    @Test
    public void testNullDataWithoutKey() {
        assertNull(decoder.decode(null));
    }

    @Test
    public void testEmptyDataWithoutKey() {
        CaesarDecoder.Prediction emptyPrediction = new CaesarDecoder.Prediction("", 100.0);
        assertEquals(emptyPrediction, decoder.decode("").get(0));
    }

    @Test
    public void testValidDataWithoutKey() {
        assertEquals(MESSAGE, decoder.decode(ENCODED_23).get(0).getPrediction());
    }
}