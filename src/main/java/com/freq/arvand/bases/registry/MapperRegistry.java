package com.freq.arvand.bases.registry;



import com.freq.arvand.bases.config.MapperInfo;
import com.freq.arvand.bases.config.XmlConfiguration;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperRegistry {
    private final XmlConfiguration configuration;
    private final Map<Class<?>, Object> knownMappers = new HashMap<>();

    public MapperRegistry(XmlConfiguration configuration) {
        this.configuration = configuration;
    }

    public void addClass(List<MapperInfo> mapperInfos) {

    }
}
