<template>
<div>
    <el-card class="box-card">
        <h2>注册</h2>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-position="left" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
          <el-form-item label="账户地址" prop="account">
            <el-input v-model="ruleForm.account"></el-input>
          </el-form-item>
            <el-form-item label="密码" prop="passwd">
                <el-input type="password" v-model="ruleForm.passwd" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="password">
                <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="输入验证码">
                <div class="emilStyle">
                    <el-input v-model="ruleForm.emailMessage"> </el-input>
                    <el-button type="primary" @click="handleSendEmail" size="mini" round>发送验证🐎</el-button>
                </div>
            </el-form-item>
        </el-form>
        <div class="btnGroup">
            <el-button type="primary" @click="registerForm('ruleForm')" size="medium" round>提交</el-button>
            <el-button @click="resetForm('ruleForm')" size="medium" round>重置</el-button>
            <el-button @click="goBack" size="medium" round>返回</el-button>
        </div>
    </el-card>
</div>
</template>

<script>
import {registerForm,resetForm,goBack,handleSendEmail} from '@/services/UserMethods/RegisterMethod'
import RegisterMixins from '@/mixins/UserMixins/RegisterMixins'
export default {
 mixins:[RegisterMixins],
  methods: {
    registerForm,
    resetForm,
    goBack,
    handleSendEmail,
  },
}
</script>

<style scoped>
.el-card {
    width: 500px;
    height: 400px;
    margin: 0 auto;
    text-align: center;
}

.box-card {
  width: 500px;
    height: 500px;
    margin: 0 auto;
    text-align: center;
}

.login-from {
    margin: auto auto;
}

.emilStyle {
    display: flex;
}

.el-button {
    width: 90px;
}

.el-input {
    margin-right: 20px;
}

.btnGroup {
    display: flex;
    justify-content: center;

}
</style>
