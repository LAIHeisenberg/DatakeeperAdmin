package com.longmai.datakeeper.web.controller.discovery;

import com.longmai.datakeeper.web.controller.discovery.facade.SensitiveDataDiscoveryFacade;
import com.longmai.datakeeper.web.controller.discovery.model.DiscoveryDataFieldQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sensitive/data")
public class SensitiveDataDiscoveryAdminController {

    @Autowired
    private SensitiveDataDiscoveryFacade sensitiveDataDiscoveryFacade;

    @Autowired
    private SensitiveDataDiscoveryFacade facade;

    @GetMapping("/list")
    public ResponseEntity<Object> listPage(DiscoveryDataFieldQuery queryParam, Integer page, Integer size){
        return new ResponseEntity<>(facade.listPage(queryParam, page, size), HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Integer> ids){
        return new ResponseEntity(facade.delete(ids.get(0)), HttpStatus.OK);
    }

}
