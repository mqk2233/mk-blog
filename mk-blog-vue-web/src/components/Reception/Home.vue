<template>
  <div class="blog">
    <Background :index="Math.floor(Math.random() * (17 - 1 + 1) + 1)">
      <template v-slot:text class="background-text">
        <h1>MK' Blog</h1>
        <h2>记录足迹，留住美好。</h2>
      </template>
    </Background>
    <div class="content">
      <Card class="card-scale" v-for="(item, key) in list" :key="key">
        <p
            slot="title"
            @click="
            $router.push({ name: 'blogDetails', params: { id: item.id } })
          "
            class="title"
        >
          {{ item.title }}
        </p>
        <div slot="extra">
          <span v-for="(labels, key) in item.labelNames" :key="key">
            <Tag color="blue">{{ labels }}</Tag>
          </span>
        </div>
        <p v-html="item.text"/>
        <Divider/>
        <div>
          <img
              :src="time"
              alt="写作时间"
              class="time-img"
          />
          <span class="time-span">{{ item.createTime }}</span>
          <b
              @click="
              $router.push({ path: 'blogDetails', params: { id: item.id } })
            "
              class="jump"
          >。。。</b
          >
        </div>
      </Card>
      <Spin v-show="bottom" >已经到底啦~~🤪</Spin>
      <Spin v-show="loading">加载中~~😫</Spin>
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
      bottom: false,
      total: 0,
      list: [],
      searchArticleName: null,
      pageSize: 5,
      currentPage: 0
    };
  },
  methods: {
    async getArticleList() {
      this.loading = true;
      this.currentPage++;
      await this.$api.article
          .articleAdminList("", this.$route.params.labelId, this.pageSize, this.currentPage)
          .then(res => {
            if (res.data.code === 200) {
              this.list = this.list.concat(res.data.data);
              this.total = parseInt(res.data.total);
              this.loading = false;
            }
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
      console.log(this.total)
      if (this.pageSize * this.currentPage >= this.total) {
        this.bottom = true;
      }
    },
    scroll() {
      window.onscroll = () => {
        //变量scrollTop是滚动条滚动时,距离顶部的距离
        let scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        //变量windowHeight是可视区的高度
        let windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
        //变量scrollHeight是滚动条的总高度
        let scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
        //滚动条到底部的条件
        if (scrollTop + windowHeight >= scrollHeight-1 && this.bottom !== true) {
          //写后台加载数据的函数
          this.getArticleList()
        }
      }
    }
  },
  mounted() {
    this.scroll()
  }
}
;
</script>
<style scoped>
.card-scale:hover {
  transform: scale(1.05);
  margin-top: 30px;
}

.card-scale {
  width: 100vh;
  margin-top: 30px;
  margin-bottom: 5vh;
}

h1,
h2 {
  position: relative;
  font-weight: 300;
  font-size: 80px;
  top: 30%;
  color: white;
  font-family: none,sans-serif;
  text-shadow: 3px 0 5px #f8f8f9;
}

h2 {
  font-size: 40px;
}

.background-text {
  margin: 0 auto;
}

.blog {
  margin: 0 auto;
}

.content {
  margin-top: 110vh;
}

.title {
  cursor: pointer
}

.time-img {
  width: 20px;
  margin-right: 5px;
}

.time-span {
  position: absolute;
}

.jump {
  cursor: pointer;
  position: absolute;
  right: 15px;
  text-shadow: 0 0 2px;
}

.loading{
  position: relative !important;
  top: 5vh;
}

</style>
