package main;

import Bpe.BpeTokenizer;
import utils.Utils;
// import pretokenizers.WhitespaceSplitter;
// import train.BpeTrainer;
import java.util.HashMap;
import java.util.Map;
import io.ModelRead;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    Map<String, Integer> tokens = new HashMap<>();
    Utils util=new Utils();
    BpeTokenizer bpe = new BpeTokenizer();
    // Utils utils = new Utils();
    // BpeTrainer train =new BpeTrainer();
    // WhitespaceSplitter splitter =new WhitespaceSplitter();
    // ModelRead read = new ModelRead();
    // try {
    //   String data = read.readFileBytes("E:\\Projects\\Bpe_Java\\src\\text.jkpt");
    //   System.out.println(data);
    // } catch (IOException ioe) {
    //   ioe.printStackTrace();
    // }
    tokens = bpe.tokenize("E:\\Projects\\Bpe_Java\\src\\text.jkpt");
    System.out.println(tokens);
    util.findMaxFreqs(tokens,10);
    // splitted=splitter.split();
    // tokens = bpe.tokenize();
    // utils.findMaxFreqs(tokens, 10);
    // train.train(tokens);
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
