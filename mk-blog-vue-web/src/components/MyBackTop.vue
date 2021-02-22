<template>
  <BackTop class="back-top-move">
    <img :src="bg" alt="返回顶部"/>
  </BackTop>
</template>

<script>
import Logo from "../../src/assets/top.png";

export default {
  data() {
    return {
      bg: Logo
    };
  }
};
</script>

<style scoped>
.back-top-move :hover {
  animation: anime-face 5s infinite ease-in-out;
}

@keyframes anime-face {
  2%,
  24%,
  80% {
    transform: translate(0, 2px) rotate(1.5deg);
  }
  4%,
  68%,
  98% {
    transform: translate(0, -1.5px) rotate(-0.5deg);
  }
  38%,
  6% {
    transform: translate(0, 2px) rotate(-1.5deg);
  }
  8%,
  86% {
    transform: translate(0, -1.5px) rotate(-1.5deg);
  }
  10%,
  72% {
    transform: translate(0, 3px) rotate(1.5deg);
  }
  12%,
  64%,
  78%,
  96% {
    transform: translate(0, -0.5px) rotate(1.5deg);
  }
  14%,
  54% {
    transform: translate(0, -1.5px) rotate(1.5deg);
  }
  16% {
    transform: translate(0, -0.5px) rotate(-1.5deg);
  }
  18%,
  22% {
    transform: translate(0, 1px) rotate(-1.5deg);
  }
  20%,
  36%,
  46% {
    transform: translate(0, -1.5px) rotate(2.5deg);
  }
  26%,
  50% {
    transform: translate(0, 1px) rotate(0.5deg);
  }
  28% {
    transform: translate(0, 1px) rotate(1.5deg);
  }
  30%,
  40%,
  62%,
  76%,
  88% {
    transform: translate(0, -0.5px) rotate(2.5deg);
  }
  32%,
  34%,
  66% {
    transform: translate(0, 2px) rotate(-0.5deg);
  }
  42% {
    transform: translate(0, 3px) rotate(-1.5deg);
  }
  44%,
  70% {
    transform: translate(0, 2px) rotate(0.5deg);
  }
  48%,
  74%,
  82% {
    transform: translate(0, -0.5px) rotate(0.5deg);
  }
  52%,
  56%,
  60% {
    transform: translate(0, 3px) rotate(2.5deg);
  }
  58% {
    transform: translate(0, 1px) rotate(2.5deg);
  }
  84% {
    transform: translate(0, 2px) rotate(2.5deg);
  }
  90% {
    transform: translate(0, 3px) rotate(-0.5deg);
  }
  92% {
    transform: translate(0, 1px) rotate(-0.5deg);
  }

  94% {
    transform: translate(0, 3px) rotate(0.5deg);
  }
  0%,
  100% {
    transform: translate(0, 0) rotate(0);
  }
}
</style>
