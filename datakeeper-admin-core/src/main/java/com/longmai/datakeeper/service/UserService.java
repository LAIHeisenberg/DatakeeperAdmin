package com.longmai.datakeeper.service;

import com.longmai.datakeeper.dto.UserDto;
import com.longmai.datakeeper.dto.UserLoginDto;

import java.util.List;

public interface UserService {

    List<UserDto> listAllEnabledUser();

    UserDto findByDn(String dn);

    UserLoginDto getUserLoginDto(String userName);

}
