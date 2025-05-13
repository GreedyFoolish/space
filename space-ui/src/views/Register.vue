<template>
  <div class="login-container">
    <h2>登录</h2>
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        class="demo-ruleForm"
        label-width="auto"
        status-icon
        style="max-width: 600px"
    >
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" autocomplete="off" type="password"/>
      </el-form-item>
      <el-form-item label="Confirm" prop="checkPass">
        <el-input
            v-model="ruleForm.checkPass"
            autocomplete="off"
            type="password"
        />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model.number="ruleForm.age"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)">
          Submit
        </el-button>
        <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue"

const ruleFormRef = ref()

const checkAge = (rule, value, callback) => {
  if (!value) {
    return callback(new Error("Please input the age"))
  }
  setTimeout(() => {
    if (!Number.isInteger(value)) {
      callback(new Error("Please input digits"))
    } else {
      if (value < 18) {
        callback(new Error("Age must be greater than 18"))
      } else {
        callback()
      }
    }
  }, 1000)
}

const validatePass = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("Please input the password"))
  } else {
    if (ruleForm.checkPass !== "") {
      if (!ruleFormRef.value) return
      ruleFormRef.value.validateField("checkPass")
    }
    callback()
  }
}

const validatePass2 = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请再次输入密码"))
  } else if (value !== ruleForm.password) {
    callback(new Error("两次密码输入不匹配！"))
  } else {
    callback()
  }
}

const ruleForm = reactive({
  password: "",
  checkPass: "",
  age: ""
})

const rules = reactive({
  password: [{validator: validatePass, trigger: "blur"}],
  checkPass: [{validator: validatePass2, trigger: "blur"}],
  age: [{validator: checkAge, trigger: "blur"}]
})

const submitForm = (formEl) => {
  if (!formEl) return
  formEl.validate(valid => {
    if (valid) {
      console.log("submit!")
    } else {
      console.error("error submit!")
    }
  })
}

const resetForm = (formEl) => {
  if (!formEl) return
  formEl.resetFields()
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
