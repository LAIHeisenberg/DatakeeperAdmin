import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/admin/db/masking/user',
    method: 'post',
    data
  })
}
export function bindColumn(data) {
  return request({
    url: 'api/admin/db/masking/user/bindColumn',
    method: 'post',
    data
  })
}

export default {add,bindColumn}

