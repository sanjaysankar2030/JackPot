package core;
java.util.HashMap;
java.util.List;

public class TrainedModel {
    HashMap<Integer, String> idToToken;  // for decoding
    HashMap<String, Integer> tokenToId;  // for encoding
    List<Pair> mergeRules;               // ordered merge rules learned during training
}
