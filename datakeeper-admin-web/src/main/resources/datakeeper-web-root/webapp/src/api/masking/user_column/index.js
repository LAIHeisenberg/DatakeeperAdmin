import request from '@/utils/request'

export function del(ids) {
  return request({
    url: 'api/admin/db/masking/user/column/delete',
    method: 'delete',
    data: ids
  })
}

export default {del}

