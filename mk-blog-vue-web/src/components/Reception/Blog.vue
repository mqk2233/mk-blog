<template>
  <div>
    <Background
        time="1.5"
        height="70"
        url="https://i.loli.net/2020/02/02/ji3LWXYzlGacmMO.jpg"
    />
    <div class="a-bounceinB">
      <mavon-editor
          class="card"
          :value="articleEdit.contentMd"
          :subfield="prop.subfield"
          :defaultOpen="prop.defaultOpen"
          :toolbarsFlag="prop.toolbarsFlag"
          :editable="prop.editable"
          :scrollStyle="prop.scrollStyle"
      />
    </div>
    <div ref="toc" class="catalog" v-html="articleEdit.catalog"/>
    <MyBackTop/>
  </div>
</template>

<script>
import Background from "../Background";
import MyBackTop from "../MyBackTop";

export default {
  name: "Blog",
  data() {
    return {
      //文章对象
      articleEdit: {
        id: 0,
        title: "",
        contentMd: "",
        contentHtml: "",
        catalog: "",
        status: 0,
        isProhibit: 1,
        categoryId: 0,
        labelIds: [],
        categoryIds: []
      }
    };
  },
  methods: {
    getArticleById() {
      this.$api.article
          .getArticleById(this.$router.currentRoute.params.id)
          .then(res => {
            this.articleEdit = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    moveCatalog() {
      let scrollPosition;
      let toc = this.$refs.toc;
      window.addEventListener("scroll", function () {
        scrollPosition = window.scrollY;
        if (scrollPosition > 450) {
          toc.classList.remove("catalog");
          toc.classList.add("toc");
        } else {
          toc.classList.add("catalog");
          toc.classList.remove("toc");
        }
      });
    }
  },
  mounted: function () {
    this.getArticleById();
    this.moveCatalog();
  },
  components: {Background, MyBackTop},
  computed: {
    prop() {
      return {
        subfield: false, // 单双栏模式
        defaultOpen: "preview", //edit： 默认展示编辑区域 ， preview： 默认展示预览区域
        editable: false,
        toolbarsFlag: false,
        scrollStyle: true
      };
    }
  }
};
</script>

<style scoped>
.card {
  margin-top: 65vh;
  width: 110vh;
}

.catalog {
  opacity: 0;
  transition: all 0.2s ease-out;
}

.toc {
  opacity: 1;
  position: fixed;
  left: 175vh;
  top: 7vh;
  transition: all 0.2s ease-out;
}

.a-bounceinB {
  animation: bounceinB 1s;
}

@keyframes bounceinB {
  0% {
    opacity: 0;
    transform: translateY(100px);
  }
  60% {
    opacity: 1;
    transform: translateY(-30px);
  }
  80% {
    transform: translateY(10px);
  }
  100% {
    transform: translateY(0);
  }
}
</style>
