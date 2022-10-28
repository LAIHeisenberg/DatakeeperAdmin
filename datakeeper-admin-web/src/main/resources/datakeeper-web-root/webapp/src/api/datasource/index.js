import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/db/datasource',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/users',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/db/datasource',
    method: 'put',
    data
  })
}

export function testConnect(data) {
  return request({
    url: '/api/db/datasource/testConnect',
    method: 'post',
    data
  })
}

export function listOptions() {
  return request({
    url: '/api/db/datasource/listOptions',
    method: 'get'
  })
}

export function introspectTable(dataSourceId,tableName) {
  return request({
    url: '/api/db/datasource/introspect/'+dataSourceId+'/'+tableName,
    method: 'get',
  })
}

export default { add, edit, del, testConnect, listOptions,introspectTable}

