package com.freq.arvand.bases.config;


import com.freq.arvand.bases.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

public class XmlConfiguration {

    // key = statementId  | value = SQL definition
    private final Map<String, MappedStatement> statements = new HashMap<>();

    public void add(MappedStatement ms) {

        System.out.println("📦 Register SQL → " + ms.getId());
        statements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return statements.get(id);
    }
}
