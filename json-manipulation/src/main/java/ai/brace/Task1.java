package ai.brace;

import ai.brace.utils.JsonUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Implementation of task1
 * ========== task1 ==========
 * Goal: "Load, parse, and sort"
 * Load the a1.json file in the resources directory.
 * Sort and print the contents (textdata) of textArray by ID, in ascending order.
 * The expected output:
 *
 *   All this has nothing...
 *   It was a clear, cold day in January...
 *   I knew what I was doing when...
 *
 * @author peterliu
 */
public class Task1 implements Task {
    @Override
    public void runTask() {
        try {
            printName();
            List<Map<String, ?>> list = JsonUtil.retrieveContent(fileName1, textArrayfieldName);
            // sort list by id field in the map
            Collections.sort(list, (l1, l2) -> ((Double) l1.get("id")).compareTo((Double) l2.get("id")));
            for (Map<?, ?> item : list) {
                System.out.println(item.get(keyForData));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
