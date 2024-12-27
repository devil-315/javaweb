<script setup>
        import {ref,reactive} from 'vue'
        import {useRouter} from 'vue-router'
        const router = useRouter()
        import { onMounted, onBeforeUnmount  } from 'vue';
        import{ defineUser}  from '../store/userStore.js'
        let sysUser = defineUser()
        import request  from '../utils/request'

        let loginUser =reactive({
                username:"",
                userPwd:""
        })

       let usernameMsg =ref("")
       let userPwdMsg =ref("")

       onMounted(() => {
        // 在组件加载完成后为body元素添加样式
        document.querySelector('body').setAttribute('style', 'background-color: #34495e;margin:0;font-family:"Arial", "sans-serif"')
      });

      // 在组件销毁前执行的代码
      onBeforeUnmount(() => {
            // 移除body元素的所有样式
            document.querySelector('body').removeAttribute('style');
          });

       function checkUsername(){
            let usernameReg= /^[a-zA-Z0-9]{5,10}$/
            if(!usernameReg.test(loginUser.username)){
                usernameMsg.value="格式有误"
                return false
            }
            usernameMsg.value="OK"
            return true
       }

       function checkUserPwd(){
            let userPwdReg = /^[0-9]{6}$/

            if(!userPwdReg.test(loginUser.userPwd)){
                userPwdMsg.value="格式有误"
                return false
            }

            userPwdMsg.value="OK"
            return true
       }

       async function login(){
            // 表单数据格式都正确再提交
            let flag1 =checkUsername()
            let flag2 =checkUserPwd()
            if(!(flag1 && flag2)){
                return 
            }
           let {data} = await request.post("user/login",loginUser)
           if(data.code == 200 ){
                alert("登录成功")
                console.log(data)
                // 获得登录的用户信息,更新到pinia中
                sysUser.uid =data.data.loginUser.uid
                sysUser.username = data.data.loginUser.username
                // 跳转到showSchedule
                router.push("/showSchedule")
           } else if( data.code == 503){
                alert("密码有误")

           }else if (data.code == 501 ){
                alert("用户名有误")
           }else {
                alert("未知错误")
           }
           
       }

       function clearForm(){
        loginUser.username=""
        loginUser.userPwd=""
        usernameMsg.value=""
        userPwdMsg.value=""
    }


</script>

<template>
    <div class="login-container">
      <div class="login-box">
        <h1 class="login-title">LOGIN</h1>
        <div class="input-container">
          <input
          class="login-input"
          type="text"
          v-model="loginUser.username"
          placeholder="请输入账号"
          @blur="checkUsername()"
        />
          <span id="usernameMsg" v-text="usernameMsg" class="login-msg"></span>
        </div>
        
        <div class="input-container">
          <input
          class="login-input"
          type="password"
          v-model="loginUser.userPwd"
          placeholder="请输入密码"
          @blur="checkUserPwd()"
        />
        <span id="userPwdMsg" v-text="userPwdMsg" class="login-msg"></span>
        </div>
        
  
        <div class="button-container">
          <button class="login-btn" @click="login()">登录</button>
          <button class="login-btn reset-btn"  @click="clearForm()">重置</button>
          <router-link to="/regist">
            <button class="login-btn register-btn">去注册</button>
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


/* 提示信息样式 */
.login-msg {
  color: gold;
  font-size: 12px;
  margin-left: 10px;
}


</style>
