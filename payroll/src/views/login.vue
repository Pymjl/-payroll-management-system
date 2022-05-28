<template>
  <div class="container">
    <div class="login">
      <div class="title">立即登录</div>
      <div class="form">
        <div class="box">
          <n-icon :size="17">
            <UserFavorite />
          </n-icon>
          <span>账号</span
          ><input
            id="username"
            type="text"
            :value="userInfo.username"
            required
          />
        </div>
        <div class="box">
          <n-icon :size="17">
            <SubnetAclRules />
          </n-icon>
          <span>密码</span
          ><input
            id="password"
            type="password"
            :value="userInfo.password"
            required
          />
        </div>
        <n-space class="code" justify="space-between">
          <input type="text" :value="userInfo.code" required />
          <n-button type="info" secondary>获取验证码</n-button>
        </n-space>
      </div>
      <div id="loginBtn">登录</div>
      <div class="register">
        还没有账号？<span id="registerBtn" @click="jump">立即注册</span>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent, ref } from "vue";
import { useMessage, NButton, NSpace, NIcon } from "naive-ui";
import { UserFavorite, SubnetAclRules } from "@vicons/carbon";
import useLogin from "../componables/login";

export default defineComponent({
  name: "LoginVue",
  components: {
    NButton,
    NSpace,
    UserFavorite,
    NIcon,
    SubnetAclRules,
  },
  props: {},
  setup() {
    const { jump } = useLogin();
    const message = useMessage();
    message.info("info");
    const userInfo = ref({
      username: "",
      password: "",
      code: "",
    });

    return {
      userInfo,
      jump,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "../style/login.scss";
</style>
