package com.freq.arvand.bases.user;

import com.freq.arvand.bases.mapping.MappedStatement;
import com.freq.arvand.bases.registry.MapperRegistry;
import com.freq.arvand.bases.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserImp implements UserMapper {
    @Autowired
    private final MapperRegistry mapperRegistry;

    public void test(){
        UserMapper mapper = mapperRegistry.getMapper(UserMapper.class);
        MappedStatement statement = (MappedStatement) mapper.getActiveUserCount();

        statement.getSql();
        System.out.println("Test Done");
    }
    public UserImp(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public List<String> getAllUser() {
        return List.of();
    }

    @Override
    public String getActiveUserCount() {
        return "";
    }

    @Override
    public List getUserByName(String userName,String pass) {
        return List.of();
    }
}
