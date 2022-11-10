<!--  本文件由FreeMarker生成   -->
<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
                <el-input v-model="query.taskName" clearable size="small" placeholder="输入任务名称搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.rule" clearable size="small" placeholder="输入识别规则搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.mode" clearable size="small" placeholder="输入识别模式搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.period" clearable size="small" placeholder="输入识别周期搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.riskLevel" clearable size="small" placeholder="输入风险等级搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.status" clearable size="small" placeholder="输入状态搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation />
      </div>
      <crudOperation :permission="permission" />
    </div>
    <!--表单组件-->
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
      <el-form ref="form" inline :model="form" size="small" label-width="80px">
              <el-form-item label="任务名称" prop="taskName">
                <el-input v-model="form.taskName" style="width: 370px;" />
              </el-form-item>
              <el-form-item label="数据源" prop="dataSourceId">
                <el-select v-model="form.dataSourceId" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="识别规则" prop="rule">
                <el-select v-model="form.rule" placeholder="请选择">
                  <el-option key="ALL" label="全部规则" value="ALL"></el-option>
                  <el-option key="GROUP_1" label="分组1" value="GROUP_1"></el-option>
                  <el-option key="GROUP_2" label="分组2" value="GROUP_2"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="识别周期" prop="period">
                <el-radio-group v-model="form.period">
                  <el-radio label="1" >单次</el-radio>
                  <el-radio label="2" >每天</el-radio>
                  <el-radio label="3" >每周</el-radio>
                  <el-radio label="4" >每月</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="扫描行数" prop="scanLine">
                <el-input v-model="form.scanLine" style="width: 370px;" />
              </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
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
          <el-table-column label="id" prop="id" />
          <el-table-column label="任务名称" prop="taskName" />
          <el-table-column label="数据库名" prop="dbName" />
          <el-table-column label="识别规则" prop="rule" />
          <el-table-column label="识别周期" prop="period" />
          <el-table-column label="状态" prop="status" />
          <el-table-column label="创建人" prop="createByName" />
          <el-table-column label="创建时间" prop="createTime" />
          <el-table-column label="操作" width="130px" align="center" fixed="right">
            <template slot-scope="scope">
              <el-popover v-model="pop" placement="top" width="180" trigger="manual">
                <p>要现在执行任务吗?</p>
                <div style="text-align: right; margin: 0">
                  <el-button size="mini" type="text" @click="doCancel">取消</el-button>
                  <el-button type="primary" size="mini" @click="doExecuteTask(scope.row.id)">确定</el-button>
                </div>
                <el-button slot="reference" type="success" @click="toExecuteTask" >执行</el-button>
              </el-popover>
            </template>
          </el-table-column>
    </el-table>
  </div>
</template>

<script>
import discoveryTask from '@/api/discover/task/index'
import dataSource from '@/api/datasource/index'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'

const defaultForm = {
          taskName : null ,
          dataSourceId: null,
          rule : null ,
          mode : null ,
          period : null ,
          scanLine : null,

        }
export default {
  name: 'DiscoveryTask',
  components: {crudOperation, rrOperation, udOperation },
  cruds(){
    return CRUD({ title: '任务', url: 'api/admin/discovery/task/list', crudMethod: { ...discoveryTask }})
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  created() {
    const parentThis = this
    dataSource.listOptions().then(function(resp) {
      parentThis.options = resp 
    })
  },
  data() {
    return {
      options: [],
      permission: {
        add: ['admin' ],
        edit: ['admin'],
        del: ['admin']
      },
      pop: false,
    }
  },
  methods: {
    doExecuteTask(taskId){
      const parentThis = this
      discoveryTask.executeTask(taskId).then(function(resp){
        parentThis.$notify({
            title: '成功',
            message: '执行完成',
            type: 'success'
          })
      })
      this.pop = false
      crud.toQuery()
    },
    doCancel() {
      this.pop = false
    },
    toExecuteTask() {
      this.pop = true
    },
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
 ::v-deep .el-input-number .el-input__inner {
    text-align: left;
  }
</style>
