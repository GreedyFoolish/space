<template>
  <div class="login-container">
    <h2>登录</h2>
    <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="auto" status-icon style="max-width: 600px">
      <el-form-item label="用户名" prop="name">
        <el-input v-model="ruleForm.name" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" autocomplete="off" type="password"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)">
          登录
        </el-button>
        <el-button @click="resetForm(ruleFormRef)">重置</el-button>
        <el-button type="text" @click="toRegister">去注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue"
import {login} from "@/api/auth/auth.js"
import {useUserStore} from "@/stores/userStore.js";
import router from "@/router/index.js";

const ruleFormRef = ref()

const validateName = (rule, value, callback) => {
  if (!value) {
    return callback(new Error("请输入用户名"))
  } else {
    return callback()
  }
}

const validatePass = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请输入密码"))
  } else {
    if (ruleForm.name !== "") {
      if (!ruleFormRef.value) return
      ruleFormRef.value.validateField("name")
    }
    callback()
  }
}

const ruleForm = reactive({
  name: "",
  password: ""
})

const rules = reactive({
  name: [{validator: validateName, trigger: "blur"}],
  password: [{validator: validatePass, trigger: "blur"}]
})

const submitForm = (formEl) => {
  if (!formEl) {
    return
  }
  formEl.validate(valid => {
    if (valid) {
      login(ruleForm).then(res => {
        if (res.code === 200) {
          const token = res.data?.token
          if (!token) {
            return
          }
          useUserStore().login(token).then(() => {
            router.push("/home");
          })
        }
      })
    }
  })
}

const resetForm = (formEl) => {
  if (!formEl) {
    return
  }
  formEl.resetFields()
}

const toRegister = () => {
  router.push("/register")
}
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 100px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
