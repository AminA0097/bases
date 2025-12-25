package com.freq.arvand.bases.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOUtil {

    public static String readInputStream(InputStream inputStream) throws IOException {

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[4096];
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, charsRead);
            }

            return sb.toString();
        }
    }

    public static String readResourceFile(String resourcePath) throws IOException {

        try (InputStream inputStream =
                     IOUtil.class
                             .getClassLoader()
                             .getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new FileNotFoundException("فایل پیدا نشد: " + resourcePath);
            }

            return readInputStream(inputStream);
        }
    }
}
