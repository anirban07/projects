/**
 * Created by Anirban on 9/7/2016.
 * This class encodes a String using the Caesar cipher.
 * The String is encoded by rotating all the characters in the String by an integer,
 * the key. Only alphabetic characters are encoded.
 * For example, the String "abc" would be encoded as def if the key was 3.
 */
public class CaesarEncoder {
    /**
     * This method encodes the String passed using a Caesar cipher with the key passed.
     * @param data The String to be encoded
     * @param key The key to be used for the Caesar cipher
     * @return The encoded String. null if data passed is null.
     */
    public static String encode(String data, int key) {
        if (data == null) return null;
        key = key % 26;  // Key of Caesar cipher must be between -25 and 25
        if (key < 0) key += 26;  // make the key positive
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
     * @param character The character to be rotated.
     * @param key The amount the character is to be rotated by.
     *            Must be non negative.
     * @return The rotated character.
     */
    private static char rotate(char character, int key) {
        if (character >= 'A' && character <= 'Z') {  // Upper case alphabet
            character = (char) ((character - 'A' + key) % 26 + 'A');
        } else if (character >= 'a' && character <= 'z') {  // Lower case alphabet
            character = (char) ((character - 'a' + key) % 26 + 'a');
        }
        return character;
    }
}
