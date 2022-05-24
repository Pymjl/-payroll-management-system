<template>
  <h1>Vue3+axios+Naive</h1>
  <n-button type="primary">start</n-button>
</template>

<script>
import { defineComponent } from "vue";
import { NButton } from "naive-ui";

export default defineComponent({
  name: "HomePage",
  components: { NButton },
  props: {},
  setup() {
    return {};
  },
});
</script>

<style scoped></style>
