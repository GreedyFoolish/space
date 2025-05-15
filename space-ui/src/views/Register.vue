<template>
  <div class="login-container">
    <h2>注册</h2>
    <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="auto" status-icon style="max-width: 600px">
      <el-form-item label="用户名" prop="name">
        <el-input v-model.number="ruleForm.name"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" autocomplete="off" type="password"/>
      </el-form-item>
      <el-form-item label="密码确认" prop="checkPass">
        <el-input v-model="ruleForm.checkPass" autocomplete="off" type="password"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)">
          注册
        </el-button>
        <el-button @click="resetForm(ruleFormRef)">重置</el-button>
        <el-button type="text" @click="toLogin">去登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue"
import {register} from "@/api/auth/auth.js";
import router from "@/router/index.js";
import {useUserStore} from "@/stores/userStore.js";
import {sha256} from "@/utils/cryptoUtils.js";

const ruleFormRef = ref()

const validateName = (rule, value, callback) => {
  if (!value) {
    return callback(new Error("请输入姓名"))
  }
}

const validatePass = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请输入密码"))
  } else {
    if (ruleForm.checkPass !== "") {
      if (!ruleFormRef.value) {
        return
      }
      ruleFormRef.value.validateField("checkPass")
    }
    callback()
  }
}

const confirmPass = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请再次输入密码"))
  } else if (value !== ruleForm.password) {
    callback(new Error("两次密码输入不匹配！"))
  } else {
    callback()
  }
}

const ruleForm = reactive({
  name: "",
  password: "",
  checkPass: "",
})

const rules = reactive({
  name: [{validator: validateName, trigger: "blur"}],
  password: [{validator: validatePass, trigger: "blur"}],
  checkPass: [{validator: confirmPass, trigger: "blur"}]
})

const submitForm = (formEl) => {
  if (!formEl) {
    return
  }
  formEl.validate(valid => {
    if (valid) {
      console.log("submit!")
    }
  })
}

const resetForm = (formEl) => {
  if (!formEl) {
    return
  }
  formEl.resetFields()
}

const toLogin = () => {
  router.push("/login")
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
