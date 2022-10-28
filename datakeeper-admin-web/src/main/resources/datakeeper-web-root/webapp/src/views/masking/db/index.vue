<template>
  <div class="app-container">
    <div class="head-container">
      <crudOperation show="" :permission="permission" />
    </div>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="crud.data" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="dbName" label="数据库" />
      <el-table-column prop="tableName" label="表名" />
      <el-table-column prop="columnName" label="列名" />
      <el-table-column prop="dataType" label="类型" />
      <el-table-column prop="maskingLevel" label="脱敏等级" />
      <el-table-column prop="maskingMethod" label="脱敏方法" />
      <el-table-column prop="maskingMethodArgs" label="脱敏方法参数" />
      <el-table-column prop="comment" label="备注" />
      <el-table-column label="操作" width="70px" fixed="right">
        <template slot-scope="scope">
          <udOperation
            :data="scope.row"
            :permission="permission"
            msg="确定要删除该脱敏列吗?"
          />
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <pagination />
  </div>
</template>

<script>
import maskingOps from '@/api/masking/db/index'
import CRUD, { presenter, header, crud, form } from '@crud/crud'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {host:null,port:null,type:null,dbName:null,userName:null,password:null,remark:null}
export default {
  name: 'maskingColumnList',
  components: { pagination, crudOperation, udOperation },
  cruds() {
    return CRUD({ url: '/api/admin/db/masking/list', crudMethod: { ...maskingOps }, title: '加密列字段' })
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  data() {
    return {
      permission: {
        add: ['admin'],
        edit: ['no-edit'],
        del: ['admin']
      }
    }
  },
  created() {
    this.crud.optShow = {
      add: false
    }
  },
  methods: {

  }
}
</script>
