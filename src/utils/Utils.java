package utils;
import core.UtilsInterface;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Utils implements UtilsInterface{

    public void findMaxFreqs(Map<String, Integer> pairFrequency, int n) {
    // PriorityQueue for finding top 10 values
    PriorityQueue<Map.Entry<String, Integer>> maxHeap =
        new PriorityQueue<Map.Entry<String, Integer>>(
            pairFrequency.size(), new Comparator<Map.Entry<String, Integer>>() {
              // Comparator for comparing two single Values by using "get.Values()" from the Map
              // hence the "Map.<...>"
              public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue().compareTo(a.getValue());
              }
            });
    maxHeap.addAll(pairFrequency.entrySet());
    System.out.println("------------------------------------------------");
    // Get top pairs
    int i = 0;
    while (!maxHeap.isEmpty() && i < n) {
      Map.Entry<String, Integer> top = maxHeap.poll(); // .poll is for dequeue operation
      System.out.println(i + " | " + top.getKey() + " -> " + top.getValue());
      i++;
    }
    System.out.println("------------------------------------------------");
  }

}
