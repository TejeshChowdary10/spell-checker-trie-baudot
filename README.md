# Spell Checker with Trie + Baudot Code Decoding

> **An Efficient Spell Checker System for Paragraphs and Baudot Codes Using Tries**  
> IEEE ASIANCON 2024 â€” DOI: [10.1109/ASIANCON62057.2024.10838175](https://doi.org/10.1109/ASIANCON62057.2024.10838175)

---

## Overview

A Java desktop application that implements an efficient spell checker using a **Trie data structure**, extended with the ability to decode and spell-check **Baudot-encoded text**. Includes a Swing GUI with admin login, CRUD operations on the dictionary Trie, and paragraph-level spell checking.

---

## Features

- **Paragraph spell checking** â€” loads a dictionary into a Trie; checks each word in O(L) time where L is word length
- **Baudot code decoding** â€” decodes 5-bit Baudot-encoded strings and spell-checks the result
- **Admin panel** â€” insert, delete, and search words in the Trie via GUI
- **Java Swing UI** â€” login screen â†’ main menu â†’ spell check / Baudot mode

---

## Architecture

```
FILE.txt (dictionary)
    â†“
Trie (insert all words on startup)
    â†“
User Input (paragraph or Baudot string)
    â”œâ”€â”€ Paragraph mode â†’ tokenise â†’ Trie.search() per word â†’ highlight misspellings
    â””â”€â”€ Baudot mode   â†’ decode 5-bit codes â†’ tokenise â†’ Trie.search() per word
```

### Trie Operations

| Operation | Time Complexity |
|---|---|
| Insert | O(L) |
| Search | O(L) |
| Delete | O(L) |
| Prefix check | O(L) |

where L = length of the word.

### Why Trie over HashMap?
A HashMap gives O(1) exact lookup but cannot efficiently support prefix queries, autocomplete, or ordered traversal. A Trie naturally supports these operations and is memory-efficient for a large dictionary with shared prefixes.

---

## Files

| File | Description |
|---|---|
| `Trie.java` | Core Trie data structure (insert, search, delete) |
| `Main.java` | Login screen + application entry point |
| `FileOperations.java` | Dictionary loading from FILE.txt |
| `AdminView.java` | Admin GUI for CRUD operations |

---

## Setup

1. Place an English dictionary file at `FILE.txt` in the project root  
   (e.g., download from [dwyl/english-words](https://github.com/dwyl/english-words))
2. Compile: `javac -d out src/*.java`
3. Run: `java -cp out Main`

---

## Key Concepts

- **Trie (prefix tree)** â€” a tree where each path from root to leaf spells a word; edges represent characters
- **Baudot code** â€” 5-bit character encoding developed by Ã‰mile Baudot (1870); historically used in telegraphs and teletypes; predecessor to ASCII
- **CRUD on Trie** â€” insert (word registration), delete (word removal), search (spell check)

---

## Authors

Geda Tejesh Chowdary Â· Paramkusam Sriharsha Â· Yelipe Gowtham  
Amrita Vishwa Vidyapeetham, Bengaluru
