<template>
  <div class="container">
    <n-card>
      <n-h1>快速注册</n-h1>
      <n-form
        ref="formRef"
        :model="model"
        :rules="rules"
        label-placement="left"
        label-width="auto"
        require-mark-placement="right-hanging"
        size="small"
        :style="{
          maxWidth: '640px',
        }"
      >
        <n-form-item label="账号" path="username">
          <n-input
            v-model:value="model.username"
            placeholder="请输入邮箱地址"
          />
        </n-form-item>
        <n-form-item label="密码" path="password">
          <n-input
            v-model:value="model.password"
            type="password"
            placeholder="请输入密码"
          />
        </n-form-item>
        <n-form-item label="昵称" path="nickname">
          <n-input v-model:value="model.nickname" placeholder="请输入昵称" />
        </n-form-item>
        <n-form-item label="年龄" path="age">
          <n-input v-model:value="model.age" placeholder="请输入年龄" />
        </n-form-item>
        <n-form-item label="性别" path="gender">
          <n-radio-group v-model:value="model.gender" name="radiogroup">
            <n-space>
              <n-radio value="0">女</n-radio>
              <n-radio value="1">男</n-radio>
            </n-space>
          </n-radio-group>
        </n-form-item>
        <n-form-item label="银行账号" path="account">
          <n-input v-model:value="model.account" placeholder="请输入银行账号" />
        </n-form-item>
        <n-form-item label="电话号码" path="phone">
          <n-input v-model:value="model.phone" placeholder="请输入电话号码" />
        </n-form-item>
      </n-form>
      <n-space justify="space-around">
        <n-input
          v-model:value="model.code"
          placeholder="请输入邮箱验证码"
        ></n-input>
        <n-button round>获取验证码</n-button>
      </n-space>
      <!-- 提交 -->
      <div style="display: flex; justify-content: center; margin-top: 10px">
        <n-button type="primary"> 注册 </n-button>
      </div>
    </n-card>
  </div>
</template>

<script>
import { defineComponent, ref } from "vue";
import useRegister from "../componables/register";
import {
  NForm,
  NFormItem,
  NCard,
  NH1,
  NInput,
  NRadioGroup,
  NSpace,
  NRadio,
  NButton,
} from "naive-ui";

export default defineComponent({
  name: "RegisterVue",
  components: {
    NCard,
    NForm,
    NFormItem,
    NH1,
    NInput,
    NRadioGroup,
    NSpace,
    NRadio,
    NButton,
  },
  setup() {
    const formRef = ref(null);
    const { model, rules } = useRegister();
    return {
      formRef,
      model,
      rules,
    };
  },
});
</script>

<style scoped lang="scss">
.container {
  width: 100%;
  height: 100vh;
  padding: 1px;
  box-sizing: border-box;
  background-color: #464646;
  color: #eee;
  .n-card {
    margin: 70px auto;
    max-width: 450px;
    .n-h1 {
      text-align: center;
    }
  }
}
</style>
