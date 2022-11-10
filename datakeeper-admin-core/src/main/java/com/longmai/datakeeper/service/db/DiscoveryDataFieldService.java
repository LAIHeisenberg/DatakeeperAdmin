package com.longmai.datakeeper.service.db;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datakeeper.dao.entity.DiscoveryDataField;
import com.longmai.datakeeper.dto.DiscoveryDataFieldDto;

import java.util.List;

public interface DiscoveryDataFieldService {

    Page<DiscoveryDataFieldDto> listPage(DiscoveryDataField param, Integer pageNum, Integer pageSize);

    DiscoveryDataFieldDto getById(Integer id);

    boolean create(DiscoveryDataField entity);

    boolean batchSave(List<DiscoveryDataField> entityList, Integer batchSize);

    boolean deleteById(Integer id);

}