package com.longmai.datakeeper.web.controller.user;

import com.longmai.datakeeper.rest.api.ApiMaskingHandler;
import com.longmai.datakeeper.rest.dto.ApiMaskingDetailDto;
import com.longmai.datakeeper.web.controller.user.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ApiMaskingHandler apiMaskingHandler;

    @PreAuthorize("@el.check('user:list')")
    @GetMapping
    public ResponseEntity<Object> listAllUser(){
        List<ApiMaskingDetailDto> apiMaskingDetailDtos = apiMaskingHandler.listAll();
        return new ResponseEntity<>(userFacade.listAllUser(), HttpStatus.OK);
    }


}
