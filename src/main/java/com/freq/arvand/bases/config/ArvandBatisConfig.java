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
    public MapperRegistry mapperRegistry(XmlConfiguration configuration) {

        InputStream xmlStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("application-mappers.xml");

        if (xmlStream == null) {
            throw new RuntimeException("❌ application-mappers.xml not found");
        }

        ArvandBatisConfigParser parser = new ArvandBatisConfigParser();
        List<MapperInfo> mapperInfos = parser.parse(xmlStream);

        XmlMapperParser xmlMapperParser =
                new XmlMapperParser(configuration);

        xmlMapperParser.parse(mapperInfos);
        System.out.println("Application Mapper Registry Done Successfully");
        return null;
    }
}