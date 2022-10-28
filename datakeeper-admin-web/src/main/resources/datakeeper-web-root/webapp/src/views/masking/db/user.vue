<template>
  <div class="app-container">
    <div class="head-container">
      <crudOperation show="" :permission="permission" />
    </div>
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="570px">
      <el-form ref="form" :inline="true" :model="form" size="small" label-width="120px">
        <el-form-item label="真实姓名" prop="realName">
          <el-input v-model="form.realName" />
        </el-form-item>
        <el-form-item label="数据库用户名" prop="dbUsername">
          <el-input v-model="form.dbUsername" />
        </el-form-item>
        <el-form-item label="数据源" prop="dataSourceId">
          <el-select v-model="form.dbSourceId" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="脱敏等级" prop="permLevel">
          <el-select v-model="form.permLevel" placeholder="请选择">
            <el-option label="等级1" value="1"></el-option>
            <el-option label="等级2" value="2"></el-option>
          </el-select>
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


    <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="bindColumnDialogShow" :title="关联脱敏列" width="720px">
      <el-form ref="form" :inline="true" :model="bindColumnForm" label-width="120px">
          
        <el-row>
          <el-col span="6">
            <el-form-item label="数据源" prop="dataSourceId">
              <el-select v-model="bindColumnForm.dataSourceId" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="表名" prop="tableName">
              <el-input v-model="bindColumnForm.tableName" />
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-button type="text" @click="doSearch">查询</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-table  v-loading="crud.loading" :data="bindColumnForm.maskColumnList" style="width: 100%;" >
            <el-table-column type="selection" width="55" />
            <el-table-column prop="tableName" label="表名" />
            <el-table-column prop="columnName" label="列名" />
            <el-table-column prop="dataType" label="类型" />
            <el-table-column prop="maskingLevel" label="脱敏等级" />
            <el-table-column prop="maskingMethod" label="脱敏方法" />
            <el-table-column prop="maskingMethodArgs" label="脱敏方法参数" />
            <el-table-column prop="comment" label="备注" />
          </el-table>
        </el-row>
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
      <el-table-column prop="realName" label="真实姓名" />
      <el-table-column prop="dbUsername" label="数据库用户名" />
      <el-table-column prop="dbSourceId" label="数据源" />
      <el-table-column prop="permLevel" label="脱敏等级" >
        <template slot-scope="scope">
          <el-select v-model="scope.row.permLevel"  disabled placeholder="请选择">
            <el-option label="等级1" value="等级1"></el-option>
            <el-option label="等级2" value="等级2"></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" />
      <el-table-column label="操作" width="70px" fixed="right">
        <template slot-scope="scope">
          <el-button @click="showBindColumnDialog">关联脱敏列</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <pagination />
  </div>
</template>

<script>
import dataSource from '@/api/datasource/index'
import maskUserOps from '@/api/masking/user/index'
import CRUD, { presenter, header, crud, form } from '@crud/crud'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {realName:null,dbUsername:null,dataSourceId:null,permLevel:null,remark:null}
export default {
  name: 'MaskingUserList',
  components: { pagination, crudOperation, udOperation },
  cruds() {
    return CRUD({ url: '/api/admin/db/masking/user/list', crudMethod: { ...maskUserOps }, title: '脱敏用户' })
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      bindColumnDialogShow : false,
      bindColumnForm : {
        dataSourceId :  null,
        tableName : null,
        userId : null,
        maskColumnList : null
      },
      permission: {
        add: ['admin'],
        edit: ['admin'],
        del: ['admin']
      },
      options: [],
    }
  },
  created() {
    this.crud.optShow = {
      add: true
    }
    const parentThis = this
    dataSource.listOptions().then(function(resp) {
      parentThis.options = resp 
    })
  },
  methods: {
    showBindColumnDialog(){
      this.showBindColumnDialog = true
      // this.bindColumnForm.userId = id
    },
    doSearch(){
      const dataSourceId = this.bindColumnForm.dataSourceId
      const tableName = this.bindColumnForm.tableName
      const param = new Object()
      param.dataSourceId = dataSourceId
      param.tableName = tableName
      const parentThis = this
      maskUserOps.listBy(param).then(function(resp){
        parentThis.bindColumnForm.maskColumnList = resp
        console.log(resp)
      })
    }

  }
}
</script>
