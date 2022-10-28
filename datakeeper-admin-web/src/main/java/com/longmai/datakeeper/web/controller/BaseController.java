package com.longmai.datakeeper.web.controller;

import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.security.service.dto.JwtUserDto;
import com.longmai.datakeeper.utils.SecurityUtils;

import java.util.Objects;

public class BaseController {

    public UserLoginDto getCurrentUser(){
        JwtUserDto jwtUserDto = (JwtUserDto) SecurityUtils.getCurrentUser();
        if (Objects.nonNull(jwtUserDto)){
            return jwtUserDto.getUser();
        }
        return null;
    }
}
