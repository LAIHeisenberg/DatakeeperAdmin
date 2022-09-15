package com.longmai.datakeeper.service;

import com.longmai.datakeeper.dto.AuthorityDto;
import com.longmai.datakeeper.dto.UserLoginDto;

import java.util.List;

public interface RoleService {

    List<Long> listRoleIds(Long userId);

    List<AuthorityDto> mapToGrantedAuthorities(UserLoginDto user);

}
