import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import Login from "../components/login.vue";
// 首页
import index from "../views/index.vue";


import Members from "../components/Members.vue";

import Family from "../components/Family.vue";

import Relation from "../components/Relation.vue";

import Things from "../components/Things.vue";

import Chat from "../components/Chat.vue";

// 路由
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "登录",
    component: Login,
    hidden: true,
  },
  {
    path: "/index",
    name: "首页",
    component: index,
    children: [
      {
        path: "/members/list",
        name: "成员管理",
        component: Members
      },
      {
        path: "/family/list",
        name: "家族管理",
        component: Family
      },
      {
        path: "/relation/list",
        name: "家族关系管理",
        component: Relation
      },
      {
        path: "/things/list",
        name: "事迹管理",
        component: Things
      },
      {
        path: "/chat/list",
        name: "可视化",
        component: Chat
      }
    ]
  }
];
const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
