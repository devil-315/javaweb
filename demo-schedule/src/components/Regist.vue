<script setup>
    import {ref,reactive} from 'vue'
    /* 导入发送请求的axios对象 */
    import request from'../utils/request'
    import { onMounted, onBeforeUnmount  } from 'vue';
    import {useRouter} from 'vue-router'
    const router = useRouter()

    let registUser = reactive({
        username:"",
        userPwd:""
    })
    let usernameMsg=ref('')
    let userPwdMsg=ref('')
    let reUserPwdMsg=ref('')
    let reUserPwd=ref('')
    async function checkUsername(){
        let usernameReg= /^[a-zA-Z0-9]{5,10}$/
        if(!usernameReg.test(registUser.username)){
            usernameMsg.value="格式有误"
            return false
        }
        // 发送异步请求   继续校验用户名是否被占用
        let {data} = await request.post(`user/checkUsernameUsed?username=${registUser.username}`)
        if(data.code != 200){
            usernameMsg.value="用户名占用"
            return false
        }
        usernameMsg.value="可用"
        return true
    }

    function checkUserPwd(){
        let userPwdReg = /^[0-9]{6}$/
        if(!userPwdReg.test(registUser.userPwd)){
            userPwdMsg.value="格式有误"
            return false
        }
        userPwdMsg.value="OK"
        return true
    }

    function checkReUserPwd(){
        let userPwdReg = /^[0-9]{6}$/
        if(!userPwdReg.test(reUserPwd.value)){
            reUserPwdMsg.value="格式有误"
            return false
        }
        if(registUser.userPwd != reUserPwd.value){
            reUserPwdMsg.value="两次密码不一致"
            return false
        }
        reUserPwdMsg.value="OK"
        return true
    }

    // 注册的方法
    async function regist(){
        // 校验所有的输入框是否合法
        let flag1 =await checkUsername()
        let flag2 =await checkUserPwd()
        let flag3 =await checkReUserPwd()
        if(flag1 && flag2 && flag3){
          let  {data}= await request.post("user/regist",registUser)
          if(data.code == 200){
            // 注册成功跳转 登录页
            alert("注册成功,快去登录吧")
            router.push("/login")
          }else{
            alert("抱歉,用户名被抢注了")
          }
        }else{
            alert("校验不通过,请求再次检查数据")
        }
    }

    function clearForm(){
        registUser.username=""
        registUser.userPwd=""
        usernameMsg.value=""
        userPwdMsg.value=""
        reUserPwd.value=""
        reUserPwdMsg.value=""

    }

    onMounted(() => {
        // 在组件加载完成后为body元素添加样式
        document.querySelector('body').setAttribute('style', 'background-color: #34495e;margin:0;font-family:"Arial", "sans-serif"')
      });

      // 在组件销毁前执行的代码
      onBeforeUnmount(() => {
            // 移除body元素的所有样式
            document.querySelector('body').removeAttribute('style');
          });
</script>

<template>
    <div class="login-container">
      <div class="login-box">
        <h1 class="login-title">LOGIN</h1>
        <div class="input-container">
          <input
          class="login-input"
          id="usernameInput"
          type="text"
          v-model="registUser.username"
          placeholder="请输入账号"
          @blur="checkUsername()"
        />
        <span id="usernameMsg" class="msg" v-text="usernameMsg"></span>
        </div>
        
        <div class="input-container">
          <input
          class="login-input"
          id="userPwdInput"
          type="password"
          v-model="registUser.userPwd"
          placeholder="请输入密码"
          @blur="checkUserPwd()"
        />
        <span id="userPwdMsg" class="msg" v-text="userPwdMsg"></span>
        </div>
        
        <div class="input-container">
          <input
          class="login-input"
          id="reUserPwdInput"
          type="password"
          v-model="reUserPwd"
          placeholder="确认密码"
          @blur="checkReUserPwd()"
        />
        <span id="reUserPwdMsg" class="msg" v-text="reUserPwdMsg"></span>
        </div>

        <div class="button-container">
          <button class="login-btn" @click="regist()">注册</button>
          <button class="login-btn reset-btn" @click="clearForm()">重置</button>
          <router-link to="/login">
            <button class="login-btn register-btn">去登录</button>
          </router-link>
        </div>
      </div>
    </div>
  </template>
<style scoped>
/* 登录容器样式 */
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

/* 登录框样式 */
.login-box {
  width: 300px;
  background: #191919;
  padding: 30px;
  position: relative;
  text-align: center;
  border-radius: 20px;
  box-shadow: 0 19px 38px rgba(0, 0, 0, 0.3), 0 15px 12px rgba(0, 0, 0, 0.22);
}

/* 登录标题样式 */
.login-title {
  color: white;
  font-weight: 800;
}

/* 输入框样式 */
.login-input {
  background: none;
  text-align: center;
  border: 1px solid #3498db;
  padding: 14px 10px;
  border-radius: 24px;
  color: white;
  width: 200px;
  outline: none;
  transition: 0.3s;
}

.login-input:focus {
  width: 260px;
  border-color: #2ecc71;
}

/* 按钮容器样式 */
.button-container {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

/* 按钮样式 */
.login-btn {
  background: none;
  display: block;
  padding: 10px 25px;
  text-align: center;
  border: 1px solid #2ecc71;
  color: white;
  border-radius: 24px;
  cursor: pointer;
  transition: 0.3s;
}

.login-btn:hover {
  background-color: #2ecc71;
}

/* 重置按钮样式 */
.reset-btn {
  border-color: #e74c3c;
  color: #e74c3c;
}

/* 注册按钮样式 */
.register-btn {
  border-color: #3498db;
  color: #3498db;
}

/* 输入框容器样式 */
.input-container {
  display: flex;
  align-items: baseline; /* 使基线对齐 */
  justify-content: center;
  position: relative;
  margin: 20px 0; /* 调整垂直间距 */
}

.msg {
  color: gold;
  font-size: 12px;
  margin-left: 10px;
}
</style>

