package com.freq.arvand.bases.registry;



import com.freq.arvand.bases.annotation.ArvandMapper;
import com.freq.arvand.bases.binding.MapperProxy;
import com.freq.arvand.bases.config.MapperInfo;
import com.freq.arvand.bases.config.XmlConfiguration;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperRegistry {

    private final XmlConfiguration configuration;
    private final Map<Class<?>, Object> knownMappers = new HashMap<>();

    public MapperRegistry(XmlConfiguration configuration) {
        this.configuration = configuration;
    }

    public void addClass(List<MapperInfo> mapperInfos)
            throws ClassNotFoundException {

        for (MapperInfo info : mapperInfos) {
            String fqcn =
                    info.getClassPackage() + "." + info.getClassName();

            Class<?> clazz = Class.forName(fqcn);

            if (knownMappers.containsKey(clazz)) {
                continue;
            }

            Object proxy = Proxy.newProxyInstance(
                    clazz.getClassLoader(),
                    new Class[]{clazz},
                    new MapperProxy(clazz, configuration)
            );

            knownMappers.put(clazz, proxy);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clazz) {
        return (T) knownMappers.get(clazz);
    }
}