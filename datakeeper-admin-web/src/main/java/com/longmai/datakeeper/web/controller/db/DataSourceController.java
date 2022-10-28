package com.longmai.datakeeper.web.controller.db;

import com.longmai.datakeeper.utils.PageUtil;
import com.longmai.datakeeper.vo.StructDataSourceVo;
import com.longmai.datakeeper.web.controller.BaseController;
import com.longmai.datakeeper.web.controller.common.SelectOption;
import com.longmai.datakeeper.web.controller.db.facade.DataSourceFacade;
import com.longmai.datakeeper.web.controller.db.model.DataSourceCreate;
import com.longmai.datakeeper.web.controller.db.model.DataSourceUpdate;
import com.longmai.datakeeper.web.controller.db.model.TableColumnDef;
import com.longmai.datakeeper.web.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/db/datasource")
public class DataSourceController extends BaseController {

    @Autowired
    private DataSourceFacade dataSourceFacade;

    @GetMapping("/list")
    public ResponseEntity<Object> listAllDataSource(){
        List<StructDataSourceVo> structDataSourceVos = dataSourceFacade.listAll();
        Map<String, Object> map = PageUtil.toPage(structDataSourceVos, structDataSourceVos.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/testConnect")
    public Boolean testConnect(@RequestBody DataSourceCreate dataSourceCreate){
        String jdbcUrl = DBUtils.getJdbcUrl(dataSourceCreate.getIpHost(), dataSourceCreate.getPort(), dataSourceCreate.getDbName());
        return DBUtils.testConnection(jdbcUrl, dataSourceCreate.getUserName(), dataSourceCreate.getPassword());
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody DataSourceCreate dataSourceCreate){
        return new ResponseEntity<>(dataSourceFacade.create(dataSourceCreate, getCurrentUser()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody DataSourceUpdate dataSourceUpdate){
        return new ResponseEntity<>(dataSourceFacade.update(dataSourceUpdate, getCurrentUser()), HttpStatus.OK);
    }

    @GetMapping("/listOptions")
    public List<SelectOption> listDataSourceSelectOption(){
        return dataSourceFacade.listDataSourceSelectOption();
    }

    @GetMapping("/introspect/{dataSourceId}/{tableName}")
    public ResponseEntity<Object> introspectTable(@PathVariable("dataSourceId")Integer dataSourceId, @PathVariable("tableName")String tableName){
        List<TableColumnDef> columnDefs = dataSourceFacade.introspectTable(dataSourceId, tableName);
        Map<String, Object> map = PageUtil.toPage(columnDefs, columnDefs.size());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
