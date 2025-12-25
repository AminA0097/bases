package com.freq.arvand.bases.config;


import com.freq.arvand.bases.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

public class XmlConfiguration {

    private final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void add(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }
}

