package com.longmai.datakeeper.service;

import com.longmai.datakeeper.dto.MenuDto;
import com.longmai.datakeeper.vo.MenuVo;

import java.util.List;

public interface MenuService {


    List<MenuDto> findByUser(Long currentUserId);

    List<MenuDto> buildTree(List<MenuDto> menuDtos);

    List<MenuVo> buildMenus(List<MenuDto> menuDtos);
}
