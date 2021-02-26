<template>
  <div>
    <Background
        :index="Math.floor(Math.random() * (17 - 1 + 1) + 1)"
        time="1.5"
        height="70"
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
    <Anchor show-ink ref="toc" class="catalog toc">
      <AnchorLink v-if="articleEdit.catalogTree.length !== 0" v-for="(item1, index1) in articleEdit.catalogTree"
                  :href="item1.id" :title="item1.title">
        <AnchorLink v-if="articleEdit.catalogTree[index1].childList.length !== 0"
                    v-for="(item2, index2) in articleEdit.catalogTree[index1].childList" :href="item2.id"
                    :title="item2.title">
          <AnchorLink v-if="articleEdit.catalogTree[index1].childList[index2].childList.length !== 0"
                      v-for="(item3, index3) in articleEdit.catalogTree[index1].childList[index2].childList"
                      :href="item3.id" :title="item3.title">
            <AnchorLink
                v-if="articleEdit.catalogTree[index1].childList[index2].childList[index3].childList.length !== 0"
                v-for="(item4, index4) in articleEdit.catalogTree[index1].childList[index2].childList[index3].childList[index4]"
                :href="item4.id" :title="item4.title">
              <AnchorLink
                  v-if="articleEdit.catalogTree[index1].childList[index2].childList[index3].childList[index4].childList.length !== 0"
                  v-for="(item5, index5) in articleEdit.catalogTree[index1].childList[index2].childList[index3].childList[index4].childList[index5]"
                  :href="item5.id" :title="item5.title">
                <AnchorLink
                    v-if="articleEdit.catalogTree[index1].childList[index2].childList[index3].childList[index4].childList[index5].childList.length !== 0"
                    v-for="item6 in articleEdit.catalogTree[index1].childList[index2].childList[index3].childList[index4].childList[index5]"
                    :href="item6.id" :title="item6.title">
                </AnchorLink>
              </AnchorLink>
            </AnchorLink>
          </AnchorLink>
        </AnchorLink>
      </AnchorLink>
    </Anchor>
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
      anchor: {
        aid: 0,
        title: ""
      },
      anchorTree: [],
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
        categoryIds: [],
        catalogTree: []
      }
    };
  },
  methods: {
    getArticleById() {
      this.$api.article
          .getArticleById(this.$route.params.id)
          .then(res => {
            this.articleEdit = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    moveCatalog() {
      let scrollPosition;
      let toc = this.$refs.toc.$el;
      window.addEventListener("scroll", function () {
        scrollPosition = window.scrollY;
        if (scrollPosition > 470) {
          toc.classList.remove("catalog");

          toc.classList.add("toc");
        } else {
          toc.classList.add("catalog");
          toc.classList.remove("toc");
        }
      });
    }
  },
  mounted() {
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
  margin: 80vh 30vh 10vh 49vh;
  width: 110vh;
}

.catalog {
  opacity: 0;
  transition: all 0.5s ease-out;
}

.toc {
  opacity: 1;
  position: fixed;
  left: 165vh;
  top: 10vh;
  transition: all 0.5s ease-out;
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
