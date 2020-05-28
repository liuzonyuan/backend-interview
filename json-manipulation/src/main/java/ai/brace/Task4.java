package ai.brace;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

/**
 * Implementation of task4
 * ========== task4 ==========
 * Goal: "Additive merging of JSON data"
 * Do as many of the following as you can:
 *
 * Merge the JSON files, from older to newer. The final JSON file should have the latest data.
 * The merge is additive. That is, if the data element in the older JSON doesn't exist in the newer JSON, it will be copied to the newer JSON.
 * Change the lastModified epoch datetime to an ISO format, e.g. 2010-01-01T12:00:00Z
 * Replace the uuid with a random one.
 * Serialize the new JSON file to disk as output.json
 * Expected output should look like the expectedOutput.json file.
 *
 *
 * **************** The output.json will be write to build directory. Please look at the trace to get the path.
 *
 * @author peterliu
 */
public class Task4 implements Task {
    @Override
    public void runTask() {
        try {
            printName();
            Map<Object, Object> result = new LinkedHashMap<>();
            Path path1 = Paths.get(ClassLoader.getSystemResource(fileName1).toURI());
            Reader reader = Files.newBufferedReader(path1, StandardCharsets.UTF_8);
            Map<?, ?> map = new Gson().fromJson(reader, Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getKey().equals(uuidfieldName)) {
                    result.put(entry.getKey(), UUID.fromString((String) entry.getValue()));
                } else if (entry.getKey().equals(lastModifiedfieldName)) {
                    result.put(entry.getKey(), Instant.ofEpochSecond(((Double)entry.getValue()).longValue()).toString());
                } else {
                    result.put(entry.getKey(), entry.getValue());
                }
            }
            Path path2 = Paths.get(ClassLoader.getSystemResource(fileName2).toURI());
            reader = Files.newBufferedReader(path2, StandardCharsets.UTF_8);
            map = new Gson().fromJson(reader, Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (!result.containsKey(entry.getKey())) {
                    result.put(entry.getKey(), entry.getValue());
                }
                if (entry.getKey().equals(textArrayfieldName)) {
                    List<Map<String, ?>> originList = (List<Map<String, ?>>) result.get(textArrayfieldName);
                    originList.addAll((List<Map<String, ?>>) entry.getValue());
                }
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String output = ClassLoader.getSystemResource(".").getPath() + "output.json";
            PrintWriter writer = new PrintWriter(new File(output));
            gson.toJson(result, writer);
            writer.flush();
            writer.close();
            System.out.println("The output file is generated at: " + output);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
