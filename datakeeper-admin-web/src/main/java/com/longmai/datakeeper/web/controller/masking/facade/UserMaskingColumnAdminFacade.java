package com.longmai.datakeeper.web.controller.masking.facade;

import com.longmai.datakeeper.dao.po.UserMaskingColumnPo;
import com.longmai.datakeeper.service.db.DBMaskingColumnService;
import com.longmai.datakeeper.service.db.DBUserMaskingColumnService;
import com.longmai.datakeeper.web.controller.masking.model.UserMaskingColumnVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserMaskingColumnAdminFacade {

    @Autowired
    private DBUserMaskingColumnService userMaskingColumnService;

    public List<UserMaskingColumnVo> listColumn(){
        List<UserMaskingColumnPo> userMaskingColumnPos = userMaskingColumnService.listUserMaskingColumn();
        if (CollectionUtils.isEmpty(userMaskingColumnPos)){
            return Collections.emptyList();
        }
        return userMaskingColumnPos.stream().map(new Function<UserMaskingColumnPo, UserMaskingColumnVo>() {
            @Override
            public UserMaskingColumnVo apply(UserMaskingColumnPo userMaskingColumnPo) {
                UserMaskingColumnVo vo = new UserMaskingColumnVo();
                BeanUtils.copyProperties(userMaskingColumnPo,vo);
                return vo;
            }
        }).collect(Collectors.toList());
    }

    public boolean deleteById(Integer id){
        return userMaskingColumnService.deleteById(id);
    }


}
