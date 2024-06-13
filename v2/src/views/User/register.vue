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
            <el-button type="primary" @click="submitForm('ruleForm')" size="medium" round>提交</el-button>
            <el-button @click="resetForm('ruleForm')" size="medium" round>重置</el-button>
            <el-button @click="goBack" size="medium" round>返回</el-button>
        </div>
    </el-card>
</div>
</template>

<script>
import {
    register,
    sendEmail,
    registerE
} from "@/api/home";
import {
    errorMessage,
    successMessage
} from "@/main";
export default {
    data() {
        return {
            ruleForm: {
                username: "",
              account:"",
                passwd: "",
                password: "",
                emailMessage: "",
            },
            /**
             *From表单校验
             */
            rules: {
                username: [{
                        required: true,
                        message: "用户名不能为空！",
                        trigger: "blur"
                    },
                    {
                        pattern: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                        message: "请输入正确的邮箱",
                        trigger: ["blur"],
                    },
                ],
              account:[{
                 required:true,
                message:"请输入账户地址",
                trigger:"blur"
              },{
                pattern: /^0x[a-fA-F0-9]{40}$/,
                message: "密码必须以 '0x' 开头，且长度必须为 42 位！",
                trigger: "blur"
              }],
                passwd: [{
                    required: true,
                    message: "请输入密码！",
                    trigger: "blur"
                }],
                password: [{
                    required: true,
                    message: "请再次输入密码！",
                    trigger: "blur"
                }],
                emailMessage: [
                {
                    pattern: /^[0-9]{6}$/,
                    message: "请输入正确的邮箱验证码",
                    trigger: ["blur"]
                }, ],
            },
        };
    },
    methods: {
        submitForm() {
            if (this.ruleForm.emailMessage == null || this.ruleForm.emailMessage === "") {
                errorMessage(300, "请输入邮箱验证码");
                return;
            }

            this.$refs.ruleForm.validate((valid) => {
                if (valid) {
                    const data = {
                        email: this.ruleForm.username,
                        math: this.ruleForm.emailMessage,
                    };
                    registerE(data).then((res) => {
                        if (res.code === 200) {
                            register(this.ruleForm).then((res) => {
                                if (res.code === 200) {
                                    this.$notify.success({
                                        title:"Registration Success",
                                        message:"注册成功",
                                        showClose:false
                                    })
                                    this.$router.push("/login");

                                } else {
                                    this.$notify.error({
                                       title:"Registration Failed",
                                        message: "注册失败",
                                        showClose:false
                                    });
                                }

                            });

                        } else {
                            errorMessage(res.code, res.msg);
                        }
                    });

                } else {
                    return false;
                }

            });

        },
        resetForm() {
            this.$refs.ruleForm.resetFields();
        },
        goBack() {
            this.$router.go(-1);
        },
        handleSendEmail() {
            this.$refs.ruleForm.validate((valid) => {
                if (valid) {
                    sendEmail(this.ruleForm.username)
                    .then( (res) => {
                      console.log("res"+res)
                        if (res.code === 555) {
                                    this.$notify.success({
                                        title:"Verification code sent successfully",
                                        message:"验证码发送成功,60秒后过期，请尽快填写",
                                        showClose:false,
                                    })
                                    // this.$router.push("/login");
                                } else {
                                    this.$notify.error({
                                       title:"Failed to send verification code",
                                        message: "验证码发送失败",
                                        showClose:false
                                    });
                                }
                    });
                }
            });
        },
    },
};
</script>

<style scoped>
.el-card {
    width: 500px;
    height: 400px;
    margin: 0 auto;
    text-align: center;
}

.box-card {
    margin: auto auto;
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
