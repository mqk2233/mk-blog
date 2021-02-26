<template>
  <div>
    <Background
        :index="Math.floor(Math.random() * (17 - 1 + 1) + 1)"
        time="1.5"
        height="70"
    />
    <div class="a-bounceinL">
      <h1 class="h1-text">分类</h1>
      <Tree :data="data5" class="tree-text"/>
    </div>
  </div>
</template>
<script>
import Background from "../Background";

export default {
  components: {Background},
  data() {
    return {
      data5: []
    };
  },
  methods: {
    getCategoryList() {
      this.$api.category
          .categoryList()
          .then(res => {
            this.data5 = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    }
  },
  mounted() {
    this.getCategoryList();
  }
};
</script>
<style lang="css" scoped>

.h1-text {
  font-size: 50px
}

.tree-text {
  font-size: 15px
}

.a-bounceinL {
  animation: bounceinL 1s;
  margin: 80vh 30vh 10vh 80vh;
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
