export default {
    data() {
        return {
            ruleForm: {
                username: "",
                account: "",
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
                account: [{
                    required: true,
                    message: "请输入账户地址",
                    trigger: "blur"
                }, {
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
                    },],
            },
        };
    }
}