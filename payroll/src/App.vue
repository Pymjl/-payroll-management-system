<template>
  <n-message-provider>
    <router-view></router-view>
  </n-message-provider>
</template>

<script>
import { defineComponent } from "vue";
import { NMessageProvider } from "naive-ui";

export default defineComponent({
  name: "App",
  components: {
    NMessageProvider,
  },
});
</script>
