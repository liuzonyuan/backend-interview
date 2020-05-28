package ai.brace;

import ai.brace.utils.JsonUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Implementation of task2
 * ========== task2 ==========
 * Goal: "Merging data"
 * Load both the a1.json and a2.json files from resources.
 * Merge, sort, and print textdata in both textArray arrays by ID, in ascending order.
 * The expected output:
 *
 *   All this has nothing...
 *   It was a clear, cold day in January...
 *   I knew what I was doing when...
 *   I did not have long to wait...
 *   I had of late climbed these...
 *   I walked on through well-ordered...
 *
 * @author peterliu
 */
public class Task2 implements Task{
    @Override
    public void runTask() {
        try {
            printName();
            List<Map<String, ?>> list1 = JsonUtil.retrieveContent(fileName1, textArrayfieldName);
            List<Map<String, ?>> list2 = JsonUtil.retrieveContent(fileName2, textArrayfieldName);
            list1.addAll(list2); // merge two lists
            Collections.sort(list1, (l1, l2) -> ((Double) l1.get("id")).compareTo((Double) l2.get("id")));
            for (Map<?, ?> item : list1) {
                System.out.println(item.get(keyForData));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
