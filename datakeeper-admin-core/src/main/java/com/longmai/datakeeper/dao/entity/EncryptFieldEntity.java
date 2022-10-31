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
 * @since 2022-10-20
 */
@TableName("dk_encrypt_field")
@Data
public class EncryptFieldEntity extends BaseEntity  {


    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    private String columnName;

    private String tableName;

    private String dbName;

    private Integer dbSourceId;

    private String algorithm;

    private String secretKey;

    private String dataType;

    private String comment;

}
