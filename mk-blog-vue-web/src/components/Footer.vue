<template>
  <footer>
    <p>{{ msg }}</p>
  </footer>
</template>

<script>
export default {
  name: "app-footer",
  data() {
    return {
      msg: "Copyright © 2020 - MK. All Rights Reserved"
    };
  }
};
</script>
<style scoped>
p {
  position: absolute !important;
  height: 20px;
  left: 45%;
  top: 95%;
  text-align: center;
}
</style>
