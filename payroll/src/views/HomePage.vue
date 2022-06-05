<template>
  <nav-header></nav-header>
  <n-layout has-sider :native-scrollbar="false">
    <!--  侧边栏 -->
    <n-layout-sider bordered>
      <n-menu :options="menuOptions" default-value="analysis" />
    </n-layout-sider>
    <!-- 内容 -->
    <n-layout-content content-style="padding: 24px;">
      <router-view />
    </n-layout-content>
  </n-layout>
</template>

<script>
import { defineComponent, h } from "vue";
import { NIcon, NMenu, NLayout, NLayoutSider, NLayoutContent } from "naive-ui";
import { RouterLink } from "vue-router";
import NavHeader from "@/components/navHeader.vue";
import {
  Home,
  UserProfile,
  IdManagement,
  Finance,
  TimePlot,
  Logout,
} from "@vicons/carbon";
import ls from "../utils/ls.js";

function renderIcon(icon) {
  return () => h(NIcon, null, { default: () => h(icon) });
}

// 管理员身份
const adminOptions = [
  // 首页
  {
    label: () =>
      h(RouterLink, { to: { name: "analysis" } }, { default: () => "首页" }),
    key: "analysis",
    icon: renderIcon(Home),
  },
  // 个人信息
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "personal",
          },
        },
        { default: () => "个人信息" }
      ),
    key: "personal",
    icon: renderIcon(UserProfile),
  },
  // 员工管理
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "record",
          },
        },
        { default: () => "员工档案" }
      ),
    key: "record",
    icon: renderIcon(IdManagement),
  },
  // 财务部
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "account",
          },
        },
        { default: () => "财务部" }
      ),
    key: "account",
    icon: renderIcon(Finance),
  },
  // 员工考勤
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "attendance",
          },
        },
        { default: () => "员工考勤" }
      ),
    key: "attendance",
    icon: renderIcon(TimePlot),
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            path: "/login",
          },
          onClick: () => {
            ls.clear();
            window.location.reload();
          },
        },
        { default: () => "退出登录" }
      ),
    key: "logout",
    icon: renderIcon(Logout),
  },
];
// 普通员工身份
const normalOptions = [
  // 首页
  {
    label: () =>
      h(RouterLink, { to: { name: "analysis" } }, { default: () => "首页" }),
    key: "analysis",
    icon: renderIcon(Home),
  },
  // 个人信息
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            name: "personal",
          },
        },
        { default: () => "个人信息" }
      ),
    key: "personal",
    icon: renderIcon(UserProfile),
  },
  {
    label: () =>
      h(
        RouterLink,
        {
          to: {
            path: "/login",
          },
          onClick: () => {
            ls.clear();
            window.location.reload();
          },
        },
        { default: () => "退出登录" }
      ),
    key: "logout",
    icon: renderIcon(Logout),
  },
];
// 菜单信息
let menuOptions = [];
console.log(ls.getItem("user"));
console.log(ls.getItem("user").identity);
// 判断是否是管理员
if (ls.getItem("user").identity === 0) {
  menuOptions = normalOptions;
} else {
  menuOptions = adminOptions;
}

export default defineComponent({
  name: "HomePage",
  components: {
    NMenu,
    NavHeader,
    NLayout,
    NLayoutSider,
    NLayoutContent,
  },
  props: {},
  setup() {
    return {
      menuOptions,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "../style/var.scss";
h1 {
  color: $color;
}
.n-layout-content {
  height: calc(100vh - 70px);
  background: #fafafc;
}
</style>
