<template>
  <div class="login" :style="'background-image:url('+ Background +');'">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="left" label-width="0px"
             class="login-form">
      <h3 class="title">
        勇敢牛牛不怕困难-智能系统
      </h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号"
                  @keyup.enter.native="onSubmit()">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"
                  @keyup.enter.native="onSubmit()">
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input v-model="loginForm.code" auto-complete="off" placeholder="验证码" style="width: 63%">
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon"/>
        </el-input>
        <!--        <div class="login-code">-->
        <!--          <img :src="codeUrl">-->
        <!--        </div>-->
      </el-form-item>
      <el-form-item>
        <el-radio v-model="radio1" label="1" border>医生</el-radio>
        <el-radio v-model="radio1" label="2" border>病人</el-radio>
        <el-radio v-model="radio1" label="3" border>管理员</el-radio>
      </el-form-item>
      <el-form-item style="margin: 0 0 25px 0">
        <span>记住账号？</span>
        <el-switch
            v-model="loginForm.rememberMe"
            active-text="Yes"
            inactive-text="No"
            inactive-color="red">
        </el-switch>
      </el-form-item>


      <el-form-item style="width:100%;">
        <el-button :loading="loading" size="medium" type="primary" style="width:100%;"
                   @click.native.prevent="onSubmit(loginForm)">
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
    <div v-if="$store.state.settings.showFooter" id="el-login-footer">
      <span v-html="$store.state.settings.footerTxt"/>
      <span> ⋅ </span>
      <a href="https://beian.miit.gov.cn/#/Integrated/index" target="_blank">{{ $store.state.settings.caseNumber }}</a>
    </div>
  </div>
</template>

<script>

import Background from '@/assets/background.jpg'
// import {getCodeImg} from '@/api/login'
import Cookies from 'js-cookie'
import Config from '@/settings'
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      codeUrl: '',
      Background: Background,
      loginForm: {
        username: '',
        password: '',
        code: '',
        loading: false,
        rememberMe: true
      },
      loginRules: {
        username: [{required: true, trigger: 'blur', message: '用户名不能为空'}],
        password: [{required: true, trigger: 'blur', message: '密码不能为空'}],
        code: [{required: true, trigger: 'change', message: '验证码不能为空'}]
      },
      loading: false,
      redirect: undefined,
      radio1: '1',
    }
  },
  created() {
    // 获取验证码
    // this.getCode()
    // 获取用户名密码等Cookie
    this.getCookie()
    // token 过期提示
    // this.point()
  },
  methods: {
    // getCode() {
    //   getCodeImg().then(res => {
    //     this.codeUrl = res.img
    //     // this.loginForm.uuid = res.uuid
    //   })
    // },
    getCookie() {
      const username = Cookies.get('username')
      let password = Cookies.get('password')
      const rememberMe = Cookies.get('rememberMe')
      // 保存cookie里面的加密后的密码
      // this.cookiePass = password === undefined ? '' : password
      password = password === undefined ? this.loginForm.password : password
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password,
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
        code: ''
      }
    },
    onSubmit() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          const user = {
            userName: this.loginForm.username,
            userPwd: this.loginForm.password,
            rememberMe: this.loginForm.rememberMe
            // code: this.loginForm.code,
          }
          if (valid) {
            this.loading = true
            if (user.rememberMe) {
              Cookies.set('username', user.userName, {expires: Config.passCookieExpires})
              Cookies.set('password', user.userPwd, {expires: Config.passCookieExpires})
              Cookies.set('rememberMe', user.rememberMe, {expires: Config.passCookieExpires})
            } else {
              Cookies.remove('username')
              Cookies.remove('password')
              Cookies.remove('rememberMe')
            }
            this.$store.dispatch('Login', user).then(() => {
              this.loading = false
              this.$router.push( '/home')
            }).catch(() => {
              this.loading = false
              // this.getCode()
            })
          } else {
            this.$message({
              showClose: true,
              message: '请按要求输入内容'
            });
            return false;
          }
        }
      });
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-size: cover;
}

.title {
  margin: 0 auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  padding: 25px 25px 5px 25px;

  .el-input {
    height: 38px;

    input {
      height: 38px;
    }
  }

  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}

.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}

.login-code {
  width: 33%;
  display: inline-block;
  height: 38px;
  float: right;

  img {
    cursor: pointer;
    vertical-align: middle
  }
}
</style>
