package com.freq.arvand.bases.user.mapper;

import com.freq.arvand.bases.annotation.ArvandMapper;

import java.util.List;

@ArvandMapper
public interface UserMapper {

    List<String> getAllUser();

    Object getActiveUserCount();

    List getUserByName(String userName,String password);
}