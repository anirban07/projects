import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Anirban on 9/7/2016.
 * This class decodes a message encoded using the Caesar cipher.
 */
public class CaesarDecoder {
    // A set of words in the English language.
    private Set<String> dictionary;
    // An array of English alphabets in decreasing order of their
    // frequency of occurance in common English.
    private char[] letters;

    /**
     * Constructs the Caesar cipher decoder.
     * @throws IOException
     */
    public CaesarDecoder() throws IOException {
        URL wordsUrl = Resources.getResource("words2.txt");
        URL lettersUrl = Resources.getResource("letters.txt");

        dictionary = new HashSet<String>(Resources.readLines(wordsUrl, Charsets.UTF_8));
        List<String> lettersList = new ArrayList<String>(Resources.readLines(lettersUrl, Charsets.UTF_8));
        letters = new char[26];
        for (int i = 0; i < 26; i++) {
            letters[i] = lettersList.get(i).charAt(0);
        }
    }

    /**
     * Decodes the encoded message passed in using the key provided.
     * @param data The encoded message to decode.
     * @param key The key to be used to decode the encoded message.
     * @throws IllegalArgumentException If key passed in is negative or
     * data passed is null.
     * @return The decoded mesage obtained.
     */
    public String decode(String data, int key) {
        key %= 26; // -25 <= key <= 25
        // Decoding a Caesar cipher encoded message is identical to encoding the
        // same message by using the key, 26 - key used to encode it.
        return CaesarEncoder.encode(data, 26 - key);
    }

    /**
     * Tries to decode the encoded message passed in.
     * It uses frequency analysis to makes predictions for the key of
     * the encoded message passed in.
     * @param data The encoded mesage whose key is to be prediced.
     * @return A List of Prediction objects sorted in decreasing order
     * of confidence. null if data passed is null.
     */
    public List<Prediction> decode(String data) {
        if (data == null) return null;
        if (data.isEmpty()) {
            List<Prediction> emptyList = new ArrayList<Prediction>();
            emptyList.add(new Prediction("", 100.0));
            return emptyList;
        }
        List<CharacterFrequency> frequencyList = getFrequency(data);
        Map<String, Double> predictionConfidenceMap = new HashMap<String, Double>();
        List<Prediction> predictionList = new ArrayList<Prediction>();
        for (int i = 0; i < 10; i++) {
            int key = (frequencyList.get(0).character - letters[i] + 26) % 26;
            String prediction = decode(data, key);
            double confidence = getConfidence(prediction);
            predictionConfidenceMap.put(prediction, confidence);
        }
        for (String prediction : predictionConfidenceMap.keySet()) {
            predictionList.add(new Prediction(prediction, predictionConfidenceMap.get(prediction)));
        }
        Collections.sort(predictionList, Collections.<Prediction>reverseOrder());
        return predictionList;
    }

    /**
     * Returns the confidence in a decoded message.
     * The higher the fraction of the number of english words to the total
     * number of words, the higher the confidence.
     * @param data The decoded message whose confidence is to be computed.
     * @pre data != null
     * @return The confidence in the decoded message passed.
     */
    private double getConfidence(String data) {
        assert data != null;
        if (data.isEmpty()) {
            return 100.0;
        }
        Scanner words = new Scanner(data.toLowerCase());
        int total = 0;
        int correct = 0;
        while (words.hasNext()) {
            total++;
            String word = words.next().replaceAll("[^(a-z)]+$", "");
            if (dictionary.contains(word)) correct++;
        }
        return (100.0 * correct) / total;
    }

    /**
     * Computes the frequencies of the alphabetic characters in the given String
     * and returns a List of CharacterFrequency objects.
     * @param data The String whose character frequency is to be computed.
     * @pre data != null
     * @return A list of CharacterFrequency objects, sorted in decreasing order of
     * character frequency.
     */
    private List<CharacterFrequency> getFrequency(String data) {
        assert data != null;
        Map<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
        String lowerCaseData = data.toLowerCase();
        for (int i = 0; i < lowerCaseData.length(); i++) {
            char curr = lowerCaseData.charAt(i);
            if (curr >= 'a' && curr <= 'z') {
                if (!frequencyMap.containsKey(curr)) {
                    frequencyMap.put(curr, 0);
                }
                frequencyMap.put(curr, frequencyMap.get(curr) + 1);
            }
        }
        List<CharacterFrequency> frequencyList = new ArrayList<CharacterFrequency>();
        for (Character c : frequencyMap.keySet()) {
            frequencyList.add(new CharacterFrequency(c, frequencyMap.get(c)));
        }
        Collections.sort(frequencyList, Collections.<CharacterFrequency>reverseOrder());
        return frequencyList;
    }

    public static class Prediction implements Comparable<Prediction> {
        private String prediction;
        private double confidence;

        public Prediction(String p, double a) {
            this.prediction = p;
            this.confidence = a;
        }

        public String getPrediction() {
            return prediction;
        }

        public void setPrediction(String prediction) {
            this.prediction = prediction;
        }

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Prediction that = (Prediction) o;

            if (prediction != that.prediction) return false;
            if (Double.compare(that.confidence, confidence) != 0) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return  prediction.hashCode() ^ ((Double) confidence).hashCode();
        }

        public int compareTo(Prediction o) {
            return Double.compare(this.confidence, o.confidence);
        }

        public String toString() {
            return "(" + prediction + ", " + confidence + ")";
        }
    }

    private class CharacterFrequency implements Comparable<CharacterFrequency> {
        private char character;
        private int frequency;

        public CharacterFrequency(char c, int f) {
            this.character = c;
            this.frequency = f;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;

            CharacterFrequency that = (CharacterFrequency) o;

            if (getCharacter() != that.getCharacter()) return false;
            return getFrequency() == that.getFrequency();
        }

        @Override
        public int hashCode() {
            return ((Character) this.character).hashCode() ^ ((Integer) frequency).hashCode();
        }

        public char getCharacter() {
            return character;
        }

        public void setCharacter(char character) {
            this.character = character;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public int compareTo(CharacterFrequency other) {
            return this.frequency - other.frequency;
        }

        public String toString() {
            return "(" + character + ", " + frequency + ")";
        }
    }
}
