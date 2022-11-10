package com.longmai.datakeeper.web.controller.masking;

import com.longmai.datakeeper.web.controller.BaseController;
import com.longmai.datakeeper.web.controller.masking.facade.ApiMaskingFacade;
import com.longmai.datakeeper.web.controller.masking.model.ApiMaskingCreate;
import com.longmai.datakeeper.web.controller.masking.model.ApiMaskingQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/api/masking")
public class ApiMaskingController extends BaseController {

    @Autowired
    private ApiMaskingFacade facade;

    @GetMapping("/list")
    public ResponseEntity<Object> listPage(ApiMaskingQueryParam queryParam, Integer page, Integer size){
        return new ResponseEntity<>(facade.listPage(queryParam, page, size), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ApiMaskingCreate createBean){
        facade.create(createBean, getCurrentUser());
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Integer> ids){
        return new ResponseEntity(facade.delete(ids.get(0)), HttpStatus.OK);
    }

}