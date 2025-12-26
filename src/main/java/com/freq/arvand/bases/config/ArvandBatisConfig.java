package com.freq.arvand.bases.config;


import com.freq.arvand.bases.parsing.XmlMapperParser;
import com.freq.arvand.bases.registry.MapperRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class ArvandBatisConfig {

    @Bean
    public XmlConfiguration xmlConfiguration() {
        return new XmlConfiguration();
    }

    @Bean
    public MapperRegistry mapperRegistry(XmlConfiguration configuration)
            throws Exception {

        InputStream stream =
                getClass().getClassLoader()
                        .getResourceAsStream("application-mappers.xml");

        if (stream == null) {
            throw new RuntimeException(
                    "❌ application-mappers.xml not found");
        }

        ArvandBatisConfigParser parser =
                new ArvandBatisConfigParser();
        List<MapperInfo> mapperInfos = parser.parse(stream);

        XmlMapperParser xmlMapperParser =
                new XmlMapperParser(configuration);
        xmlMapperParser.parse(mapperInfos);

        MapperRegistry registry =
                new MapperRegistry(configuration);
        registry.addClass(mapperInfos);

        return registry;
    }
}
