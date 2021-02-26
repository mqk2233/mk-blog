<template>
  <div>
    <Background
        :index="Math.floor(Math.random() * (17 - 1 + 1) + 1)"
        time="1.5"
        height="70"
    />
    <div class="a-bounceinL text">
      <h1 class="h1-text">标签</h1>
      <Tag
          checkable
          class="tag"
          :color="randomColor()"
          size="large"
          v-for="(item, key) in list"
          v-bind:key="key"
          @on-change="jump(item)"
      >
        {{ item.labelName }}({{ item.num }})
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
    jump(label) {
      this.$router.push({name: 'blog', params: {labelId: label.id}})
    },
    randomColor() {
      return (
          "hsl(" +
          Math.round(Math.random() * 360) +
          "," +
          Math.round(Math.random() * 100) +
          "%," +
          60 +
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

.h1-text {
  font-size: 50px
}

.a-bounceinL {
  animation: bounceinL 1s;
  margin: 80vh 30vh 10vh 60vh;
  width: 90vh;
}

.tag {
  cursor: pointer
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
