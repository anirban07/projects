/**
 * Created by Anirban on 9/7/2016.
 * This class encodes a String using the Caesar cipher.
 * The String is encoded by rotating all the characters in the String by a non-negative
 * number, the key. Only alphabetic characters are encoded.
 * For example, the String "abc" would be encoded as def if the key was 3.
 */
public class CaesarEncoder {
    /**
     * This method encodes the String passed using a Caesar cipher with the key passed.
     * @param data The String to be encoded
     * @param key The key to be used for the Caesar cipher
     * @return The encoded String.
     */
    public static String encode(String data, int key) {
        if (data == null) throw new IllegalArgumentException("String passed in cannot be null");
        if (key < 0) throw new IllegalArgumentException("Key passed in cannot be negative");
        key = key % 26;  // Key of Caesar cipher must be between 0 and 25
        StringBuilder output = new StringBuilder(data.length());
        for (int i = 0; i < data.length(); i++) {
            output.append(rotate(data.charAt(i), key));
        }
        return output.toString();
    }

    /**
     * This method shifts the character passed by the key.
     * The character is shifted only if it is an alphabetic character.
     * The characters wrap around after the z and Z.
     * @param c The character to be rotated.
     * @param key The amount the character is to be rotated by.
     * @return The rotated character.
     */
    private static char rotate(char c, int key) {
        if (c >= 'A' && c <= 'Z') {  // Upper case alphabet
            c = (char) ((c - 'A' + key) % 26 + 'A');
        } else if (c >= 'a' && c <= 'z') {  // Lower case alphabet
            c = (char) ((c - 'a' + key) % 26 + 'a');
        }
        return c;
    }
}
