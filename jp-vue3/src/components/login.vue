<template>
  <div class="login-wrap">
    <el-form
      ref="ruleFormRef"
      :model="form"
      :rules="rules"
      class="demo-ruleForm login-container"
      :size="formSize"
      status-icon
    >
      <h3 class="title">家谱管理系统</h3>
      <el-form-item prop="username">
        <el-input
          type="text"
          v-model="form.username"
          auto-complete="off"
          placeholder="请输入账号"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="form.password"
          auto-complete="off"
          placeholder="请输入密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button
          type="primary"
          style="width: 100%"
          @click="submitForm(ruleFormRef)"
          >登录</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import axios from "axios";
import type { FormInstance, FormRules, ElMessage } from "element-plus";
import { useRouter } from "vue-router";

const router = useRouter();

const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();
const form = reactive({
  username: "",
  password: "",
});
const rules = reactive<FormRules>({
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
});

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      axios.post("http://localhost:8100/User/login", form).then((res: any) => {
        if (res.data.code == 200) {
          ElMessage.success("登录成功");
          router.push("/members/list");
          localStorage.setItem("user",JSON.stringify(res.data.data));
        } else {
          ElMessage.error(res.data.msg);
        }
      });
    } else {
      console.log("error submit!", fields);
    }
  });
};
</script>

<style scoped>
.login-wrap {
  box-sizing: border-box;
  width: 100%;
  height: 100vh;
  padding-top: 15%;
  background-image: url("../assets/bg.png");
  background-position: center right;
  background-size: 100%;
}
.login-container {
  border-radius: 10px;
  margin: 0px auto;
  width: 300px;
  line-height: 40px;
  padding: 30px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  text-align: left;
  box-shadow: 0 0 20px 2px rgba(0, 0, 0, 0.1);
}
.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
