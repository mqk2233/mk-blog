<template>
  <div class="layout">
    <Header>
      <Menu style="z-index: 0" active-name="1" mode="horizontal" theme="dark">
        <div class="layout-logo"></div>
        <div
            @click="isOpenDrawer = true"
            style="cursor:pointer;margin-left: 93%;"
        >
          <Avatar
              :src="user.imgHead"
              class="change1"
              style="margin-right: 0.5em;"
          />
          <span style="color: white;font-size: 15px;">{{ user.userName }}</span>
        </div>
        <Drawer :closable="false" v-model="isOpenDrawer">
          <Card>
            <header :style="{ zIndex: 0 }">
              <div id="change1">
                <Avatar
                    :src="user.imgHead"
                    class="change1"
                    size="large"
                    style="-webkit-transition: all 1s ease-in-out;"
                />
              </div>
              <span style="color: black;font-size: 14px;margin-left: 0.5em;">
                今天也要努力学习哦^~^ {{ user.userName }}</span
              >
            </header>
          </Card>
          <Card style="margin-top: 1em">
            <CellGroup style="margin-top:1em;">
              <Cell title="Only show titles"/>
              <Cell label="label content" title="Display label content"/>
              <Cell extra="details" title="Display right content"/>
              <Cell extra="details" title="Link" to="/components/button"/>
              <Cell
                  target="_blank"
                  title="Open link in new window"
                  to="/components/button"
              />
              <Cell disabled title="Disabled"/>
              <Cell selected title="Selected"/>
              <Cell title="With Badge" to="/components/badge">
                <Badge :count="10" slot="extra"/>
              </Cell>
            </CellGroup>
          </Card>
          <Card style="margin-top: 8em;">
            <Tooltip content="我的博客" placement="top">
              <Icon
                  @click="doJump('index')"
                  color="#5cadff"
                  size="35"
                  style="cursor:pointer;"
                  type="ios-paper-plane"
              />
            </Tooltip>
            <Tooltip content="退出登录" placement="top" style="float:right">
              <Icon
                  @click="doJump('login')"
                  size="35"
                  style="cursor:pointer;"
                  type="md-exit"
              />
            </Tooltip>
          </Card>
        </Drawer>
      </Menu>
    </Header>
    <Layout>
      <Sider
          :style="{ background: '#ffffff', zIndex: 0 }"
          breakpoint="md"
          hide-trigger
      >
        <Menu
            :class="menuitemClasses"
            active-name="homeAdmin"
            theme="light"
            width="auto"
            ref="menu"
        >
          <menu-item name="homeAdmin" replace to="/homeAdmin">
            <Icon type="ios-paper-plane"/>
            <span>仪表盘</span>
          </menu-item>
          <menu-item name="articleAdmin" replace to="/articleAdmin">
            <Icon type="md-book"/>
            <span>文章管理</span>
          </menu-item>
          <menu-item name="categoryAdmin" replace to="/categoryAdmin">
            <Icon type="logo-buffer"/>
            <span>类别管理</span>
          </menu-item>
          <menu-item name="labelAdmin" replace to="/labelAdmin">
            <Icon type="md-attach"/>
            <span>标签管理</span>
          </menu-item>
          <Submenu name="6">
            <template slot="title">
              <Icon type="logo-codepen"/>
              系统监控
            </template>
            <menu-item
                name="6-1"
                replace
                target="_blank"
                to="api/druid/login.html"
            >
              <span>Druid监控</span>
            </menu-item>
          </Submenu>
        </Menu>
      </Sider>
      <Layout :style="{ padding: '0 24px 24px' }">
        <Breadcrumb :style="{ margin: '24px 0' }">
          <BreadcrumbItem to="main">后台</BreadcrumbItem>
          <BreadcrumbItem :to="path">{{ title }}</BreadcrumbItem>
        </Breadcrumb>
        <Content
            :style="{ padding: '24px', minHeight: '76vh', background: '#fff' }"
        >
          <router-view/>
        </Content>
        <Footer style="text-align: center;"
        >Copyright © 2019 - MK. All Rights Reserved
        </Footer>
      </Layout>
    </Layout>
    <app-live/>
  </div>
</template>
<script>
import Live2d from "../components/Live2d.vue";
import {CURRENT_USER, LOGOUT} from "@/mutation/mutation-types";

export default {
  data() {
    return {
      isCollapsed: false,
      isOpenDrawer: false,
      title: "",
      path: "",
      user: "",
      isChange: true
    };
  },
  methods: {
    getCurrentUser() {
      this.$api.user
          .getCurrentUser()
          .then(res => {
            this.user = res.data.data;
            this.$store.commit(CURRENT_USER, res.data.data);
          })
          .catch(() => {
            this.$router
                .replace({name: "login"})
                .catch(err => this.$Notice.warning({title: err.toString()}));
          });
    },
    doJump(path) {
      this.$router
          .replace({name: path})
          .catch(err => this.$Notice.warning({title: err.toString()}));
      if (path === "login") {
        this.$store.commit(LOGOUT, "");
      }
    }
  },
  created() {
    this.getCurrentUser();
  },
  computed: {
    menuitemClasses: function () {
      return ["menu-item", this.isCollapsed ? "collapsed-menu" : ""];
    }
  },
  watch: {
    $route(to) {
      // 对路由变化作出响应
      if (this.$router.currentRoute.params.id != null &&
          this.$router.currentRoute.params.id !== "") {
        this.title = "编辑文章"
      } else {
        this.title = to.meta.title;
      }
      this.path = to.path;
    }
  },
  mounted() {
    this.$nextTick(() => {
      let menu = this.$refs.menu;
      menu.updateOpened();
      menu.updateActiveName();
      menu.currentActiveName = this.$route.path.slice(1);
    });
  },
  components: {
    "app-live": Live2d
  }
};
</script>
<style scoped>
.menu-item span {
  display: inline-block;
  overflow: hidden;
  width: 69px;
  text-overflow: ellipsis;
  white-space: nowrap;
  vertical-align: bottom;
  transition: width 0.2s ease 0.2s;
}

.menu-item i {
  transform: translateX(0px);
  transition: font-size 0.2s ease, transform 0.2s ease;
  vertical-align: middle;
  font-size: 16px;
}

.collapsed-menu span {
  width: 0;
  transition: width 0.2s ease;
}

.collapsed-menu i {
  transform: translateX(5px);
  transition: font-size 0.2s ease 0.2s, transform 0.2s ease 0.2s;
  vertical-align: middle;
  font-size: 22px;
}

.layout {
  border: 0 solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  overflow: hidden;
}

.layout-logo {
  width: 100px;
  height: 30px;
  background: #5b6270;
  border-radius: 3px;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
}

.change1:hover {
  -webkit-transform: rotate(360deg);
  -webkit-transition: all 0.5s ease-in-out;
}
</style>
