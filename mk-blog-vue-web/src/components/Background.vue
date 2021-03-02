<template>
  <div class="bg" ref="bg">
    <slot name="text"/>
  </div>
</template>

<script>

const bgs = ['https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/1.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/2.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/3.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/4.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/5.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/6.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/7.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/8.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/9.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/10.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/11.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/12.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/13.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/14.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/15.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/16.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/17.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/18.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/19.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/20.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/21.jpg',
  'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/22.jpg'];
export default {
  name: "Background",
  data() {
    return {};
  },
  props: {
    bgs: {
      type: Array,
      default: () => bgs
    },
    index: {
      type: Number,
      required: true
    },
    height: {
      type: String,
      default: "100"
    },
    time: {
      type: String,
      default: "2"
    }
  },
  methods: {
    onLoad() {
      let height = this.height;
      let time = this.time;
      let background = new Image();
      let bgDiv = this.$refs.bg;
      background.src = bgs[this.index];
      background.onload = function () {
        bgDiv.style.backgroundImage = "url(" + background.src + ")";
        bgDiv.style.height = height + "vh";
        bgDiv.style.transitionProperty = "all";
        bgDiv.style.transitionDuration = time + "s";
      };
    }
  },
  mounted() {
    this.onLoad();
  }
};
</script>

<style scoped>
.bg {
  background-repeat: no-repeat;
  background-attachment: local;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 0;
  left: 0;
  top: 0;
  position: absolute;
  z-index: -1;
  text-align: center;
}
</style>
