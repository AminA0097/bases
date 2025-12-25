package com.freq.arvand.bases.scanner;


import com.freq.arvand.bases.annotation.ArvandMapper;
import com.freq.arvand.bases.registry.MapperRegistry;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class ArvandMapperScanner {

    private final MapperRegistry registry;

    public ArvandMapperScanner(MapperRegistry registry) {
        this.registry = registry;
    }

    public void scan(String basePackage) {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(ArvandMapper.class));

        for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
            try {
                Class<?> clazz = Class.forName(bd.getBeanClassName());
                System.out.println("🔍 Found mapper: " + clazz.getName());
//                registry.addMapper(clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
