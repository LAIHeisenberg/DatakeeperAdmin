package com.longmai.datakeeper.web.controller.masking;

import com.longmai.datakeeper.utils.PageUtil;
import com.longmai.datakeeper.web.controller.BaseController;
import com.longmai.datakeeper.web.controller.masking.facade.DBMaskingAdminFacade;
import com.longmai.datakeeper.web.controller.masking.model.MaskingColumnVo;
import com.longmai.datakeeper.web.controller.masking.model.MaskingTableCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/db/masking")
public class DBMaskingAdminController extends BaseController {

    @Autowired
    private DBMaskingAdminFacade maskingAdminFacade;

    @GetMapping("/list")
    public ResponseEntity<Object> listAll(){
        List<MaskingColumnVo> maskingColumnVos = maskingAdminFacade.listAll();
        Map<String, Object> map = PageUtil.toPage(maskingColumnVos, maskingColumnVos.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/listBy")
    public ResponseEntity list(@RequestParam("dataSourceId")Integer dataSourceId, @RequestParam("tableName")String tableName){
        return new ResponseEntity(maskingAdminFacade.listBy(dataSourceId, tableName), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody MaskingTableCreate maskingTableCreate){
        maskingAdminFacade.createEncryptColumn(maskingTableCreate, getCurrentUser());
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody List<Integer> ids){
        return new ResponseEntity(maskingAdminFacade.delete(ids.get(0)), HttpStatus.OK);
    }


}
