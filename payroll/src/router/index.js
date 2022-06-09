import { createRouter, createWebHashHistory } from "vue-router";

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      component: () => import("../views/HomePage.vue"),
      meta: {
        requireAuth: true,
        title: "职工工资管理系统",
      },
      redirect: "/analysis",
      children: [
        // 首页（数据分析）
        {
          path: "/analysis",
          name: "analysis",
          component: () => import("../views/analysis.vue"),
        },
        // 个人信息
        {
          path: "/personal",
          name: "personal",
          component: () => import("../views/personal.vue"),
        },
        // 员工档案 + 人事调动
        {
          path: "/record",
          name: "record",
          component: () => import("../views/record.vue"),
        },
        {
          path: "/record/:id",
          name: "record-detail",
          component: () => import("../views/detail.vue"),
        },
        // 员工考勤
        {
          path: "/attendance",
          name: "attendance",
          component: () => import("../views/attendance.vue"),
        },
        {
          path: "/attendance/:id",
          name: "attendance-detail",
          component: () => import("../views/checkIn.vue"),
        },
        // 财务部门
        {
          path: "/account",
          name: "account",
          component: () => import("../views/account.vue"),
        },
        
      ],
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
