
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Anirban on 9/7/2016.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        CaesarDecoder decoder = new CaesarDecoder();
        String filename = "message.txt";
        URL url = Resources.getResource(filename);
        String file = Resources.toString(url, Charsets.UTF_8);
        int key = 25;
        String encoded = CaesarEncoder.encode(file, key);
        System.out.println("Encoded:");
        System.out.println(encoded);
        System.out.println();
        List<CaesarDecoder.Prediction> decoded = decoder.decode(encoded);
        System.out.println("Decoded:");
        System.out.println(decoded.get(0).getPrediction());
        System.out.println();
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Confidence: " + df.format(decoded.get(0).getAccuracy()) + "%");
    }
}
