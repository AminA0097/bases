package com.freq.arvand.bases.registry;



import com.freq.arvand.bases.binding.MapperProxy;
import com.freq.arvand.bases.config.MapperInfo;
import com.freq.arvand.bases.config.XmlConfiguration;
import com.freq.arvand.bases.parsing.XmlMapperParser;

import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.List;

public class MapperRegistry {

    private final List<MapperInfo> mapperInfo;

    public MapperRegistry(List<MapperInfo> mapperInfo) {
        this.mapperInfo = mapperInfo;
    }

    public <T> T getMapper(Class<T> mapperInterface) {
//        return (T) Proxy.newProxyInstance(
//                mapperInterface.getClassLoader(),
//                new Class[]{mapperInterface},
//                new MapperProxy(mapperInterface, configuration)
//        );
        return null;
    }

    /**
     * وقتی Mapper پیدا شد، XML مربوطه‌ش لود می‌شه
     */
    public void addMapper(XmlConfiguration xmlConfiguration) {

        String xmlPath =
                "mapper/" + mapperInterface.getSimpleName() + ".xml";

        InputStream is = mapperInterface
                .getClassLoader()
                .getResourceAsStream(xmlPath);

        if (is == null) {
            throw new RuntimeException("❌ XML not found: " + xmlPath);
        }
    }
}
