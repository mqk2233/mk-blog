<template>
  <div class="bg" ref="bg">
    <slot name="text" />
  </div>
</template>

<script>
export default {
  name: "Background",
  props: {
    url: {
      type: String,
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
  data: function() {
    return {
      return() {}
    };
  },
  methods: {
    onLoad() {
      let height = this.height;
      let time = this.time;
      let background = new Image();
      let bgDiv = this.$refs.bg;
      background.src = this.url;
      background.onload = function() {
        bgDiv.style.backgroundImage = "url(" + background.src + ")";
        bgDiv.style.height = height + "vh";
        bgDiv.style.transitionProperty = "all";
        bgDiv.style.transitionDuration = time + "s";
      };
    }
  },
  mounted: function() {
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
