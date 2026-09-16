package com.jorji;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

@FunctionalInterface
public interface PathHandler {
    Logger LOG = Logger.getLogger(PathHandler.class);

    void tryHandle(Path path) throws IOException;

    default void handle(Path path) {
        try {
            tryHandle(path);
        } catch (IOException e) {
            LOG.error("Error handling path: " + path, e);
        }
    }
}
