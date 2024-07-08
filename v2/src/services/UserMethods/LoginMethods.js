import  {login} from "@/api/User";
export function loginForm(){
    console.log(this.ruleForm)
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

}
export function resetForm(){
    this.$refs.ruleForm.resetFields();
}
export  function handleRegister(){
    this.$router.push("/register");
}
