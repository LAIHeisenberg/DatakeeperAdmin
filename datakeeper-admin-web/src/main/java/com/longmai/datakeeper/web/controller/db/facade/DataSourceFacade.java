package com.longmai.datakeeper.web.controller.db.facade;

import com.longmai.datakeeper.dao.entity.StructDataSourceEntity;
import com.longmai.datakeeper.dto.UserLoginDto;
import com.longmai.datakeeper.service.db.StructDataSourceService;
import com.longmai.datakeeper.vo.StructDataSourceVo;
import com.longmai.datakeeper.web.controller.common.SelectOption;
import com.longmai.datakeeper.web.controller.db.model.DataSourceCreate;
import com.longmai.datakeeper.web.controller.db.model.DataSourceUpdate;
import com.longmai.datakeeper.web.controller.db.model.TableColumnDef;
import com.longmai.datakeeper.web.utils.DBUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataSourceFacade {

    @Autowired
    private StructDataSourceService structDataSourceService;

    public List<StructDataSourceVo> listAll(){
       return structDataSourceService.listAll();
    }

    public boolean create(DataSourceCreate dataSourceCreate, UserLoginDto currentUser){
        StructDataSourceEntity dataSourceEntity = new StructDataSourceEntity();

        BeanUtils.copyProperties(dataSourceCreate,dataSourceEntity);
        dataSourceEntity.setCreateById(currentUser.getId().intValue());
        dataSourceEntity.setUserName(currentUser.getUserName());
        return structDataSourceService.create(dataSourceEntity);
    }

    public boolean update(DataSourceUpdate dataSourceUpdate, UserLoginDto currentUser){
        StructDataSourceEntity dataSourceEntity = new StructDataSourceEntity();
        BeanUtils.copyProperties(dataSourceUpdate,dataSourceEntity);
        dataSourceEntity.setCreateById(currentUser.getId().intValue());
        dataSourceEntity.setUserName(currentUser.getUserName());
        return structDataSourceService.update(dataSourceEntity);
    }

    public List<TableColumnDef> introspectTable(Integer dataSourceId, String tableName){
        StructDataSourceEntity dataSourceEntity = structDataSourceService.getDataSourceEntityById(dataSourceId);
        if (Objects.isNull(dataSourceEntity)){
            return Collections.emptyList();
        }
        String ipHost = dataSourceEntity.getIpHost();
        Integer port = dataSourceEntity.getPort();
        String dbName = dataSourceEntity.getDbName();
        String userName = dataSourceEntity.getUserName();
        String password = dataSourceEntity.getPassword();
        List<DBUtils.ColumnDef> columnDefs = DBUtils.introspectTable(DBUtils.getJdbcUrl(ipHost, port, dbName), userName, password, tableName);
        return columnDefs.stream().map(new Function<DBUtils.ColumnDef, TableColumnDef>() {
            @Override
            public TableColumnDef apply(DBUtils.ColumnDef columnDef) {
                TableColumnDef tableColumnDef = new TableColumnDef();
                BeanUtils.copyProperties(columnDef,tableColumnDef);
                return tableColumnDef;
            }
        }).collect(Collectors.toList());
    }

    public List<SelectOption> listDataSourceSelectOption(){
        List<StructDataSourceVo> list = listAll();
        if (!CollectionUtils.isEmpty(list)){
            return list.stream().map(new Function<StructDataSourceVo, SelectOption>() {
                @Override
                public SelectOption apply(StructDataSourceVo structDataSourceVo) {
                    SelectOption selectOption = new SelectOption();
                    selectOption.setLabel(structDataSourceVo.getName());
                    selectOption.setValue(String.valueOf(structDataSourceVo.getId()));
                    return selectOption;
                }
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }



}
