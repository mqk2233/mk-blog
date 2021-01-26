import Vue from "vue";
import Router from "vue-router";
import Index from "../views/Index.vue";
import Login from "../views/Login.vue";
import Main from "../views/Main.vue";
import HomeAdmin from "../components/Backstage/Home";
import Article from "../components/Backstage/Article";
import Category from "../components/Backstage/Category";
import Label from "../components/Backstage/Label";
import User from "../components/Backstage/User";
import AddArticle from "../components/Backstage/AddArticle";
import Classify from "../components/Reception/Classify";
import File from "../components/Reception/File";
import LabelI from "../components/Reception/Label";
import Home from "../components/Reception/Home";
import Blog from "../components/Reception/Blog";
import { LoadingBar } from "view-design";

Vue.use(Router);

const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "*",
      name: "index",
      component: Index,
      redirect: "blog",
      children: [
        {
          path: "/blog",
          name: "blog",
          component: Home,
          meta: { title: "首页" }
        },
        {
          path: "/blogDetails",
          name: "blogDetails",
          component: Blog,
          meta: { title: "博客详情" }
        },
        {
          path: "/classify",
          name: "classify",
          component: Classify,
          meta: { title: "首页" }
        },
        {
          path: "/file",
          name: "file",
          component: File,
          meta: { title: "归档" }
        },
        {
          path: "/labelI",
          name: "labelI",
          component: LabelI,
          meta: { title: "标签" }
        }
      ]
    },
    {
      path: "/main",
      name: "main",
      component: Main,
      redirect: "homeAdmin",
      meta: { title: "首页" },
      children: [
        {
          path: "/homeAdmin",
          name: "homeAdmin",
          component: HomeAdmin,
          meta: { title: "首页" }
        },
        {
          path: "/articleAdmin",
          name: "articleAdmin",
          component: Article,
          meta: { title: "文章管理" }
        },
        {
          path: "/categoryAdmin",
          name: "categoryAdmin",
          component: Category,
          meta: { title: "分类管理" }
        },
        {
          path: "/labelAdmin",
          name: "labelAdmin",
          component: Label,
          meta: { title: "标签管理" }
        },
        {
          path: "/userAdmin",
          name: "userAdmin",
          component: User,
          meta: { title: "用户管理" }
        },
        {
          path: "/addArticle",
          name: "addArticle",
          component: AddArticle,
          meta: { title: "新增文章" }
        }
      ]
    }
  ],
  // 路由跳转滚动条行为
  scrollBehavior(to, from, savedPosition) {
    // return 期望滚动到哪个的位置
    if (savedPosition) {
      return savedPosition;
    } else {
      return { x: 0, y: 0 };
    }
  }
});
LoadingBar.config({
  height: 2.5
});
router.beforeEach((to, from, next) => {
  LoadingBar.start();
  if (
      (to.path.slice(-5) === "Admin" || to.path === "/main") &&
      localStorage.getItem("token") == null
  ) {
    next({
      path: "/login"
    });
  } else {
    next();
  }
});
router.afterEach(() => {
  LoadingBar.finish();
});

// const routerPush = Router.prototype.push;
// Router.prototype.push = location => {
//   return routerPush.call(this, location).catch(error => error);
// };
export default router;
