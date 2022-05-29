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
            v-model="userInfo.username"
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
            v-model="userInfo.password"
            required
          />
        </div>
        <n-space justify="center">
          <input v-model="userInfo.code" placeholder="图片验证码" />
          <n-button
            secondary
            class="verifyCode"
            :style="
              verifyCode ? { backgroundImage: `url(${verifyCode})` } : null
            "
            :loading="isLoading"
            @click="getCode"
          ></n-button>
        </n-space>
        <n-space justify="center">
          <input v-model="userInfo.emailCode" placeholder="邮箱验证码" />
          <n-button round @click="getEmailCodeEvent">获取邮箱验证码</n-button>
        </n-space>
      </div>
      <div id="loginBtn" @click="signIn">登录</div>
      <div class="register">
        还没有账号？<span id="registerBtn" @click="jump">立即注册</span>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import { NButton, NSpace, NIcon } from "naive-ui";
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
    const {
      userInfo,
      verifyCode,
      isLoading,
      getCode,
      jump,
      signIn,
      getEmailCodeEvent,
    } = useLogin();

    return {
      userInfo,
      verifyCode,
      isLoading,
      jump,
      signIn,
      getCode,
      getEmailCodeEvent,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "../style/login.scss";
</style>
