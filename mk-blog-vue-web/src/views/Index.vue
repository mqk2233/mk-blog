<template>
  <div class="layout clearfix">
    <Layout>
      <Header
          ref="header"
          class="layout-header-bar layout-header-bar-hover slide-up slide-down"
      >
        <Menu
            active-name="blog"
            mode="horizontal"
            theme="light"
            style="height: 0;font-weight: 800;"
            ref="menu"
        >
          <div class="layout-logo">
            <h2 @click="toHome">MK' blog</h2>
          </div>
          <div class="layout-nav">
            <MenuItem name="blog" replace to="blog">
              <Icon type="ios-navigate"/>
              首页
            </MenuItem>
            <MenuItem name="file" replace to="file">
              <Icon type="ios-keypad"/>
              归档
            </MenuItem>
            <MenuItem name="classify" replace to="classify">
              <Icon type="ios-analytics"/>
              分类
            </MenuItem>
            <MenuItem name="labelI" replace to="labelI">
              <Icon type="md-bookmark"/>
              标签
            </MenuItem>
          </div>
        </Menu>
      </Header>
      <Content class="content-style">
        <router-view/>
      </Content>
      <Footer class="layout-footer-center">
        Copyright © 2019 - MK. All Rights Reserved
      </Footer>
    </Layout>
    <vue-canvas-nest
        :config="{ color: '#a3a3a3', opacity: '1', count: 80 }"
        class="vue-canvas-nest-element"
        style="z-index: 0 !important;"
    />
    <app-live/>
  </div>
</template>
<script>
import Live from "../components/Live2d.vue";
import vueCanvasNest from "vue-canvas-nest";

export default {
  components: {
    "app-live": Live,
    vueCanvasNest
  },

  data() {
    return {
      lastScrollPosition: window.scrollY,
      showSlideUp: false,
      showSlidDown: false
    };
  },
  methods: {
    headerMove() {
      let last_scroll_position;
      let header = this.$refs.header.$el;
      // 向下滚动
      window.addEventListener("scroll", function () {
        last_scroll_position = window.scrollY;
        // 向下滚动
        if (last_scroll_position > 70) {
          header.classList.remove("slide-down");
          header.classList.add("slide-up");
          // 向上滚动
        } else if (last_scroll_position < 70) {
          header.classList.remove("slide-up");
          header.classList.add("slide-down");
        }
      });
    },
    toHome() {
      this.$router.push({name: "blog"});
      this.$refs.menu.currentActiveName = this.$route.path.slice(1);
    },
    updateMenu() {
      this.$nextTick(() => {
        let menu = this.$refs.menu;
        menu.updateOpened();
        menu.updateActiveName();
        menu.currentActiveName = this.$route.path.slice(1);
      });
    }
  },
  mounted() {
    this.updateMenu();
    this.headerMove();
  }
};
</script>
<style scoped>
.layout {
  position: relative;
  overflow: hidden;
}

.layout-logo {
  float: left;
  position: relative;
  top: 3px;
  left: 30%;
  font-family: fantasy;
}

.layout-nav {
  width: 59%;
  margin: 0 1em 0 auto;
}

.content-style {
  display: flex !important;
  z-index: 3 !important;
  min-height: 100vh !important;
}

.layout-footer-center {
  text-align: center !important;
}

.layout-header-bar {
  position: fixed !important;
  z-index: 999 !important;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.3);
  height: 5em;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
  transition: background-color 2s;
}

.layout-header-bar-hover:hover {
  background-color: white;
}

.vue-canvas-nest-element {
  display: block !important;
  position: static;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  overflow: hidden;
  pointer-events: none;
  z-index: 2 !important;
  opacity: 0.7;
  background-color: #eeeeee;
}

.slide-up {
  transform: translateY(-100px);
  transition: transform 0.5s ease-out, background-color 2s;
}

/*导航菜单向下滑动*/
.slide-down {
  transform: translateY(0);
  transition: transform 0.5s ease-out, background-color 2s;
}


h2 {
  color: #444343;
  cursor: pointer;
}
</style>
