package com.longmai.datakeeper.web.controller.masking.model;

import lombok.Data;

import java.util.List;

@Data
public class MaskingUserBindColumn {

    private Integer userId;
    private List<Integer> columnId;

}
