<template>
<div class="el-card__body">
    <el-card class="box-card">
        <h2>登录</h2>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-position="left" label-width="100px" @keydown.enter.native="submitForm()" class="login-from">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
          <el-form-item label="账户地址" prop="account">
            <el-input v-model="ruleForm.account"></el-input>
          </el-form-item>
            <el-form-item label="密码" prop="passwd">
                <el-input type="password" v-model="ruleForm.passwd" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div class="btnGroup">
            <el-button type="primary" @click="submitForm()" size="medium" round>登录</el-button>
            <el-button @click="resetForm()" size="medium" round>重置</el-button>
            <div class="link"></div>
        </div>
        <a class="link" @click="handleRegister" type="warning">还没有账号？去注册</a>
    </el-card>
</div>
</template>

<script>
import {
    login
} from "@/api/home";
export default {
    data() {
        return {
            ruleForm: {
              username: "",
              account:"",
              passwd: "",
            },
            rules: {
              username: [{
                    required: true,
                    message: "用户名不能为空！",
                    trigger: "blur"
                }],
              account:[{
                required:true,
                message:"请输入账户地址",
                trigger:"blur"
              },
                {
                pattern: /^0x[a-fA-F0-9]{40}$/,
                message: "密码必须以 '0x' 开头，且长度必须为 42 位！",
                trigger: "blur"
              }
              ],
              passwd: [{
                    required: true,
                    message: "密码不能为空！",
                    trigger: "blur"
                },],
            },
        };
    },
    methods: {
        submitForm() {
            this.$refs.ruleForm.validate((valid) => {
                if (valid) {
                    login(this.ruleForm.username,this.ruleForm.account, this.ruleForm.passwd)

                    .then((res) => {
                            if (res.code === 200) {
                                this.$notify.success({
                                title:"Successfully Login",
                                message: "登录成功",
                                showClose: false,
                            });
                                this.$router.push("/index");
                            } else {
                                this.$notify.error({
                                    title:"Login Failed",
                                    message: "登陆失败",
                                    showClose: false,
                                });
                            }

                        })
                        .catch(res => {
                            this.$notify.warning({
                                title:"System Exception",
                                message: "系统异常",
                                showClose: false,
                            })
                        });
                } else {
                    return false;
                }

            });

        },
        resetForm() {
            this.$refs.ruleForm.resetFields();
        },
        handleRegister() {
            this.$router.push("/register");
        },
    },
};
</script>

<style scoped>
.el-card {
    width: 500px;
    height: 300px;
    margin: 0 auto;
    text-align: center;
}

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
