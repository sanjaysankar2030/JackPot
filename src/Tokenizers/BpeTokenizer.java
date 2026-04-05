package Bpe;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class BpeTokenizer {
  private String text;
  private Map<String, Integer> map = new HashMap<>();
  int freq;

  public BpeTokenizer() {
    this.text = text;
    this.map = map;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }
  public Map<String,Integer> tokenize() {
    // string converted to charArray
    char[] tokens = text.toCharArray();
    for (int i = 0; i < tokens.length; i++) {
      if (i + 1 < tokens.length) {
        // Splitted for tokenization as ascii value of the chars
        int[] pairs = {tokens[i], tokens[i + 1]};
        // took the int[] pairs and converted to String for convineince
        String pairs_str = new String();
        pairs_str=Arrays.toString(pairs);
        if (!map.containsKey(pairs_str)) {
          freq = 1;
          map.put(pairs_str, freq);
        } else {
          map.put(pairs_str, map.get(pairs_str) + 1);
        }
      }
    }
    // System.out.println(map);
    // System.out.println();
    return map;
  }
}

