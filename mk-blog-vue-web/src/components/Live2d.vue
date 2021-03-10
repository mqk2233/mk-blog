<template>
  <div class="app">
    <div class="live2d-panel">
      <live2d v-if="islive2d" :modelPath="modelPath" ref='l2dMange'></live2d>
    </div>
    <div class="tools-panel">
      <live2dTools v-for="(item,index) in toolsData"
                   :key="index"
                   v-if="item.show"
                   :position="item.position"
                   @click="toolsClick(item)"
                   :width="item.width"
                   :toolsID="item.tabMsg"
                   :tabMsg="item.tabMsg"
                   :customDialogue='item.customDialogue'
                   :backgroundColor="item.backgroundColor"
                   ref='tool'
      />
      <aplayer :fixed="player.fixed"
               :autoplay="player.autoplay"
               :audio="musicList"
      />
    </div>
  </div>
</template>
<script>
import custom from "../custom";

export default {
  name: "app",
  data: () => ({
    player: {
      autoplay: true,
      listFolded: true,
      float: true,
      mini: true,
      fixed: true,
      repeat: "repeat-all"
    },
    musicList: [{
      name: "Illusionary Daytime",
      artist: "Shirfine",
      url: "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/music/1.mp3",
      cover: "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/music/1.jpg"
    },
      {
        name: "Tears of Cherry Blossoms",
        artist: "Shirfine",
        url: "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/music/2.mp3",
        cover: "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/music/2.jpg"
      },
      {
        name: "Windy Hill",
        artist: "羽肿",
        url: "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/music/3.mp3",
        cover: "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/static/music/3.jpg"
      },
    ],
    modelPath: "",
    modelPaths: "",
    customDialogue: custom,
    toolsData: [
      {
        tabMsg: "dialogue",
        width: 280,
        customDialogue: custom,
        show: true,
        position: "left"
      },
      {
        tabMsg: "github",
        backgroundColor: "#add8e6",
        show: true,
        position: "left"
      },
      {
        tabMsg: "save",
        backgroundColor: "#add8e6",
        show: true,
        position: "left"
      },
      {
        tabMsg: "hide",
        backgroundColor: "#add8e6",
        show: true,
        position: "left"
      }
    ],
    islive2d: true,
    isDialogue: false
  }),
  mounted() {
    setInterval(() => {
      fetch(
          "https://v1.hitokoto.cn/?c=b"
      )
          .then(res => res.json())
          .then(data => {
            if (!this.isDialogue) {
              let tool = this.$refs.tool.filter(item => {
                return item.customDialogue;
              });
              if (tool && tool.length > 0) tool[0].showMessage(data.hitokoto);
            } else {
              this.$refs.dialogue.showMessage(data.hitokoto);
            }
          });
    }, 10000);
    this.modelPath =
        "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/live2d/live2d-widget-model-unitychan/assets/unitychan.model.json";
    setTimeout(() => {
      this.modelPaths =
          "https://cdn.jsdelivr.net/gh/mqk2233/blog-file/live2d/live2d-widget-model-unitychan/assets/unitychan.physics.json";
    }, 2000000);
  },
  methods: {
    toolsClick(item) {
      switch (item.tabMsg) {
        case "github":
          window.open("https://github.com/mqk2233");
          break;
        case "save":
          this.$refs.l2dMange.save(`live2d-${Date.now()}.png`);
          break;
        case "hide":
          this.islive2d = false;
          this.toolsDisplay("hide");
          break;
        case "show":
          this.islive2d = true;
          this.toolsDisplay("show");
          break;
      }
    },
    toolsDisplay(display) {
      for (let i = 0, len = this.toolsData.length; i < len; i++) {
        let tabMsg = this.toolsData[i].tabMsg;
        if (display === "hide") {
          if (tabMsg === "home" || tabMsg === "about") continue;
          this.toolsData[i].show = false;
          if (tabMsg === "hide") {
            this.toolsData[i].show = true;
            this.toolsData[i].tabMsg = "show";
          }
        } else {
          this.toolsData[i].show = true;
          if (tabMsg === "show") this.toolsData[i].tabMsg = "hide";
        }
      }
    }
  }
};
</script>

<style lang="stylus" scoped>
.tools-panel
  position fixed
  left 0
  bottom 7em
  max-width 100px
  z-index 999

.live2d-panel
  position fixed
  left: 0
  bottom: 0
  z-index 999

#app
  height 100px
  z-index 999

.aPlayer
  position: fixed !important
  margin-left 0;
  z-index 99

</style>
