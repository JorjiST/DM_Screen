package com.jorji;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

@FunctionalInterface
public interface PathHandler {
    void tryHandle(Path path) throws IOException;

    default void handle(Path path) {
        try {
            tryHandle(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load: " + path, e);
        }
    }
}
