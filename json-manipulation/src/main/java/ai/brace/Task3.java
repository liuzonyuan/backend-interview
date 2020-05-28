package ai.brace;

import ai.brace.utils.JsonUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

/**
 * Implementation of task3
 * ========== task3 ==========
 * Goal: "Simple analysis - a word frequency counter"
 * Building on the data from both JSON files, count the word frequency in the textArray ➔ textdata elements.
 * The expected output:
 *
 * ("Elderflowers".) : 1
 * (All) : 1
 * (An) : 1
 * (And) : 1
 * (But) : 1
 * (I) : 15
 *
 * @author peterliu
 */
public class Task3 implements Task {
    @Override
    public void runTask() {
        try {
            printName();
            List<Map<String, ?>> list1 = JsonUtil.retrieveContent(fileName1, textArrayfieldName);
            List<Map<String, ?>> list2 = JsonUtil.retrieveContent(fileName2, textArrayfieldName);
            list1.addAll(list2); // merge the list
            // map reduce to get the counts for each word
            Map<String, Long> wordCount = list1.stream().map(m -> (String) m.get(keyForData))
                    .flatMap(s -> Arrays.stream(s.trim().split("\\s")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
                    .filter(word -> word.length() > 0)
                    .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(comparingByKey())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
            wordCount.forEach((k, v) -> System.out.println(String.format("(%s) : %d", k, v)));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
