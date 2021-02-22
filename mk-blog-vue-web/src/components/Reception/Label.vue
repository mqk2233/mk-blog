<template>
  <div>
    <Background
        time="1.5"
        height="70"
        url="https://i.loli.net/2020/02/04/iawOIh9gMsNzHAL.jpg"
    />
    <div class="a-bounceinL text">
      <h1 style="font-size: 50px">标签</h1>
      <Tag
          :color="randomColor()"
          size="large"
          v-for="(item, key) in list"
          v-bind:key="key"
      >
        {{ item.labelName }}
      </Tag>
    </div>
  </div>
</template>
<script>
import Background from "../Background";

export default {
  components: {Background},
  data() {
    return {
      list: []
    };
  },
  methods: {
    getLabelList() {
      this.$api.label
          .labelAllList()
          .then(res => {
            this.list = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    randomColor() {
      return (
          "hsl(" +
          Math.round(Math.random() * 360) +
          "," +
          Math.round(Math.random() * 100) +
          "%," +
          Math.round(Math.random() * 80) +
          "%)"
      );
    }
  },
  mounted() {
    this.getLabelList();
  }
};
</script>
<style lang="css" scoped>
.text {
  margin-top: 70vh;
  width: 70vh;
}

.a-bounceinL {
  animation: bounceinL 1s;
  margin-top: 60vh;
  margin-left: 25vh;
}

@keyframes bounceinL {
  0% {
    opacity: 0;
    transform: translateX(-100px);
  }
  60% {
    opacity: 1;
    transform: translateX(30px);
  }
  80% {
    transform: translateX(-10px);
  }
  100% {
    transform: translateX(0);
  }
}
</style>
