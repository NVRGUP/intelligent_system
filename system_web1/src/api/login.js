import request from '@/utils/request'
import {use} from "element-ui";

export function login(userName, userPwd) {
  console.log(userName,userPwd)
  return request({
    url: `/api/user/login?userName=${userName}&userPwd=${userPwd}`,
    method: 'GET',
  })
}

export function getInfo() {
  return request({
    url: 'auth/info',
    method: 'get'
  })
}

export function getCodeImg() {
  return request({
    url: 'user/code',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: 'auth/logout',
    method: 'delete'
  })
}
