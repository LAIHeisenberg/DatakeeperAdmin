<template>
  <div class="app-container">
    <div class="head-container">
      <crudOperation show="" :permission="permission" />
    </div>
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="570px">
      <el-form ref="form" :inline="true" :model="form" size="small" label-width="120px">
        <el-form-item label="Host" prop="ipHost">
          <el-row>
            <el-col>
              <el-input v-model="form.ipHost" />
            </el-col>
            <el-col>
              <el-button type="success" @click="testConnection">测试连接</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="端口号" prop="port">
          <el-input v-model="form.port" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-input v-model="form.type" />
        </el-form-item>
        <el-form-item label="数据库名" prop="dbName">
          <el-input v-model="form.dbName" />
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="crud.cancelCU">取消</el-button>
        <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
      </div>
    </el-dialog>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="ipHost" label="Host" />
      <el-table-column prop="port" label="端口号" />
      <el-table-column prop="type" label="类型" />
      <el-table-column prop="dbName" label="数据库名" />
      <el-table-column prop="userName" label="用户名" />
      <el-table-column prop="remark" label="备注" />
      <el-table-column prop="createTimeStr" label="创建时间" />
      <el-table-column label="操作" width="70px" fixed="right">
        <template slot-scope="scope">
          <udOperation
            :data="scope.row"
            :permission="permission"
            msg="确定要删除该模板吗?"
          />
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <pagination />
  </div>
</template>

<script>
import dataSource from '@/api/datasource/index'
import CRUD, { presenter, header, crud, form } from '@crud/crud'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import datasource from '../../api/datasource'
const defaultForm = {host:null,port:null,type:null,dbName:null,userName:null,password:null,remark:null}
export default {
  name: 'dataSource',
  components: { pagination, crudOperation, udOperation },
  cruds() {
    return CRUD({ url: '/api/db/datasource/list', crudMethod: { ...dataSource }, title: '数据源' })
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin'],
        edit: ['admin'],
        del: ['admin']
      }
    }
  },
  created() {
    this.crud.optShow = {
      add: true
    }
  },
  methods: {
    testConnection(){
      const parentThis = this
      datasource.testConnect(this.form).then(function(resp){
        if(resp){
          parentThis.$notify({
            title: '成功',
            message: '测试连接成功',
            type: 'success'
          })
        }else{
          parentThis.$notify({
            title: '失败',
            message: '测试连接失败',
            type: 'warning'
          })
        }
      })
    }
  }
}
</script>
