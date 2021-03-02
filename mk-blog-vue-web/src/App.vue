<style>
#app {
  height: 100%;
  width: 100%;
  display: none;
}

body,
html {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>
<template>
  <div id="app">
    <router-view/>
  </div>
</template>
<script>
export default {
  data() {
    return {
      count: 0
    }
  },
  beforeCreate() {
    console.log("%c MK-BLOG %c v".concat("1.0"," %c"),
        'background: #35495e;' +
        ' padding: 1px;' +
        ' border-radius: 3px 0 0 3px;' +
        ' color: #fff',
        'background: #00bcd4; ' +
        'padding: 1px; ' +
        'border-radius: 0 3px 3px 0; ' +
        'color: #fff',
        'background: transparent');
    let imgs = ['https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/1.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/2.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/3.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/4.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/5.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/6.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/7.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/8.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/9.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/10.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/11.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/12.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/13.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/14.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/15.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/16.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/17.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/18.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/19.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/20.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/21.jpg',
      'https://cdn.jsdelivr.net/gh/mqk2233/blog-file/img/22.jpg'];
    for (let img of imgs) {
      let image = new Image();
      image.src = img;
      image.onload = () => {
        this.count++;
        if (this.count === 18) {
          setTimeout(function () {
            document.body.removeChild(document.getElementById("appLoading"));
            document.getElementById("app").style.display = "block";
          }, 500);
        }
      };
    }
  }
};
</script>
