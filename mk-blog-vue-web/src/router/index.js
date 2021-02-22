import Vue from "vue";
import Router from "vue-router";
import {LoadingBar} from "view-design";

Vue.use(Router);

const router = new Router({
    mode: "history",
    routes: [
        {
            path: "/login",
            name: "login",
            component: () => import('../views/Login.vue')
        },
        {
            path: "*",
            name: "index",
            component: () => import('../views/Index.vue'),
            redirect: "blog",
            children: [
                {
                    path: "/blog",
                    name: "blog",
                    component: () => import('../components/Reception/Home'),
                    meta: {title: "首页"}
                },
                {
                    path: "/blogDetails:id",
                    name: "blogDetails",
                    component: () => import('../components/Reception/Blog'),
                    meta: {title: "博客详情"}
                },
                {
                    path: "/classify",
                    name: "classify",
                    component: () => import('../components/Reception/Classify'),
                    meta: {title: "首页"}
                },
                {
                    path: "/file",
                    name: "file",
                    component: () => import('../components/Reception/File'),
                    meta: {title: "归档"}
                },
                {
                    path: "/labelI",
                    name: "labelI",
                    component: () => import('../components/Reception/Label'),
                    meta: {title: "标签"}
                }
            ]
        },
        {
            path: "/main",
            name: "main",
            component: () => import('../views/Main.vue'),
            redirect: "homeAdmin",
            meta: {title: "首页"},
            children: [
                {
                    path: "/homeAdmin",
                    name: "homeAdmin",
                    component: () => import('../components/Backstage/Home'),
                    meta: {title: "首页"}
                },
                {
                    path: "/articleAdmin",
                    name: "articleAdmin",
                    component: () => import('../components/Backstage/Article'),
                    meta: {title: "文章管理"}
                },
                {
                    path: "/categoryAdmin",
                    name: "categoryAdmin",
                    component: () => import('../components/Backstage/Category'),
                    meta: {title: "分类管理"}
                },
                {
                    path: "/labelAdmin",
                    name: "labelAdmin",
                    component: () => import('../components/Backstage/Label'),
                    meta: {title: "标签管理"}
                },
                {
                    path: "/addArticle:id",
                    name: "addArticle",
                    component: () => import('../components/Backstage/AddArticle'),
                    meta: {title: "新增文章"}
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
            return {x: 0, y: 0};
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

export default router;
