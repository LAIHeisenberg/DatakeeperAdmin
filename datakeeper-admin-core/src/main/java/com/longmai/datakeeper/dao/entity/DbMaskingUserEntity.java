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
@TableName("dk_db_masking_user")
@Data
public class DbMaskingUserEntity extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String realName;

    private Integer dbSourceId;

    private String dbUsername;

    private Integer permLevel;

    private String remark;

}
