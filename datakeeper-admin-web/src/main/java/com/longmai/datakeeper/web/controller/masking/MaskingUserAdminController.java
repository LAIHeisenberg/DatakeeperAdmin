package com.longmai.datakeeper.web.controller.masking;

import com.longmai.datakeeper.utils.PageUtil;
import com.longmai.datakeeper.web.controller.BaseController;
import com.longmai.datakeeper.web.controller.masking.facade.MaskingUserAdminFacade;
import com.longmai.datakeeper.web.controller.masking.model.MaskingUserCreate;
import com.longmai.datakeeper.web.controller.masking.model.MaskingUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/db/masking/user")
public class MaskingUserAdminController extends BaseController {

    @Autowired
    private MaskingUserAdminFacade maskingUserAdminFacade;

    @GetMapping("/list")
    public ResponseEntity<Object> listAll(){
        List<MaskingUserVo> maskingUserVos = maskingUserAdminFacade.listAll();
        Map<String, Object> map = PageUtil.toPage(maskingUserVos, maskingUserVos.size());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody MaskingUserCreate maskingUserCreate){
        return new ResponseEntity<>( maskingUserAdminFacade.create(maskingUserCreate, getCurrentUser()), HttpStatus.OK);
    }

}
