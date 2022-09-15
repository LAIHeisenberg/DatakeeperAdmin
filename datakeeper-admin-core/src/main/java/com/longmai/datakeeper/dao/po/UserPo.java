package com.longmai.datakeeper.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("dk_user")
public class UserPo {

    private Long id;

    private String userName;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private String password;

    private Integer authMethod;

    private Boolean adminFlag;

    private String dn;

    private String cert;

    private Boolean enabled;

    private Long createTime;

    private Long updateTime;

}
