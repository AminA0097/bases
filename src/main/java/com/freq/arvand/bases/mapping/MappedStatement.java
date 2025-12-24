package com.freq.arvand.bases.mapping;

public class MappedStatement {

    private final String id;              // com.xxx.AuthMapper.login
    private final String sql;             // SELECT ...
    private final SqlCommandType type;    // SELECT / INSERT ...
    private final Class<?> resultType;    // فعلاً Mock

    public MappedStatement(String id, String sql,
                           SqlCommandType type,
                           Class<?> resultType) {
        this.id = id;
        this.sql = sql;
        this.type = type;
        this.resultType = resultType;
    }

    public String getId() {
        return id;
    }

    public String getSql() {
        return sql;
    }

    public SqlCommandType getType() {
        return type;
    }
}
