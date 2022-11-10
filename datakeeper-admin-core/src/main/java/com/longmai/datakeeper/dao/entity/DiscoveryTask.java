package com.longmai.datakeeper.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author longmai
 * @since 2022-11-11
 */
@TableName("dk_discovery_task")
@Data
public class DiscoveryTask {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String taskName;

    private Integer dataSourceId;

    private String dbName;

    private String rule;

    private Integer mode;

    private Integer period;

    private Integer riskLevel;

    private Integer beginScanLine;

    private Integer endScanLine;

    private Integer status;

    private Integer createById;

    private String createByName;

    private Date createTime;

    private Integer updateById;

    private String updateByName;

    private Date updateTime;


    public enum Status{

        NEW(1,"新建"),
        DONE(10, "完成"),
        ;

        Status(Integer value, String desc){
            this.value = value;
            this.desc = desc;
        }
        private Integer value;
        private String desc;

        public static String getDesc(Integer value){
            for (Status status : Status.values()){
                if (status.value == value){
                    return status.desc;
                }
            }
            return null;
        }

        public Integer getValue(){
            return value;
        }
    }

    public enum Period {

        SINGLE(1,"单次"),
        EVERY_DAY(2,"每天"),
        EVERY_WEEK(3, "每周"),
        EVERY_MONTH(4, "每月"),
        ;

        Period(Integer value, String desc){
            this.value = value;
            this.desc = desc;
        }

        private Integer value;
        private String desc;

        public static String getDesc(Integer value){
            for (Period period : Period.values()){
                if (period.value == value){
                    return period.desc;
                }
            }
            return null;
        }
    }

}
