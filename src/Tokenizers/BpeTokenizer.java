package Bpe;

import core.Tokenizer;
import io.ModelRead;
import PreTokenizer.WhitespaceSplitter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class BpeTokenizer implements Tokenizer {
  private String text;
  private Map<String, Integer> map = new HashMap<>();
  int freq;

  public BpeTokenizer() {
    this.text = text;
    this.map = map;
  }

  public void load(String path) {
    ModelRead read = new ModelRead();
    try {
      text = read.readFileBytes(path);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    System.out.println("Text : " + text);
  }

  public void save(String path) {
    // Saves the vocabulary to the file
  }

  public List<Integer> encode(String text) {
    //encode
  }

  public Map<String, Integer> tokenize(String corpus, int vocabSize) {
  }

  void train(String corpus, int vocabSize) {
    text = load(corpus);
    List<String>splitted=WhitespaceSplitter.split(corpus);
    tokenize(String text);
    List<Integers> encode;
  }
}

/*
    // string converted to charArray
    char[] tokens = text.toCharArray();
    System.out.println(tokens);
    for (int i = 0; i < tokens.length; i++) {
      if (i + 1 < tokens.length) {
        // Splitted for tokenization as ascii value of the chars
        char[] pairs = {
          tokens[i], tokens[i + 1]
        }; // TODO:Shit is fucking up the string when it splits it
        System.out.println(
            ""
                + tokens[i]
                + tokens[i + 1]); // took the int[] pairs and converted to String for convineince
        // System.out.println(pairs + "\n");
        String pairs_str = new String();
        pairs_str = Arrays.toString(pairs);
        if (!map.containsKey(pairs_str)) {
          freq = 1;
          map.put(pairs_str, freq);
        } else {
          map.put(pairs_str, map.get(pairs_str) + 1);
        }
      }
    }
    return map;
*/






