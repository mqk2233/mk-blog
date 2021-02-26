<template>
  <div class="bg" ref="bg">
    <slot name="text"/>
  </div>
</template>

<script>

const bgs = ['https://i.loli.net/2021/02/25/H1GtlPXxIS6m2rb.jpg',
  'https://i.loli.net/2021/02/25/Dfc7pWsQkObNvma.jpg',
  'https://i.loli.net/2021/02/25/jlBOYnitTsxKSI4.jpg',
  'https://i.loli.net/2021/02/25/yPI2s1VMrK9YkOF.jpg',
  'https://i.loli.net/2021/02/25/SCcojT3GgEbnMNF.jpg',
  'https://i.loli.net/2021/02/25/tpOgsqYa152djCR.jpg',
  'https://i.loli.net/2021/02/25/oX1jS8R39tICAv7.jpg',
  'https://i.loli.net/2021/02/25/tsXcQwZTNb6Y4ia.jpg',
  'https://i.loli.net/2021/02/25/Tt5yRCwFfYjWHES.jpg',
  'https://i.loli.net/2021/02/25/36cd2GDbtsSZXek.jpg',
  'https://i.loli.net/2021/02/25/CSEtW92ZiqkLvnR.jpg',
  'https://i.loli.net/2021/02/25/nqW1okhAgVMdrms.jpg',
  'https://i.loli.net/2021/02/25/oYhQT8vRqzAc3ua.jpg',
  'https://i.loli.net/2021/02/25/EADHfwdTYKPn3iv.jpg',
  'https://i.loli.net/2021/02/25/vwchWTzDpPNiH3U.jpg',
  'https://i.loli.net/2021/02/25/mCrklJ3bp84IPHD.jpg',
  'https://i.loli.net/2021/02/25/l3DSE8rPtJuNy5x.jpg',
  'https://i.loli.net/2021/02/25/mxXLqtNYQ7OMboc.jpg'];
export default {
  name: "Background",
  data() {
    return {};
  },
  props: {
    bgs:{
      type: Array,
      default: ()=>bgs
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
