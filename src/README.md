# Jackpot — Java Tokenization Library
> A dependency-free Java tokenization framework supporting BPE, WordPiece, and Unigram.

---

## Project Structure

```
jackpot/
  src/
    core/
      Tokenizer.java          — main interface
      PreTokenizer.java       — pre-tokenization interface
      Trainer.java            — trainer interface
      TrainedModel.java       — vocabulary + merge rules bundle
      Vocabulary.java         — shared vocab structure
      SpecialTokens.java      — UNK, PAD, BOS, EOS
    pretokenizers/
      WhitespaceSplitter.java — simple whitespace splitting
      GPT2Splitter.java       — regex based splitting
      ByteLevelSplitter.java  — raw byte stream
    tokenizers/
      SimpleTokenizer.java    — Tsoding style, basic BPE
      GPT2Tokenizer.java      — byte level BPE, GPT2 style
      WordPieceTokenizer.java — BERT style
      UnigramTokenizer.java   — SentencePiece style
    trainers/
      BPETrainer.java         — learns BPE merge rules from corpus
      WordPieceTrainer.java   — learns WordPiece vocabulary
      UnigramTrainer.java     — EM based pruning
    io/
      TokenizerSaver.java     — saves vocabulary and merge rules to disk
      TokenizerLoader.java    — loads trained model from disk
  test/
    SimpleTokenizerTest.java
    GPT2TokenizerTest.java
  build.bat
  run.bat
  README.md
```

---

## Core Interfaces

### Tokenizer

```java
public interface Tokenizer {
    void train(String corpus, int vocabSize);
    List<Integer> encode(String text);
    String decode(List<Integer> tokens);
    void save(String path);
    void load(String path);
}
```

### PreTokenizer

```java
public interface PreTokenizer {
    List<String> split(String text);
}
```

### Trainer

```java
public interface Trainer {
    TrainedModel train(String corpus, int vocabSize);
}
```

### TrainedModel

```java
public class TrainedModel {
    HashMap<Integer, String> idToToken;  // for decoding
    HashMap<String, Integer> tokenToId;  // for encoding
    List<Pair> mergeRules;               // ordered merge rules learned during training
}
```

---

## Component Responsibilities

### PreTokenizer
Splits raw text into chunks before BPE runs. Merge rules never cross chunk boundaries. Different tokenizers use different splitting strategies.

```
WhitespaceSplitter  →  splits on spaces only, simple and fast
GPT2Splitter        →  regex based, handles punctuation, contractions, spaces
ByteLevelSplitter   →  no splitting, treats entire text as raw byte stream
```

Example:
```
input:  "hello world, how are you?"

WhitespaceSplitter → ["hello", "world,", "how", "are", "you?"]
GPT2Splitter       → ["hello", " world", ",", " how", " are", " you", "?"]
```

### Trainer
Learns merge rules from a corpus. Runs once, produces a TrainedModel, saves to disk. Never runs again at inference time.

BPE training loop:
```
1. split corpus into chunks via PreTokenizer
2. convert each chunk into individual characters or bytes
3. count frequency of every adjacent pair across all chunks
4. find most frequent pair
5. merge that pair into a new token, add to vocabulary
6. update frequencies incrementally
7. repeat until vocabulary size is reached
8. return TrainedModel containing vocabulary + ordered merge rules
```

### Tokenizer
Takes a TrainedModel and uses it to encode and decode text. Lightweight, fast, no training logic inside.

Encoding:
```
"hello"
→ PreTokenizer splits → ["hello"]
→ split into chars   → ["h","e","l","l","o"]
→ apply merge rule 1 → ["h","e","ll","o"]      (l+l → ll)
→ apply merge rule 2 → ["he","ll","o"]          (h+e → he)
→ apply merge rule 3 → ["hell","o"]             (he+ll → hell)
→ vocabulary lookup  → [9, 3]
```

Decoding:
```
[9, 3]
→ id lookup  → ["hell", "o"]
→ join       → "hello"
```

### Vocabulary
Two HashMaps:
```java
HashMap<Integer, String> idToToken;  // decode: 9 → "hell"
HashMap<String, Integer> tokenToId;  // encode: "hell" → 9
```

Base vocabulary starts with all 256 ASCII byte values (ids 0-255). Every learned merge gets the next available id starting from 256.

### IO
Versioned binary file format. Magic header + version byte from day one.

File format:
```
"JKPT" + version_byte + raw_vocabulary + raw_merge_rules
```

---

## Implementations

### SimpleTokenizer
- PreTokenizer: WhitespaceSplitter
- Algorithm: character level BPE
- Reference: Tsoding's bpe
- Goal: pure algorithm, no complexity, fast to build and understand

### GPT2Tokenizer
- PreTokenizer: GPT2Splitter
- Algorithm: byte level BPE
- Reference: Karpathy's minbpe
- Base vocabulary: all 256 byte values instead of characters
- Handles any unicode input without UNK tokens
- Goal: production quality, matches real GPT2 tokenization behavior

### WordPieceTokenizer
- PreTokenizer: WhitespaceSplitter
- Algorithm: WordPiece — used by BERT
- Difference from BPE: merges based on likelihood score not raw frequency
- Score formula: freq(pair) / freq(left) * freq(right)
- Goal: BERT compatible tokenization

### UnigramTokenizer
- PreTokenizer: ByteLevelSplitter
- Algorithm: Unigram language model
- Starts with large vocabulary, prunes tokens based on likelihood impact
- Uses Viterbi algorithm for efficient decoding
- Goal: SentencePiece compatible, strong for multilingual text

---

## The Full Pipeline

```
corpus
  ↓
Trainer (runs once)
  ↓
TrainedModel (vocabulary + merge rules)
  ↓
TokenizerSaver → saved to disk as .jkpt file
  ↓
TokenizerLoader → loaded at runtime
  ↓
Tokenizer (lightweight, production)
  ↓
encode("hello world") → [9, 3, 15, 42]
decode([9, 3, 15, 42]) → "hello world"
```

---

## Build Order

1. Finalize all interfaces — get the abstraction right before any algorithm
2. Vocabulary and TrainedModel data structures
3. WhitespaceSplitter PreTokenizer
4. BPETrainer — core training loop
5. SimpleTokenizer — validates the interface works end to end
6. IO layer — save and load TrainedModel
7. GPT2Splitter PreTokenizer
8. GPT2Tokenizer — byte level BPE
9. WordPieceTrainer + WordPieceTokenizer
10. UnigramTrainer + UnigramTokenizer

---

## Real World Use Cases

- JVM ecosystem NLP — pure Java alternative to HuggingFace Rust bindings
- Android on-device ML — works without native bindings
- Apache Spark pipelines — native Java tokenization for big data text preprocessing
- Enterprise Java systems — no Python dependencies, plugs into existing Java monoliths
- Indic language models — multilingual tokenization support

---

## References

- Original BPE paper: Neural Machine Translation of Rare Words with Subword Units — Sennrich et al. 2015
- Karpathy's minbpe — GPT2 style reference implementation
- Tsoding's bpe — pure compression reference implementation
- HuggingFace tokenizers — API design inspiration
