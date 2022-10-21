package com.longmai.datakeeper.service.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longmai.datakeeper.dao.entity.DbMaskingColumnEntity;
import com.longmai.datakeeper.dao.entity.DbMaskingUserEntity;
import com.longmai.datakeeper.dao.entity.DbUserMaskingColumnEntity;
import com.longmai.datakeeper.dao.entity.StructDataSourceEntity;
import com.longmai.datakeeper.dao.mapper.*;
import com.longmai.datakeeper.rest.dto.DBUserMaskingDto;
import com.longmai.datakeeper.rest.param.DBMaskingRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Service
public class DBMaskingService {

    @Autowired
    private DbMaskingUserMapper dbMaskingUserMapper;
    @Autowired
    private DbMaskingColumnMapper dbMaskingColumnMapper;
    @Autowired
    private DbUserMaskingColumnMapper dbUserMaskingColumnMapper;
    @Autowired
    private StructDataSourceMapper structDataSourceMapper;

    public DBUserMaskingDto getUserMaskingColumnDto(DBMaskingRequest maskingRequest){

        QueryWrapper<StructDataSourceEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("ip_host", maskingRequest.getHost());
        queryWrapper.eq("port", maskingRequest.getPort());
        queryWrapper.eq("db_name", maskingRequest.getDbName());
        StructDataSourceEntity dataSourceEntity = structDataSourceMapper.selectOne(queryWrapper);

        DBUserMaskingDto dbUserMaskingDto = new DBUserMaskingDto(maskingRequest.getUser(), maskingRequest.getDbName());
        if (Objects.nonNull(dataSourceEntity)){
            Integer dataSourceId = dataSourceEntity.getId();
            QueryWrapper<DbMaskingUserEntity> maskingUserQuery = new QueryWrapper();
            maskingUserQuery.eq("db_username", maskingRequest.getUser());
            maskingUserQuery.eq("db_source_id", dataSourceId);
            DbMaskingUserEntity dbMaskingUser = dbMaskingUserMapper.selectOne(maskingUserQuery);
            if (Objects.nonNull(dbMaskingUser)){
                QueryWrapper<DbUserMaskingColumnEntity> userMaskingColumnQueryWrapper = new QueryWrapper();
                userMaskingColumnQueryWrapper.eq("db_user_id", dbMaskingUser.getId());
                List<DbUserMaskingColumnEntity> dbUserMaskingColumnList = dbUserMaskingColumnMapper.selectList(userMaskingColumnQueryWrapper);
                if(!CollectionUtils.isEmpty(dbUserMaskingColumnList)){
                    List<Integer> ids = new ArrayList<>();
                    dbUserMaskingColumnList.forEach(new Consumer<DbUserMaskingColumnEntity>() {
                        @Override
                        public void accept(DbUserMaskingColumnEntity dbUserMaskingColumnEntity) {
                            //permLeve>1才需要脱敏
                            if (dbUserMaskingColumnEntity.getPermLevel() > 1){
                                ids.add(dbUserMaskingColumnEntity.getMaskingColumnId());
                            }
                        }
                    });
                    QueryWrapper<DbMaskingColumnEntity> maskingColumnQueryWrapper = new QueryWrapper();
                    maskingColumnQueryWrapper.in("id", ids);
                    List<DbMaskingColumnEntity> dbMaskingColumnList = dbMaskingColumnMapper.selectList(maskingColumnQueryWrapper);
                    dbMaskingColumnList.forEach(new Consumer<DbMaskingColumnEntity>() {
                        @Override
                        public void accept(DbMaskingColumnEntity dbMaskingColumnEntity) {
                            DBUserMaskingDto.MaskingColumnDto maskingColumnDto = new DBUserMaskingDto.MaskingColumnDto();
                            BeanUtils.copyProperties(dbMaskingColumnEntity, maskingColumnDto);
                            dbUserMaskingDto.addMaskingColumn(dbMaskingColumnEntity.getTableName(), maskingColumnDto);
                        }
                    });
                }
            }
        }

        return dbUserMaskingDto;
    }




}
