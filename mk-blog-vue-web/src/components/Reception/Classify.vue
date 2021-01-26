<template>
  <div>
    <Background
      time="1.5"
      height="70"
      url="https://i.loli.net/2020/02/04/t9Ts4OjuVlmNFrv.jpg"
    />
    <div class="a-bounceinL text">
      <h1 style="font-size: 50px">分类</h1>
      <Tree :data="data5" style="font-size: 15px" />
    </div>
  </div>
</template>
<script>
import Background from "../Background";
export default {
  components: { Background },
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
          this.$Notice.warning({ title: err.data.msg });
        });
    }
  },
  mounted: function() {
    this.getCategoryList();
  }
};
</script>
<style lang="css" scoped>
.text {
  margin-top: 70vh;
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
