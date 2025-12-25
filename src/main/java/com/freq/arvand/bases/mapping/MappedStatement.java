package com.freq.arvand.bases.mapping;

public class MappedStatement {

    private final String id;              // com.xxx.AuthMapper.login
    private final String sql;             // SELECT ...
    private final String resultType;
    private final String queryType;

    public MappedStatement(String id, String sql,
                           String resultType,
                           String queryType) {
        this.id = id;
        this.sql = sql;
        this.resultType = resultType;
        this.queryType = queryType;

    }

    public String getId() {
        return id;
    }

    public String getSql() {
        return sql;
    }

    public String getResultType() {
        return resultType;
    }

    public String getQueryType() {
        return queryType;
    }
}
