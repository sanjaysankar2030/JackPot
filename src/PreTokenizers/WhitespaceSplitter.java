package pretokenizer;

import core.PreTokenizer;
import java.util.List;
import java.util.ArrayList;

public class WhitespaceSplitter implements PreTokenizer {
  public List<String> split(String corpus) {
    List<String> splitted = new ArrayList<>();
    String[] parts = corpus.split(" ");
    for (String part : parts) {
        splitted.add(part);
    }
    return splitted;
  }
}
