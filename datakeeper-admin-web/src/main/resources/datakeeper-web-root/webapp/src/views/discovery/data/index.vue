<!--  本文件由FreeMarker生成   -->
<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
                <el-input v-model="query.id" clearable size="small" placeholder="输入ID搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.taskId" clearable size="small" placeholder="输入任务ID搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.dataSourceId" clearable size="small" placeholder="输入数据源ID搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.dbName" clearable size="small" placeholder="输入数据库搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.tableName" clearable size="small" placeholder="输入表名搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-select v-model="query.level" placeholder="请选择敏感数据等级">
                    <el-option key="等级1" label="1" value="等级1"></el-option>
                    <el-option key="等级2" label="2" value="等级2"></el-option>
                </el-select>
        <rrOperation />
      </div>
      <crudOperation :permission="permission" />
    </div>
    <!--表单组件-->
    <!--表格渲染-->
    <el-table
      ref="table"
      v-loading="crud.loading"
      lazy
      :data="crud.data"
      @select="crud.selectChange"
      @select-all="crud.selectAllChange"
      @selection-change="crud.selectionChangeHandler"
    >
          <el-table-column type="selection" width="55" />
          <el-table-column label="任务ID" prop="taskId" />
          <el-table-column label="数据源ID" prop="dataSourceId" />
          <el-table-column label="数据库" prop="dbName" />
          <el-table-column label="表名" prop="tableName" />
          <el-table-column label="列名" prop="columnName" />
          <el-table-column label="数据类型" prop="dataType" />
          <el-table-column label="敏感数据等级" prop="level" />
          <el-table-column label="命中规则" prop="matchSensitiveRule" />
          <el-table-column label="命中的文本" prop="matchText" />
          <el-table-column label="创建时间" prop="createTime" />
      <el-table-column label="操作" width="130px" align="center" fixed="right">
        <template slot-scope="scope">
          <udOperation
            :data="scope.row"
            :permission="permission"
            msg="确定删除该条记录?"
          />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import sensitiveDataField from '@/api/sensitive/data/index'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
export default {
  name: 'SensitiveDataField',
  components: {crudOperation, rrOperation, udOperation },
  cruds(){
    return CRUD({ title: '敏感数据', url: 'api/admin/sensitive/data/list', crudMethod: { ...sensitiveDataField }})
  },
  mixins: [presenter(), header(), crud()],
  data() {
    return {
      permission: {
        add: ['admin' ],
        edit: ['admin'],
        del: ['admin']
      },
    }
  },
  methods: {

  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
 ::v-deep .el-input-number .el-input__inner {
    text-align: left;
  }
</style>
