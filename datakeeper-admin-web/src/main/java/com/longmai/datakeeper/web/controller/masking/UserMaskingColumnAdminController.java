package com.longmai.datakeeper.web.controller.masking;

import com.longmai.datakeeper.utils.PageUtil;
import com.longmai.datakeeper.web.controller.BaseController;
import com.longmai.datakeeper.web.controller.masking.facade.UserMaskingColumnAdminFacade;
import com.longmai.datakeeper.web.controller.masking.model.UserMaskingColumnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/db/masking/user/column")
public class UserMaskingColumnAdminController extends BaseController {

    @Autowired
    private UserMaskingColumnAdminFacade userMaskingColumnAdminFacade;

    @GetMapping("/list")
    public ResponseEntity<Object> listColumn(){
        List<UserMaskingColumnVo> userMaskingColumnVos = userMaskingColumnAdminFacade.listColumn();
        Map<String, Object> map = PageUtil.toPage(userMaskingColumnVos, userMaskingColumnVos.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody List<Integer> ids){
        return new ResponseEntity<>(userMaskingColumnAdminFacade.deleteById(ids.get(0)), HttpStatus.OK);
    }

}
