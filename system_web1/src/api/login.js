import request from '@/utils/request'
// import {use} from "element-ui";

export function login(userName, userPwd, code) {
  return request({
    url: `/api/user/login`,
    data:{
      userName:userName,
      userPwd:userPwd,
      code:code
    },
    method: 'post'
  })
}

export function getInfo() {
  return request({
    url: 'auth/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: 'auth/logout',
    method: 'delete'
  })
}
