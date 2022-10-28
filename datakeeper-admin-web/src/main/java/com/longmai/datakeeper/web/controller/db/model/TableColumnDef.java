package com.longmai.datakeeper.web.controller.db.model;

import com.longmai.datakeeper.web.utils.DBUtils;
import lombok.Data;

@Data
public class TableColumnDef extends DBUtils.ColumnDef {
    private boolean selected;
}
