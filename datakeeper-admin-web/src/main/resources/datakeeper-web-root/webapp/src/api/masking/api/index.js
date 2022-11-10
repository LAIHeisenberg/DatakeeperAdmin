
import request from '@/utils/request'

export function add(data) {
    return request({
        url: 'api/admin/api/masking/create',
        method: 'post',
        data
    })
}

export function del(ids) {
    return request({
        url: 'api/admin/api/masking/delete',
        method: 'delete',
        data: ids
    })
}

export default {add,del}
