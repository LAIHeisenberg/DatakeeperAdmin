package com.longmai.datakeeper.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String columnName;

    private String tableName;

    private String dbName;

    private Integer dbSourceId;

    private String algorithm;

    private String secretKey;

}
