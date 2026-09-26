package bankeralgo;

class TrieNode {
    TrieNode[] Trie;
    boolean isEnd;

    public TrieNode() {
        Trie = new TrieNode[26];
        for (int i = 0; i < 26; i++) {
            Trie[i] = null;
        }
        isEnd = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (index < 0 || index >= 26) {
               
                continue;
            }
            if (temp.Trie[index] == null) {
                temp.Trie[index] = new TrieNode();
            }
            temp = temp.Trie[index];
        }
        temp.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (index < 0 || index >= 26) {
               
                continue;
            }
            if (temp.Trie[index] == null) {
                return false;
            }
            temp = temp.Trie[index];
        }
        return temp.isEnd;
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEnd) {
                return false;
            }
            current.isEnd = false;
            return true;
        }
        int charIndex = word.charAt(index) - 'a';
        if (charIndex < 0 || charIndex >= 26) {
          
            return false;
        }
        if (current.Trie[charIndex] == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(current.Trie[charIndex], word, index + 1);

        if (shouldDeleteCurrentNode) {
            current.Trie[charIndex] = null;
            return !current.isEnd && allChildrenNull(current);
        }
        return false;
    }

    private boolean allChildrenNull(TrieNode node) {
        for (int i = 0; i < 26; i++) {
            if (node.Trie[i] != null) {
                return false;
            }
        }
        return true;
    }
}