package com.longmai.datakeeper.dao.entity;

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
@TableName("dk_struct_data_source")
@Data
public class StructDataSourceEntity extends BaseEntity  {

    private Integer id;

    private String ipHost;

    private Integer port;

    private String type;

    private String dbName;

    private String remark;

    @Override
    public String toString() {
        return "StructDataSource{" +
            "id=" + id +
            ", ipHost=" + ipHost +
            ", port=" + port +
            ", type=" + type +
            ", dbName=" + dbName +
            ", remark=" + remark +
            ", createById=" + createById +
            ", createByName=" + createByName +
            ", updateById=" + updateById +
            ", updateByName=" + updateByName +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
