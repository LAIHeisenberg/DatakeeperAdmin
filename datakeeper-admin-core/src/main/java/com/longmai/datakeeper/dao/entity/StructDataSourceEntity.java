package com.longmai.datakeeper.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;



/**
 * <p>
 * 
 * </p>
 *
 * @author longmai
 * @since 2022-10-20
 */
@TableName("dk_struct_data_source")
@Data
public class StructDataSourceEntity extends BaseEntity  {

    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    private String name;

    private String ipHost;

    private Integer port;

    private String type;

    private String dbName;

    private String userName;

    private String password;

    private String remark;

    private Integer status;

}
