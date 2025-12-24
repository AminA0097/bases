package com.freq.arvand.bases.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOUtil {


    /**
     * خواندن InputStream با InputStreamReader و ذخیره در byte[]
     * سپس تبدیل به String
     */
    public static String readInputStream(InputStream inputStream) throws IOException {
        // مرحله 1: خواندن به byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        char[] buffer = new char[4096];
        int charsRead;

        while ((charsRead = reader.read(buffer)) != -1) {
            // تبدیل char[] به byte[] و ذخیره
            String chunk = new String(buffer, 0, charsRead);
            byteArrayOutputStream.write(chunk.getBytes(StandardCharsets.UTF_8));
        }

        reader.close();

        // مرحله 2: تبدیل byte[] به String
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * خواندن فایل از classpath
     */
    public static String readResourceFile(String resourcePath) throws IOException {

        InputStream inputStream = IOUtil.class.getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new FileNotFoundException("فایل پیدا نشد: " + resourcePath);
        }
        return readInputStream(inputStream);
    }
}