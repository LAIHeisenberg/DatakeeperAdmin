package com.longmai.datakeeper.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author longmai
 * @since 2022-11-10
 */
@TableName("dk_discovery_data_field")
@Data
public class DiscoveryDataField implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer taskId;

    private Integer dataSourceId;

    private String dbName;

    private Integer level;

    private String tableName;

    private String columnName;

    private String dataType;

    private String matchSensitiveRule;

    private String matchText;

    private Date createTime;

    private Date updateTime;

}
