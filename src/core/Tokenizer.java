package core;
import java.util.List;

public interface Tokenizer {
    void train(String corpus, int vocabSize);
    List<Integer> encode(String text);
    String decode(List<Integer> tokens);
    void save(String path);
    void load(String path);
}
/*
tokenize(path,vocabSize);
save("/path",json);
