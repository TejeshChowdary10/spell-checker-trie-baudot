# Spell Checker with Trie + Baudot Code Decoding

> **An Efficient Spell Checker System for Paragraphs and Baudot Codes Using Tries**
> IEEE ASIANCON 2024 -- DOI: [10.1109/ASIANCON62057.2024.10838175](https://doi.org/10.1109/ASIANCON62057.2024.10838175)

---

## Overview

A Java desktop application that implements an efficient spell checker using a **Trie data structure**, extended with the ability to decode and spell-check **Baudot-encoded text**. Includes a Swing GUI with admin login, CRUD operations on the dictionary Trie, and paragraph-level spell checking.

---

## Features

- **Paragraph spell checking** -- loads a dictionary into a Trie; checks each word in O(L) time where L is word length
- **Baudot code decoding** -- decodes 5-bit Baudot-encoded strings and spell-checks the result
- **Admin panel** -- insert, delete, and search words in the Trie via GUI
- **Java Swing UI** -- login screen -> main menu -> spell check / Baudot mode

---

## Architecture

```
FILE.txt (dictionary)
    |
    v
Trie (insert all words on startup)
    |
    v
User Input (paragraph or Baudot string)
    |-- Paragraph mode -> tokenise -> Trie.search() per word -> highlight misspellings
    +-- Baudot mode   -> decode 5-bit codes -> tokenise -> Trie.search() per word
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

- **Trie (prefix tree)** -- a tree where each path from root to leaf spells a word; edges represent characters
- **Baudot code** -- 5-bit character encoding developed by Emile Baudot (1870); historically used in telegraphs and teletypes; predecessor to ASCII
- **CRUD on Trie** -- insert (word registration), delete (word removal), search (spell check)

---

## Authors

Geda Tejesh Chowdary | Paramkusam Sriharsha | Yelipe Gowtham

Amrita Vishwa Vidyapeetham, Bengaluru
