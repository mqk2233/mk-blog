<template>
  <div style="margin: 0 auto;">
    <Background url="https://i.loli.net/2020/02/02/ji3LWXYzlGacmMO.jpg">
      <template v-slot:text style="margin: 0 auto;">
        <h1>MK' Blog</h1>
        <h2>记录足迹，留住美好。</h2>
      </template>
    </Background>
    <div style="margin-top: 90vh;">
      <Card class="card-scale" v-for="item in list" :key="item.id">
        <p
            slot="title"
            @click="
            $router.push({ name: 'blogDetails', params: { id: item.id } })
          "
            style="cursor: pointer"
        >
          {{ item.title }}
        </p>
        <div slot="extra">
          <span v-for="(labels, key) in item.labelNames" v-bind:key="key">
            <Tag color="blue">{{ labels }}</Tag>
          </span>
        </div>
        <p v-html="item.text"/>
        <Divider/>
        <div>
          <img
              :src="time"
              alt="写作时间"
              style="width: 20px;margin-right: 5px;"
          />
          <span style="position: absolute;">{{ item.createTime }}</span>
          <b
              @click="
              $router.push({ path: 'blogDetails', params: { id: item.id } })
            "
              style="cursor:pointer;position: absolute;right: 15px;text-shadow: 0 0 2px;"
          >。。。</b
          >
        </div>
      </Card>
      <Page :total="total" class-name="page"/>
      <MyBackTop/>
    </div>
  </div>
</template>
<script>
import Time from "../../../src/assets/time.png";
import Background from "../Background";
import MyBackTop from "../MyBackTop";

export default {
  components: {Background, MyBackTop},
  data() {
    return {
      img: "",
      time: Time,
      loading: true,
      total: 0,
      list: [],
      searchArticleName: null,
      pageSize: 5,
      currentPage: 1
    };
  },
  methods: {
    // 文章列表
    getArticleList() {
      this.$api.article
          .articleAdminList("", 0, this.pageSize, this.currentPage)
          .then(res => {
            this.list = res.data.data;
            this.total = parseInt(res.data.total);
            this.loading = false;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    }
  },
  mounted: function () {
    this.getArticleList();
  }
};
</script>
<style scoped>
.card-scale:hover {
  transform: scale(1.05);
  width: 100%;
  margin-top: 30px;
}

.card-scale {
  width: 100%;
  margin-top: 30px;
}

h1,
h2 {
  position: relative;
  font-weight: 300;
  font-size: 80px;
  top: 30%;
  color: white;
  font-family: none;
  /*opacity: 0;*/
  text-shadow: 3px 0 5px #f8f8f9;
}

.page {
  margin-top: 5vh;
  text-align: center;
}

h2 {
  font-size: 40px;
}
</style>
