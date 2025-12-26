
package com.freq.arvand.bases.binding;
import com.freq.arvand.bases.config.XmlConfiguration;
import com.freq.arvand.bases.mapping.MappedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy implements InvocationHandler {

    private final Class<?> mapperInterface;
    private final XmlConfiguration configuration;

    public MapperProxy(Class<?> mapperInterface,
                       XmlConfiguration configuration) {
        this.mapperInterface = mapperInterface;
        this.configuration = configuration;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }

        String statementId =
                mapperInterface.getName() + "." + method.getName();

        MappedStatement ms =
                configuration.getMappedStatement(statementId);

        if (ms == null) {
            throw new RuntimeException(
                    "❌ No mapped statement found for: " + statementId
            );
        }


        // TODO: Executor will be here
        return ms;
    }
}