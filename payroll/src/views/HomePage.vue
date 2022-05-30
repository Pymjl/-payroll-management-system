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
import { defineComponent, h, ref, onMounted } from "vue";
import {
  NIcon,
  NMenu,
  NLayout,
  NLayoutSider,
  NLayoutContent,
  useMessage,
} from "naive-ui";
import { RouterLink } from "vue-router";
import NavHeader from "@/components/navHeader.vue";
import { getUserInfo } from "../api/load.js";
import {
  Home,
  UserProfile,
  IdManagement,
  Finance,
  TimePlot,
  Logout,
} from "@vicons/carbon";
import ls from "../utils/ls.js";

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
    const message = useMessage();
    const identity = ref(null);
    const isShow = ref(null);

    function renderIcon(icon) {
      return () => h(NIcon, null, { default: () => h(icon) });
    }
    const menuOptions = [
      // 首页
      {
        label: () =>
          h(
            RouterLink,
            {
              to: {
                name: "analysis",
              },
            },
            { default: () => "首页" }
          ),
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
            { default: () => "员工管理" }
          ),
        key: "record",
        icon: renderIcon(IdManagement),
        disabled: isShow.value ? false : true,
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
        disabled: isShow.value ? false : true,
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
        disabled: isShow.value ? false : true,
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
    const getInfo = () => {
      getUserInfo().then(({ res, succeed }) => {
        if (succeed) {
          ls.setItem("user", res.data);
          identity.value = res.data.identity;
          // 判断是否是管理员
          if (identity.value === 0) {
            isShow.value = false;
          } else {
            isShow.value = true;
          }
        } else {
          message.error(res.message);
        }
      });
    };
    onMounted(() => {
      getInfo();
    });
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
