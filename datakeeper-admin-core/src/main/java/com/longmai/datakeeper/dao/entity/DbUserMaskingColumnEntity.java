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
@TableName("dk_db_user_masking_column")
@Data
public class DbUserMaskingColumnEntity {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    private Integer dbUserId;

    private Integer maskingColumnId;

    private Integer permLevel;

    private Integer algorithmId;

}
