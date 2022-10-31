import request from '@/utils/request'

export function create(data) {
  return request({
    url: 'api/admin/db/masking/create',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/admin/db/masking/delete',
    method: 'delete',
    data: ids
  })
}

export function listBy(params) {
  return request({
    url: 'api/admin/db/masking/listBy',
    method: 'get',
    params
  })
}

export default {create,del,listBy}

