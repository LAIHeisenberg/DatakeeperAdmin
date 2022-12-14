package com.longmai.datakeeper.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author longmai
 * @since 2022-10-21
 */
@TableName("dk_db_masking_column")
@Data
public class DbMaskingColumnEntity extends BaseEntity{

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String columnName;

    private String tableName;

    private String dbName;

    private Integer dbSourceId;

    private Integer maskingLevel;

    private Integer algorithmId;

    private String maskingMethod;

    private String maskingMethodArgs;

    private String dataType;

    private String comment;

}
