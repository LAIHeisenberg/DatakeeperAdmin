<!--  本文件由FreeMarker生成   -->
<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
                <el-input v-model="query.host" clearable size="small" placeholder="输入HOST搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-input v-model="query.path" clearable size="small" placeholder="输入路径搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
                <el-select v-model="query.retType" placeholder="请选择返回类型">
                    <el-option key="1" label="json" value="1"></el-option>
                    <el-option key="2" label="xml" value="2"></el-option>
                </el-select>
        <rrOperation />
      </div>
      <crudOperation :permission="permission" />
    </div>
    <!--表单组件-->
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
      <el-form ref="form" inline :model="form" size="small" label-width="80px">
              <el-form-item label="HOST" prop="host">
                <el-input v-model="form.host" style="width: 370px;" />
              </el-form-item>
              <el-form-item label="路径" prop="path">
                <el-input v-model="form.path" style="width: 370px;" />
              </el-form-item>
              <el-form-item label="请求URL" prop="apiUrl">
                <el-input v-model="form.apiUrl" style="width: 370px;" />
              </el-form-item>
              <el-form-item label="返回类型" prop="retType">
                <el-select v-model="form.retType" placeholder="返回类型">
                    <el-option key="1" label="json" value="1"></el-option>
                    <el-option key="2" label="xml" value="2"></el-option>
                </el-select>
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
          <el-table-column type="selection" width="55" />
          <el-table-column label="HOST" prop="host" />
          <el-table-column label="路径" prop="path" />
          <el-table-column label="请求URL" prop="apiUrl" />
          <el-table-column label="返回类型" prop="retType" />
          <el-table-column label="模板ID" prop="templateId" />
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
import apiMasking from '@/api/masking/api/index'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'

const defaultForm = {
          host : null ,
          path : null ,
          apiUrl : null ,
          retType : null }
export default {
  name: 'ApiMasking',
  components: {crudOperation, rrOperation, udOperation },
  cruds(){
    return CRUD({ title: '脱敏API', url: 'api/admin/api/masking/list', crudMethod: { ...apiMasking }})
  },
  mixins: [presenter(), header(), form(defaultForm), crud()],
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