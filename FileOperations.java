package bankeralgo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileOperations {
    private File file;
    private Trie trie;
    private static final Map<String, Character> BAUDOT_CODE_MAP = new HashMap<>();

    static {
        BAUDOT_CODE_MAP.put("+----", 'A');
        BAUDOT_CODE_MAP.put("--++-", 'B');
        BAUDOT_CODE_MAP.put("+-++-", 'C');
        BAUDOT_CODE_MAP.put("++++-", 'D');
        BAUDOT_CODE_MAP.put("-+---", 'E');
        BAUDOT_CODE_MAP.put("-+++-", 'F');
        BAUDOT_CODE_MAP.put("-+-+-", 'G');
        BAUDOT_CODE_MAP.put("++-+-", 'H');
        BAUDOT_CODE_MAP.put("-++--", 'I');
        BAUDOT_CODE_MAP.put("+--+-", 'J');
        BAUDOT_CODE_MAP.put("+--++", 'K');
        BAUDOT_CODE_MAP.put("++-++", 'L');
        BAUDOT_CODE_MAP.put("-+-++", 'M');
        BAUDOT_CODE_MAP.put("-++++", 'N');
        BAUDOT_CODE_MAP.put("+++--", 'O');
        BAUDOT_CODE_MAP.put("+++++", 'P');
        BAUDOT_CODE_MAP.put("+-+++", 'Q');
        BAUDOT_CODE_MAP.put("--+++", 'R');
        BAUDOT_CODE_MAP.put("--+-+", 'S');
        BAUDOT_CODE_MAP.put("+-+-+", 'T');
        BAUDOT_CODE_MAP.put("+-+--", 'U');
        BAUDOT_CODE_MAP.put("+++-+", 'V');
        BAUDOT_CODE_MAP.put("-++-+", 'W');
        BAUDOT_CODE_MAP.put("-+--+", 'X');
        BAUDOT_CODE_MAP.put("--+--", 'Y');
        BAUDOT_CODE_MAP.put("++--+", 'Z');
    }

    public FileOperations() {
        this("C:\\Users\\91912\\Desktop\\FILE.txt"); 
    }

    public FileOperations(String filePath) {
        file = new File(filePath);
        trie = new Trie();
        loadWords(); 
    }

    private void loadWords() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String word;
            while ((word = br.readLine()) != null) {
                trie.insert(word.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveWords() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            saveWords(trie.getRoot(), "", bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveWords(TrieNode node, String word, BufferedWriter bw) throws IOException {
        if (node.isEnd) {
            bw.write(word);
            bw.newLine();
        }
        for (int i = 0; i < 26; i++) {
            if (node.Trie[i] != null) {
                saveWords(node.Trie[i], word + (char) (i + 'a'), bw);
            }
        }
    }

    public void insertWord(String word) {
        trie.insert(word.toLowerCase());
        saveWords();
    }

    public void deleteWord(String word) {
        if (trie.delete(word.toLowerCase())) {
            saveWords();
        } else {
            System.out.println("Word not found in the file.");
        }
    }

    public String spellCheck(String paragraph) {
        String[] words = paragraph.toLowerCase().split("\\s+");
        StringBuilder incorrectWords = new StringBuilder();
        for (String word : words) {
            if (!trie.search(word)) {
                if (incorrectWords.length() > 0) {
                    incorrectWords.append(", ");
                }
                incorrectWords.append(word);
            }
        }
        return incorrectWords.toString();
    }

    public String decryptAndCheck(String numericalString) {
        String[] encodedWords = numericalString.split(" ");
        StringBuilder result = new StringBuilder();
        for (String encodedWord : encodedWords) {
            String word = decryptBaudot(encodedWord);
            if (trie.search(word.toLowerCase())) {
                result.append(word).append(" (Correctly spelled)\n");
            } else {
                result.append(word).append(" (Incorrectly spelled)\n");
            }
        }
        return result.toString().trim();
    }

    private String decryptBaudot(String encodedWord) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < encodedWord.length(); i += 5) {
            String baudotChar = encodedWord.substring(i, i + 5);
            if (BAUDOT_CODE_MAP.containsKey(baudotChar)) {
                word.append(BAUDOT_CODE_MAP.get(baudotChar));
            } else {
                throw new IllegalArgumentException("Invalid Baudot code: " + baudotChar);
            }
        }
        return word.toString();
    }
}