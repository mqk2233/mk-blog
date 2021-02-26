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
    let imgs = ['https://i.loli.net/2021/02/25/H1GtlPXxIS6m2rb.jpg',
      'https://i.loli.net/2021/02/25/Dfc7pWsQkObNvma.jpg',
      'https://i.loli.net/2021/02/25/jlBOYnitTsxKSI4.jpg',
      'https://i.loli.net/2021/02/25/yPI2s1VMrK9YkOF.jpg',
      'https://i.loli.net/2021/02/25/SCcojT3GgEbnMNF.jpg',
      'https://i.loli.net/2021/02/25/tpOgsqYa152djCR.jpg',
      'https://i.loli.net/2021/02/25/oX1jS8R39tICAv7.jpg',
      'https://i.loli.net/2021/02/25/tsXcQwZTNb6Y4ia.jpg',
      'https://i.loli.net/2021/02/25/Tt5yRCwFfYjWHES.jpg',
      'https://i.loli.net/2021/02/25/36cd2GDbtsSZXek.jpg',
      'https://i.loli.net/2021/02/25/CSEtW92ZiqkLvnR.jpg',
      'https://i.loli.net/2021/02/25/nqW1okhAgVMdrms.jpg',
      'https://i.loli.net/2021/02/25/oYhQT8vRqzAc3ua.jpg',
      'https://i.loli.net/2021/02/25/EADHfwdTYKPn3iv.jpg',
      'https://i.loli.net/2021/02/25/vwchWTzDpPNiH3U.jpg',
      'https://i.loli.net/2021/02/25/mCrklJ3bp84IPHD.jpg',
      'https://i.loli.net/2021/02/25/l3DSE8rPtJuNy5x.jpg',
      'https://i.loli.net/2021/02/25/mxXLqtNYQ7OMboc.jpg'];
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
