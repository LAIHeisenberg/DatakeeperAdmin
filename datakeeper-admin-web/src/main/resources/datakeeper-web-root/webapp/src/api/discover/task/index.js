import request from '@/utils/request'

export function add(data) {
    return request({
        url: 'api/admin/discovery/task/create',
        method: 'post',
        data
    })
}

export function del(ids) {
    return request({
        url: 'api/admin/discovery/task/delete',
        method: 'delete',
        data: ids
    })
}

export function executeTask(id){
    return request({
        url: 'api/admin/discovery/task/execute/'+id,
        method: 'get',
    })
}

export default {add,del,executeTask}
