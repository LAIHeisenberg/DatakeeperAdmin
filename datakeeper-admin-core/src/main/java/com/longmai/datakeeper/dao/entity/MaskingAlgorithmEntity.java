package com.longmai.datakeeper.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author longmai
 * @since 2022-10-21
 */
@TableName("dk_masking_algorithm")
@Data
public class MaskingAlgorithmEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String category;

    private String algorithm;

    @Override
    public String toString() {
        return "MaskingAlgorithm{" +
            "id=" + id +
            ", category=" + category +
            ", algorithm=" + algorithm +
        "}";
    }
}
