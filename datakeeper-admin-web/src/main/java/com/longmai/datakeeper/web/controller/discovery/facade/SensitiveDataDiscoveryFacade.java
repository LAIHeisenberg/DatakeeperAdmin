package com.longmai.datakeeper.web.controller.discovery.facade;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datadiscovery.recognizer.RecognizerFactory;
import com.longmai.datadiscovery.recognizer.RecognizerGroup;
import com.longmai.datadiscovery.recognizer.RecognizerResult;
import com.longmai.datakeeper.dao.entity.DiscoveryDataField;
import com.longmai.datakeeper.dao.entity.DiscoveryTask;
import com.longmai.datakeeper.dao.entity.StructDataSourceEntity;
import com.longmai.datakeeper.dto.DiscoveryDataFieldDto;
import com.longmai.datakeeper.dto.DiscoveryTaskDto;
import com.longmai.datakeeper.service.db.DiscoveryDataFieldService;
import com.longmai.datakeeper.service.db.DiscoveryTaskService;
import com.longmai.datakeeper.service.db.StructDataSourceService;
import com.longmai.datakeeper.utils.PageUtil;
import com.longmai.datakeeper.web.controller.discovery.model.DiscoveryDataFieldQuery;
import com.longmai.datakeeper.web.controller.discovery.model.DiscoveryDataFieldView;
import com.longmai.datakeeper.web.utils.DBUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SensitiveDataDiscoveryFacade {

    @Autowired
    private StructDataSourceService structDataSourceService;

    @Autowired
    private DiscoveryTaskService discoveryTaskService;

    @Autowired
    private DiscoveryDataFieldService serviceImpl;

    public Map<String,Object> listPage(DiscoveryDataFieldQuery queryParam, Integer pageNum, Integer pageSize){
        DiscoveryDataField queryEntity = new DiscoveryDataField();
        BeanUtils.copyProperties(queryParam, queryEntity);
        Page<DiscoveryDataFieldDto> page = serviceImpl.listPage(queryEntity, pageNum, pageSize);
        List<DiscoveryDataFieldView> view = convertView(page.getRecords());
        return PageUtil.toPage(view, page.getTotal());
    }


    public boolean delete(Integer id){
        return serviceImpl.deleteById(id);
    }

    private List<DiscoveryDataFieldView> convertView(List<DiscoveryDataFieldDto> dtoList){
        if (CollectionUtils.isEmpty(dtoList)){
            return Collections.emptyList();
        }
        return dtoList.stream().map(dto -> {
            DiscoveryDataFieldView view = new DiscoveryDataFieldView();
            BeanUtils.copyProperties(dto, view);
            return view;
        }).collect(Collectors.toList());
    }

    public void discoveryDatabase(Integer taskId){
        DiscoveryTaskDto taskDto = discoveryTaskService.getById(taskId);
        if (Objects.isNull(taskDto)){
            throw new IllegalArgumentException("未查找到任务");
        }
        Integer dataSourceId = taskDto.getDataSourceId();
        Integer beginScanLine = taskDto.getBeginScanLine();
        Integer endScanLine = taskDto.getEndScanLine();
        RecognizerGroup recognizerGroup = RecognizerGroup.valueOf(taskDto.getRule());
        if (Objects.isNull(recognizerGroup)){
            throw new IllegalArgumentException("无效的识别规则");
        }
        StructDataSourceEntity dataSource = structDataSourceService.getDataSourceEntityById(dataSourceId);
        if (Objects.isNull(dataSource)){
            return;
        }
        String dbName = dataSource.getDbName();
        String jdbcUrl = DBUtils.getJdbcUrl(dataSource.getIpHost(), dataSource.getPort(), dataSource.getDbName());
        List<DiscoveryDataField> list = new ArrayList<>();
        try {
            List<String> tables = DBUtils.introspectDataBase(jdbcUrl, dbName, dataSource.getUserName(), dataSource.getPassword());
            for (String tbl : tables){
                DBUtils.TableResult tableResult = DBUtils.fetchRows(jdbcUrl, tbl, dataSource.getUserName(), dataSource.getPassword(),  beginScanLine, (endScanLine - beginScanLine));
                List<DBUtils.TableResult.Row> rows = tableResult.getRows();
                for (DBUtils.TableResult.Row row : rows){
                    List<DBUtils.TableResult.Column> columns = row.getColumns();
                    for (DBUtils.TableResult.Column col : columns){
                        List<RecognizerResult> recognizerResults = RecognizerFactory.recognizer(col.getValue(), recognizerGroup);
                        for (RecognizerResult result : recognizerResults){
                            DiscoveryDataField discoveryDataField = new DiscoveryDataField();
                            discoveryDataField.setDataSourceId(dataSourceId);
                            discoveryDataField.setTaskId(taskId);
                            discoveryDataField.setDbName(dbName);
                            discoveryDataField.setTableName(tableResult.getTableName());
                            discoveryDataField.setColumnName(col.getLabelName());
                            discoveryDataField.setDataType(col.getDataType());
                            discoveryDataField.setLevel(1);
                            discoveryDataField.setMatchSensitiveRule(result.getDesc());
                            discoveryDataField.setMatchText(result.getTarget());
                            discoveryDataField.setCreateTime(new Date());
                            list.add(discoveryDataField);
                        }
                    }
                }
            }
            serviceImpl.batchSave(list, 100);
            discoveryTaskService.updateStatus(taskId, DiscoveryTask.Status.DONE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
