import request from '@/utils/request'

export function create(data) {
  return request({
    url: 'api/admin/encrypt/create',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/admin/encrypt/delete',
    method: 'delete',
    data: ids
  })
}


export default {create,del}

