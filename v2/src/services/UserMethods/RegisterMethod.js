import { register,sendEmail,registerE } from "@/api/User";
import { errorMessage } from "@/main";

export function registerForm() {
    if (this.ruleForm.emailMessage == null || this.ruleForm.emailMessage === "") {
        errorMessage(300, "请输入邮箱验证码");
        return;
    }

    this.$refs.ruleForm.validate((valid) => {
        if (valid) {
            const data = {
                email: this.ruleForm.username,
                math: this.ruleForm.emailMessage,
                username: this.ruleForm.username,
                account: this.ruleForm.account,
                passwd: this.ruleForm.passwd,
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
}

export function resetForm() {
    this.$refs.ruleForm.resetFields();
}

export function goBack() {
    this.$router.go(-1);
}

export function handleSendEmail() {
    this.$refs.ruleForm.validate((valid) => {
        if (valid) {
            sendEmail(this.ruleForm.username)
                .then((res) => {
                    if (res.code === 555) {
                        this.$notify.success({
                            title: "Verification code sent successfully",
                            message: "验证码发送成功, 60秒后过期，请尽快填写",
                            showClose: false,
                        });
                    } else {
                        this.$notify.error({
                            title: "Failed to send verification code",
                            message: "验证码发送失败",
                            showClose: false,
                        });
                    }
                })
                .catch(() => {
                    this.$notify.error({
                        title: "System Exception",
                        message: "发送验证码异常，请稍后重试",
                        showClose: false,
                    });
                });
        }
    });
}
