package com.longmai.datakeeper.web.controller.masking.facade;

import com.longmai.datakeeper.dao.entity.DbMaskingUserEntity;
import com.longmai.datakeeper.dao.entity.DbUserMaskingColumnEntity;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.service.db.DBMaskingUserService;
import com.longmai.datakeeper.web.controller.masking.model.MaskingUserBindColumn;
import com.longmai.datakeeper.web.controller.masking.model.MaskingUserCreate;
import com.longmai.datakeeper.web.controller.masking.model.MaskingUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MaskingUserAdminFacade {

    @Autowired
    private DBMaskingUserService maskingUserService;

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

    public void bindColumn(MaskingUserBindColumn bindColumn){
        if (Objects.isNull(bindColumn)){
            throw new IllegalArgumentException("未找到绑定的列ID");
        }
        if (CollectionUtils.isEmpty(bindColumn.getColumnId())){
            throw new IllegalArgumentException("未找到绑定的列ID");
        }
        bindColumn.getColumnId().forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer columnId) {
                DbUserMaskingColumnEntity userMaskingColumnEntity = new DbUserMaskingColumnEntity();
                userMaskingColumnEntity.setDbUserId(bindColumn.getUserId());
                userMaskingColumnEntity.setMaskingColumnId(columnId);
                userMaskingColumnEntity.setPermLevel(2);
                userMaskingColumnEntity.setAlgorithmId(1);

                maskingUserService.bindColumn(userMaskingColumnEntity);
            }
        });
    }


}
