<template>
  <div class="app-container">
    <div class="head-container">
      <el-form ref="form" :inline="true" :model="introspectTableform" size="small" label-width="90px">
        <el-row>
          <el-col :span="6">
            <el-form-item label="数据源" prop="dataSourceId">
              <el-select v-model="introspectTableform.dataSourceId" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="表名" prop="tableName">
              <el-input v-model="introspectTableform.tableName" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-button type="success" @click="introSpecTable">Introspect</el-button>
            <el-button type="primary" @click="applyMasking">提交</el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <!--表格渲染-->
    <el-table ref="table" v-loading="crud.loading" :data="columnDefList" style="width: 100%;" >
      <el-table-column prop="tableName" label="表名" />
      <el-table-column prop="columnName" label="列名" />
      <el-table-column prop="dataType" label="数据类型" />
      <el-table-column prop="comment" label="说明注释" />
      <el-table-column prop="maskingMethod" label="脱敏方法" >
        <template slot-scope="scope">
          <el-select v-model="scope.row.maskingMethod" placeholder="请选择" >
            <el-option value='shieldMasking' label="ShieldMasking"></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="maskingMethodArgs" label="脱敏方法参数" >
        <template slot-scope="scope">
          <el-input v-model="scope.row.maskingMethodArgs" />
        </template>
      </el-table-column>
      <el-table-column prop="maskingLevel" label="脱敏等级" >
        <template slot-scope="scope">
          <el-select v-model="scope.row.maskingLevel"  placeholder="请选择">
            <el-option label="等级1" value="1"></el-option>
            <el-option label="等级2" value="2"></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="selected" label="是否脱敏" >
        <template slot-scope="scope">
          <el-select v-model="scope.row.selected" placeholder="请选择" >
            <el-option :value=true label="是"></el-option>
            <el-option :value=false label="否"></el-option>
          </el-select>
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <pagination />
  </div>
</template>

<script>
import dataSource from '@/api/datasource/index'
import dbMaskingOps from '@/api/masking/db/index'
import CRUD, { presenter, header, crud, form } from '@crud/crud'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
const defaultForm = {name:null,host:null,port:null,type:null,dbName:null,userName:null,password:null,remark:null}
export default {
  name: 'maskingIntroSpecTable',
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
      },
      options: [],
      introspectTableform : {
        dataSourceId : null,
        tableName : null
      },
      columnDefList : []
      
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
    introSpecTable(){
      const parentThis = this
      dataSource.introspectTable(this.introspectTableform.dataSourceId, this.introspectTableform.tableName).then(function(resp){
        parentThis.columnDefList = resp.content
      })
    },
    applyMasking(){

      const obj = new Object()
      obj.dataSourceId = this.introspectTableform.dataSourceId
      obj.tableName = this.introspectTableform.tableName
      obj.maskingColumns = this.columnDefList

      dbMaskingOps.create(obj).then(function(resp){

      })
    }
  }
}
</script>
