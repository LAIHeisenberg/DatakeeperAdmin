package com.longmai.datakeeper.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longmai.datakeeper.dao.entity.DiscoveryTask;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author longmai
 * @since 2022-11-11
 */
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longmai.datakeeper.dto.DiscoveryTaskDto;

public interface DiscoveryTaskService {

    Page<DiscoveryTaskDto> listPage(DiscoveryTask param, Integer pageNum, Integer pageSize);

    DiscoveryTaskDto getById(Integer id);

    boolean updateStatus(Integer id, DiscoveryTask.Status status);

    boolean create(DiscoveryTask entity);

    boolean deleteById(Integer id);

}
