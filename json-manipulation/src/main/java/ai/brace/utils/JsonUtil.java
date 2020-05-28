package ai.brace.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static List<Map<String, ?>> retrieveContent(String fileName, String fieldName) throws IOException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        Map<?, ?> map = new Gson().fromJson(reader, Map.class);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey().equals(fieldName)) {
                return (List<Map<String, ?>>)entry.getValue();
            }
        }
        return null;
    }
}
