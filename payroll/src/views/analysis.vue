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
            早退：<span>{{ 0 }}</span>
          </n-button>
          <n-button type="error">
            缺勤：<span>{{ 0 }}</span>
          </n-button>
        </n-space>
      </n-card>
    </n-gi>
  </n-grid>
  <!-- <n-button type="primary" round @click="getPersonalAttendanceEvent">方法测试</n-button> -->
  <n-space justify="left"><div class="header">个人考勤表</div></n-space>
  <n-data-table :columns="columns" :data="data" />
  

</template>

<script>
import { defineComponent, ref } from "vue";
import { NCard, NGrid, NGi, NButton, NSpace, NDataTable } from "naive-ui";
import useAnalysis from "../componables/analysis";

export default defineComponent({
  name: "AnalysisVue",
  components: {
    NCard,
    NGrid,
    NGi,
    NButton,
    NSpace,
    NDataTable
  },
  props: {},
  setup() {
    const {
      createColumns,
      clockInEvent,
      clockOutEvent,
      dayOffEvent,
      getLeaveNumberEvent,
      getDayOffNumberEvent,
      getPersonalAttendanceEvent
    } = useAnalysis();
    return {
      data: ref([]),
      columns: createColumns(),
      clockInEvent,
      clockOutEvent,
      dayOffEvent,
      getLeaveNumberEvent,
      getDayOffNumberEvent,
      getPersonalAttendanceEvent
    };
  },
});
</script>

<style lang="scss" scoped>
.header {
  font-size: 16px;
  padding: 25px 0 15px 0;
}
</style>
