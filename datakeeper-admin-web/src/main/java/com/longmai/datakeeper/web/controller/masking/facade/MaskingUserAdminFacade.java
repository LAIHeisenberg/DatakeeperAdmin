package com.longmai.datakeeper.web.controller.masking.facade;

import com.longmai.datakeeper.dao.entity.DbMaskingUserEntity;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.service.db.DbMaskingUserService;
import com.longmai.datakeeper.web.controller.masking.model.MaskingUserCreate;
import com.longmai.datakeeper.web.controller.masking.model.MaskingUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MaskingUserAdminFacade {

    @Autowired
    private DbMaskingUserService maskingUserService;

    public List<MaskingUserVo> listAll(){
        List<DbMaskingUserEntity> maskingUserList = maskingUserService.listAll();
        return maskingUserList.stream().map(new Function<DbMaskingUserEntity, MaskingUserVo>() {
            @Override
            public MaskingUserVo apply(DbMaskingUserEntity maskingUserEntity) {
                MaskingUserVo maskingUserVo = new MaskingUserVo();
                BeanUtils.copyProperties(maskingUserEntity, maskingUserVo);
                return maskingUserVo;
            }
        }).collect(Collectors.toList());
    }

    public boolean create(MaskingUserCreate maskingUserCreate, UserLoginDto userLoginDto){
        DbMaskingUserEntity dbMaskingUserEntity = new DbMaskingUserEntity();
        BeanUtils.copyProperties(maskingUserCreate, dbMaskingUserEntity);
        dbMaskingUserEntity.setCreateById(userLoginDto.getId().intValue());
        dbMaskingUserEntity.setCreateByName(userLoginDto.getUserName());
        return maskingUserService.create(dbMaskingUserEntity);
    }


}
