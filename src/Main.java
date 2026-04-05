package main;

import Bpe.BpeTokenizer;
import core.Utils;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    Map<String, Integer> tokens = new HashMap<>();
    BpeTokenizer bpe = new BpeTokenizer();
    Utils utils = new Utils();
    bpe.setText(
        "Byte-Pair Encoding (BPE) is a text tokenization technique in Natural Language"
            + " Processing  It breaks down words into smaller, meaningful pieces called"
            + " subwords  It works by repeatedly finding the most common pairs of characters in"
            + " the text and combining them into a new subword until the vocabulary reaches a"
            + " desired size  This technique helps in handling rare or unknown words by"
            + " breaking them into smaller parts that the model has already learned during"
            + " training  By reducing the vocabulary size, it makes it easier to work with"
            + " large amounts of text while allowing the model to understand wide variety of"
            + " languages ");
    // System.out.println(bpe.getText());
    tokens = bpe.tokenize();
    utils.findMaxFreqs(tokens, 10);
  }
}

// java-tokenizers/
//   src/
//     core/
//       Tokenizer.java          — main interface
//       PreTokenizer.java       — pre-tokenization interface
//       Vocabulary.java         — shared vocab structure
//       SpecialTokens.java      — UNK, PAD, BOS, EOS
//     pretokenizers/
//       WhitespaceSplitter.java — simple splitting
//       GPT2Splitter.java       — regex based
//       ByteLevelSplitter.java  — raw byte stream
//     tokenizers/
//       SimpleTokenizer.java    — Tsoding style
//       GPT2Tokenizer.java      — byte level BPE
//       WordPieceTokenizer.java — BERT style
//       UnigramTokenizer.java   — SentencePiece style
//     trainers/
//       BPETrainer.java         — learns BPE merge rules
//       WordPieceTrainer.java   — learns WordPiece vocab
//       UnigramTrainer.java     — EM based pruning
//     io/
//       TokenizerSaver.java     — save vocab and rules to disk
//       TokenizerLoader.java    — load back from disk
//   test/
//     SimpleTokenizerTest.java
//     GPT2TokenizerTest.java
//   build.bat
//   run.bat
//   README.md

// String -> (PreTokenizer)WhitespaceSplitter -> (Tokenizer)SimpleBPE -> (Train)BPETrainer -> tokens
// The real time example is the text generation that he is trying to do
