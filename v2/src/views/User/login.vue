<template>
<div class="el-card__body">
    <el-card class="box-card">
        <h2>登录</h2>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-position="left" label-width="100px" @keydown.enter.native="submitForm()" class="login-from">

            <el-form-item label="用户名" prop="username">

                <el-input id="username" v-model="ruleForm.username"></el-input>

            </el-form-item>


          <el-form-item label="账户地址" prop="account">

            <el-input id="account" v-model="ruleForm.account"></el-input>

          </el-form-item>


            <el-form-item label="密码" prop="passwd">

                <el-input id="passwd" v-model="ruleForm.passwd" autocomplete="off" type="password"></el-input>

            </el-form-item>

        </el-form>
        <div class="btnGroup">
            <el-button id="submit" round size="medium" type="primary" @click="loginForm()">登录</el-button>
            <el-button @click="resetForm()" size="medium" round>重置</el-button>
            <div class="link"></div>
        </div>
        <a class="link" @click="handleRegister" type="warning">还没有账号？去注册</a>
    </el-card>
</div>
</template>

<script>
import {loginForm, resetForm,handleRegister} from "@/services/UserMethods/LoginMethods";
import LoginMixins from '@/mixins/UserMixins/LoginMixins';
export default {
   mixins:[LoginMixins],
    methods: {
      loginForm,
        resetForm,
        handleRegister,
    },
};
</script>

<style scoped>


.link {
    width: 100%;
    text-align: right;
    display: block;
    color: orange;
    margin-bottom: 10px;
}

.box-card {
  margin: auto auto;
  width: 500px;
  height: 400px;
  text-align: center;
}
.link:hover {
    color: rebeccapurple;
    cursor: pointer;
}
</style>
