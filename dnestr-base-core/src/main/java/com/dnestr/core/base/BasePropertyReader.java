package com.dnestr.core.base;

import java.io.InputStream;
import java.util.Properties;

public abstract class BasePropertyReader {

    protected static Properties load(String filePath) {
        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filePath)) {

            if (is == null) {
                throw new IllegalStateException("Resource not found: " + filePath);
            }

            Properties props = new Properties();
            props.load(is);
            return props;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load: " + filePath, e);
        }
    }
}
