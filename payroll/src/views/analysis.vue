<template>
  <n-grid x-gap="20" :cols="2">
    <n-gi>
      <n-card title="今日打卡" size="large" hoverable>
        <n-space justify="space-around" size="large">
          <n-button strong secondary round type="primary" @click="clockInEvent">
            签到
          </n-button>
          <n-button strong secondary round type="info" @click="clockOutEvent">
            签退
          </n-button>
          <n-button strong secondary round type="warning" @click="dayOffEvent">
            请假
          </n-button>
        </n-space>
      </n-card>
    </n-gi>
    <n-gi>
      <n-card title="我的出勤状况" size="large" hoverable>
        <n-space justify="space-around" size="large">
          <n-button type="warning">
            早退：<span>{{  }}</span>
          </n-button>
          <n-button type="error">
            缺勤：<span>{{  }}</span>
          </n-button>
        </n-space>
      </n-card>
    </n-gi>
  </n-grid>
  <n-space justify="left"><div class="header">考勤情况</div></n-space>
  <n-button type="primary" round @click="getDayOffNumberEvent">测试</n-button>
</template>

<script>
import { defineComponent } from "vue";
import { NCard, NGrid, NGi, NButton, NSpace } from "naive-ui";
import useAnalysis from "../componables/analysis";

export default defineComponent({
  name: "AnalysisVue",
  components: {
    NCard,
    NGrid,
    NGi,
    NButton,
    NSpace,
  },
  props: {},
  setup() {
    const {
      clockInEvent,
      clockOutEvent,
      dayOffEvent,
      getLeaveNumberEvent,
      getDayOffNumberEvent
    } = useAnalysis();
    return {
      clockInEvent,
      clockOutEvent,
      dayOffEvent,
      getLeaveNumberEvent,
      getDayOffNumberEvent
    };
  },
});
</script>

<style lang="scss" scoped>
.header {
  font-size: 18px;
  font-weight: bold;
  padding: 25px 0 15px 0;
}
</style>
