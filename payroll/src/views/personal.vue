<template>
  <n-card>
    <n-h2>个人信息</n-h2>
    <n-form
      ref="formRef"
      :model="userInfo"
      :rules="rules"
      size="small"
      label-placement="left"
    >
      <n-grid :cols="24" :x-gap="24">
        <n-form-item-gi :span="8" label="职工ID" path="id">
          <n-input v-model:value="userInfo.id" disabled />
        </n-form-item-gi>
        <n-form-item-gi :span="8" label="账号" path="username">
          <n-input v-model:value="userInfo.username" disabled />
        </n-form-item-gi>
        <n-form-item-gi :span="8" label="昵称" path="nickname">
          <n-input v-model:value="userInfo.nickname" />
        </n-form-item-gi>
      </n-grid>
      <n-grid :cols="24" :x-gap="24">
        <n-form-item-gi :span="8" label="年龄" path="age">
          <n-input v-model:value="userInfo.age" />
        </n-form-item-gi>
        <n-form-item-gi :span="8" label="身份" path="identity">
          <n-input v-model:value="userInfo.identity" disabled />
        </n-form-item-gi>
        <n-form-item-gi
          :span="8"
          label="部门"
          path="deptName"
          placeholder="暂无"
        >
          <n-input v-model:value="userInfo.deptName" disabled />
        </n-form-item-gi>
      </n-grid>
      <n-grid :cols="24" :x-gap="24">
        <n-form-item-gi :span="8" label="银行账号" path="account">
          <n-input v-model:value="userInfo.account" />
        </n-form-item-gi>
        <n-form-item-gi :span="8" label="电话" path="phone">
          <n-input v-model:value="userInfo.phone" />
        </n-form-item-gi>
        <n-form-item-gi
          :span="8"
          label="系名"
          path="facultyName"
          placeholder="暂无"
        >
          <n-input v-model:value="userInfo.facultyName" disabled />
        </n-form-item-gi>
      </n-grid>
    </n-form>
    <n-space justify="center">
      <n-button type="primary" style="width: 100px" @click="updateEvent"
        >修改</n-button
      ></n-space
    >
  </n-card>
  <n-card class="setting">
    <n-h2>个人设置</n-h2>
    <n-button-group>
      <n-input
        type="password"
        placeholder="新密码"
        v-model:value="password"
      ></n-input>
      <n-button type="primary" @click="updatePsw">修改密码</n-button>
    </n-button-group>
  </n-card>
</template>

<script>
import { defineComponent } from "vue";
import {
  NCard,
  NForm,
  NGrid,
  NFormItemGi,
  NInput,
  NButton,
  NH2,
  NSpace,
  NButtonGroup,
} from "naive-ui";
import usePersonal from "../componables/personal.js";

export default defineComponent({
  name: "PersonalVue",
  components: {
    NCard,
    NForm,
    NGrid,
    NFormItemGi,
    NInput,
    NButton,
    NH2,
    NSpace,
    NButtonGroup,
  },
  props: {},
  setup() {
    const { userInfo, rules, password, updateEvent, updatePsw } = usePersonal();
    return {
      userInfo,
      rules,
      password,
      updateEvent,
      updatePsw,
    };
  },
});
</script>

<style lang="scss" scoped>
.n-form {
  .n-input {
    width: 200px;
  }
}
.setting {
  margin-top: 20px;
}
</style>
