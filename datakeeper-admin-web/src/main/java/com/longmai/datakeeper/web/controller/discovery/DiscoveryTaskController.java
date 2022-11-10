package com.longmai.datakeeper.web.controller.discovery;

import com.longmai.datakeeper.web.controller.BaseController;
import com.longmai.datakeeper.web.controller.discovery.facade.DiscoveryTaskFacade;
import com.longmai.datakeeper.web.controller.discovery.facade.SensitiveDataDiscoveryFacade;
import com.longmai.datakeeper.web.controller.discovery.model.DiscoveryTaskCreate;
import com.longmai.datakeeper.web.controller.discovery.model.DiscoveryTaskQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/discovery/task")
public class DiscoveryTaskController extends BaseController {

    @Autowired
    private DiscoveryTaskFacade facade;
    @Autowired
    private SensitiveDataDiscoveryFacade sensitiveDataDiscoveryFacade;

    @GetMapping("/list")
    public ResponseEntity<Object> listPage(DiscoveryTaskQueryParam queryParam, Integer page, Integer size){
        return new ResponseEntity<>(facade.listPage(queryParam, page, size), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody DiscoveryTaskCreate createBean){
        facade.create(createBean, getCurrentUser());
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody List<Integer> ids){
        return new ResponseEntity(facade.delete(ids.get(0)), HttpStatus.OK);
    }

    @GetMapping("/execute/{taskId}")
    public ResponseEntity executeTask(@PathVariable Integer taskId){
        sensitiveDataDiscoveryFacade.discoveryDatabase(taskId);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}