export default {
    data() {
        return {
            ruleForm: {
                username: "",
                account: "",
                passwd: "",
            },
            rules: {
                username: [{
                    required: true,
                    message: "用户名不能为空！",
                    trigger: "blur"
                }],
                account: [{
                    required: true,
                    message: "请输入账户地址",
                    trigger: "blur"
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
    }
}