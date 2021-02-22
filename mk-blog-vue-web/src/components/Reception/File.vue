<template>
  <div class="base-div">
    <Background
        time="1.5"
        height="70"
        url="https://img.laoooo.cn:88/2019/07/20/5d326d07662e9.png"
    />
    <div class="a-bounceinL">
      <h1 style="font-size: 50px">归档</h1>
      <Timeline style="margin-top: 5px">
        <TimelineItem v-for="(item, key) in list" v-bind:key="key">
          <p class="time">{{ item.month }}</p>
          <p
              class="content"
              v-for="(item1, key) in item.articles"
              v-bind:key="key"
          >
            <Tag color="blue" size="large" style="font-size: 14px">{{
                item1.createTime | date
              }}
            </Tag
            >
            <span class="dd" @click="
              $router.push({ name: 'blogDetails', params: { id: item1.id } })
            ">{{ item1.title }}</span>
          </p>
        </TimelineItem>
      </Timeline>
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
    getTimeAis() {
      this.$api.article
          .getTimeAis()
          .then(res => {
            this.list = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    }
  },
  mounted: function () {
    this.getTimeAis();
  },
  filters: {
    date: function (time) {
      return time.slice(0, 10);
    }
  }
};
</script>
<style lang="css" scoped>
.base-div {
  display: flex;
  z-index: 3;
}

.dd:hover {
  border-bottom: 1px solid #5cadff;
}

.time {
  font-size: 20px;
  font-weight: bold;
}

.content {
  padding-left: 5px;
  cursor: pointer;
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
