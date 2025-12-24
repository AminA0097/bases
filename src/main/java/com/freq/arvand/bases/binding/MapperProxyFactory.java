package com.freq.arvand.bases.binding;


import com.freq.arvand.bases.config.XmlConfiguration;

import java.lang.reflect.Proxy;

public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;
    private final XmlConfiguration xmlConfiguration;

    public MapperProxyFactory(Class<T> mapperInterface,
                              XmlConfiguration xmlConfiguration) {
        this.mapperInterface = mapperInterface;
        this.xmlConfiguration = xmlConfiguration;
    }

    @SuppressWarnings("unchecked")
    public T newInstance() {
        return (T) Proxy.newProxyInstance(
                mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                new MapperProxy(mapperInterface, xmlConfiguration)
        );
    }
}
