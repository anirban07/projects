import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Anirban on 9/7/2016.
 */
public class CaesarDecoder {

    private Set<String> dictionary;
    private String[] letters;

    public CaesarDecoder() throws IOException {
        BufferedReader dictionaryInput = new BufferedReader(new FileReader(CaesarDecoder.class.getClassLoader()
                                                                .getResource("words2.txt").getPath()
                                                                .replaceAll("%20", " ")));
        BufferedReader lettersInput = new BufferedReader(new FileReader(CaesarDecoder.class.getClassLoader()
                                                            .getResource("letters.txt").getPath()
                                                            .replaceAll("%20", " ")));
        dictionary = new HashSet<String>();
        String dictionaryLine = null;
        while ((dictionaryLine = dictionaryInput.readLine()) != null) {
            dictionary.add(dictionaryLine);
        }
        letters = new String[26];
        for (int i = 0; i < 26; i++) {
            letters[i] = lettersInput.readLine();
        }
    }

    public String decode(String data, int key) {
        if (key < 0) throw new IllegalArgumentException("Key passed in cannot be negative");
        key %= 26;
        return CaesarEncoder.encode(data, 26 - key);
    }

    public String decode(String data) {
        List<CharacterFrequency> frequency = getFrequency(data);
        System.out.println("frequency = " + frequency);
        return null;
    }

    private List<CharacterFrequency> getFrequency(String data) {
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
