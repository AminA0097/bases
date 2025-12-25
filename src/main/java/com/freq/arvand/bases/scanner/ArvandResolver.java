package com.freq.arvand.bases.scanner;



import com.freq.arvand.bases.annotation.ArvandMapper;
import com.freq.arvand.bases.registry.MapperRegistry;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ArvandResolver {
    private final MapperRegistry registry;

    public ArvandResolver(MapperRegistry registry) {
        this.registry = registry;
    }

    public void scan(String basePackage) {
        String path = basePackage.replace('.', '/');
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = cl.getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File dir = new File(resource.getFile());
                for (String fileName : dir.list()) {
                    if (fileName.endsWith(".class")) {
                        String className = basePackage + "." +
                                fileName.substring(0, fileName.length() - 6);
                        Class<?> clazz = Class.forName(className);
                        if (clazz.isInterface() && clazz.isAnnotationPresent(ArvandMapper.class)) {
                            System.out.println("🪄 Found mapper: " + clazz.getName());
//                            registry.addMapper(clazz);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error scanning package: " + basePackage, e);
        }
    }
}
