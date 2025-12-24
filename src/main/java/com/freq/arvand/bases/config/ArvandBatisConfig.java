package com.freq.arvand.bases.config;


import com.freq.arvand.bases.registry.MapperRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class ArvandBatisConfig {

    @Bean
    public XmlConfiguration xmlConfiguration() {
        return new XmlConfiguration();
    }

    @Bean
    public MapperRegistry mapperRegistry(XmlConfiguration configuration) {

        InputStream xmlStream = this.getClass().getClassLoader()
                .getResourceAsStream("application-mappers.xml");
        ArvandBatisConfigParser parser = new ArvandBatisConfigParser();
        List<MapperInfo> packages = parser.parse(xmlStream);
        MapperRegistry registry = new MapperRegistry(packages);
        registry.addMapper(configuration);

        for (MapperInfo pkg : packages) {
            System.out.println(pkg);;
        }

        return registry;
    }
}