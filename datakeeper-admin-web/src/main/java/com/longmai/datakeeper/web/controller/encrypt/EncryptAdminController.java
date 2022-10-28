package com.longmai.datakeeper.web.controller.encrypt;

import com.longmai.datakeeper.utils.PageUtil;
import com.longmai.datakeeper.web.controller.BaseController;
import com.longmai.datakeeper.web.controller.encrypt.facade.EncryptAdminFacade;
import com.longmai.datakeeper.web.controller.encrypt.model.EncryptColumnVo;
import com.longmai.datakeeper.web.controller.encrypt.model.EncryptTableCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/encrypt")
public class EncryptAdminController extends BaseController {

    @Autowired
    public EncryptAdminFacade encryptAdminFacade;

    @PostMapping("/create")
    public void create(@RequestBody EncryptTableCreate encryptTableCreate){
        encryptAdminFacade.createEncryptColumn(encryptTableCreate, getCurrentUser());
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listEncryptColumnVo(){
        List<EncryptColumnVo> encryptColumnVos = encryptAdminFacade.listEncryptColumn();
        Map<String, Object> map = PageUtil.toPage(encryptColumnVos, encryptColumnVos.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody List<Integer> ids){
        return new ResponseEntity(encryptAdminFacade.delete(ids.get(0)), HttpStatus.OK);
    }

}
