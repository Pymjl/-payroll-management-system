import { createRouter, createWebHashHistory } from "vue-router";

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      component: () => import("../views/HomePage.vue"),
      meta: {
        requireAuth: true,
      },
      children: [],
    },
    // 登录
    {
      path: "/login",
      component: () => import("../views/login.vue"),
      meta: {
        title: "登录",
      },
    },
    // 注册
    {
      path: "/register",
      component: () => import("../views/register.vue"),
      meta: {
        title: "注册",
      },
    },
  ],
});

// 设置前置路由守卫
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    console.log("requireAuth");
    // 判断是否需要登录权限
    if (localStorage.getItem("token")) {
      next();
    } else {
      next({
        path: "/login",
      });
    }
  } else {
    next();
  }
});

export default router;
