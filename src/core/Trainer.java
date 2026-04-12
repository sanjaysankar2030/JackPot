package core;
import core.TrainedModel;

public interface Trainer {
    TrainedModel train(String corpus, int vocabSize);
}
