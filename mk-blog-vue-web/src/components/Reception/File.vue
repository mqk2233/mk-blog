<template>
  <div class="base-div">
    <Background
        :index="Math.floor(Math.random() * (17 - 1 + 1) + 1)"
        time="1.5"
        height="70"
    />
    <div class="a-bounceinL">
      <h1 class="h1-text">归档</h1>
      <Timeline class="time-line">
        <TimelineItem v-for="(item, key) in list" v-bind:key="key">
          <p class="time">{{ item.month }}</p>
          <p
              class="content"
              v-for="(item1, key) in item.articles"
              v-bind:key="key"
          >
            <Tag color="blue" size="large" class="tag-font-size">{{
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
  mounted() {
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
.h1-text {
  font-size: 50px
}

.time-line {
  margin-top: 5px
}

.tag-font-size {
  font-size: 14px
}

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
  margin: 80vh 30vh 10vh 70vh;
  width: 90vh;
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
