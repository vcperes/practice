package com.vitor.app.document;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

@Slf4j
@Service
public class DocumentService {

    public Map<String, String> document() throws IOException {
        Path[] codeRoots = {Paths.get("src/main/java"),Paths.get("src/test/java")};

        Map<String, String> codeMap = new ConcurrentHashMap<>();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Path root : codeRoots) {
                if (!Files.exists(root)) continue;

                try (Stream<Path> paths = Files.walk(root)) {
                    paths.filter(Files::isRegularFile)
                            .filter(p -> p.toString().endsWith(".java"))
                            .forEach(p -> executor.submit(() -> {
                                try {
                                    String relativePath = root.getFileName() + "/" + root.relativize(p).toString();
                                    String content = Files.readString(p);
                                    codeMap.put(relativePath, content);
                                } catch (IOException e) {
                                    log.error(e.getMessage());
                                    codeMap.put(p.toString(), "ERROR: " + e.getMessage());
                                }
                            }));
                }
            }
        }

        return codeMap;
    }
}
