import request from '@/utils/request'

export function del(ids) {
    return request({
        url: 'api/admin/sensitive/data/delete',
        method: 'delete',
        data: ids
    })
}

export default {del}
