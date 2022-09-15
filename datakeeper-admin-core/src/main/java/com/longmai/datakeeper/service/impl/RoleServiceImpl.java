package com.longmai.datakeeper.service.impl;

import com.longmai.datakeeper.dao.mapper.MenuMapper;
import com.longmai.datakeeper.dao.mapper.RoleMapper;
import com.longmai.datakeeper.dao.po.MenuPo;
import com.longmai.datakeeper.dao.po.RolePo;
import com.longmai.datakeeper.dto.AuthorityDto;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<AuthorityDto> mapToGrantedAuthorities(UserLoginDto user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getAdminFlag()) {
            permissions.add("admin");
            return permissions.stream().map(AuthorityDto::new)
                    .collect(Collectors.toList());
        }
        List<Long> roleIds = listRoleIds(user.getId());
        List<RolePo> rolePos = roleMapper.selectBatchRoleIds(roleIds);
        if (!CollectionUtils.isEmpty(rolePos)){
            List<Long> roleIdList = rolePos.stream().map(new Function<RolePo, Long>() {
                @Override
                public Long apply(RolePo rolePo) {
                    return rolePo.getRoleId();
                }
            }).collect(Collectors.toList());
            List<MenuPo> menuPos = menuMapper.selectMenuByRoleId(roleIdList);
            return menuPos.stream().map(new Function<MenuPo, String>() {
                @Override
                public String apply(MenuPo menuPo) {
                    return menuPo.getPermission();
                }
            }).map(new Function<String, AuthorityDto>() {
                @Override
                public AuthorityDto apply(String s) {
                    return new AuthorityDto(s);
                }
            }).collect(Collectors.toList());

        }else {
            return Collections.emptyList();
        }

    }


    @Override
    public List<Long> listRoleIds(Long userId){
        return roleMapper.listRoleIdByUserId(userId);
    }


}
