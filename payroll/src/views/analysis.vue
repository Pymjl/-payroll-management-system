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
            早退：<span>{{ leaveNumber }}</span>
          </n-button>
          <n-button type="error">
            缺勤：<span>{{ dayOffNumber }}</span>
          </n-button>
        </n-space>
      </n-card>
    </n-gi>
  </n-grid>
  
  <n-space justify="center"><div class="header">个人考勤表</div></n-space>
  <n-data-table 
    remote
    :columns="columns" 
    :data="data" 
    :pagination="pagination"
    :loading="loading"
    @update-page="pageChangeEvent"
  />

</template>

<script>
import { defineComponent, onMounted, h } from "vue";
import { NCard, NGrid, NGi, NButton, NSpace, NDataTable, NTag } from "naive-ui";
import useAnalysis from "../componables/analysis";

const statusInfo = [
  { title: "早退", type: "warning" },
  { title: "缺勤", type: "error" },
  { title: "正常", type: "success" },
];

const createColumns = () => {
    return [
      {
        title: '签到时间',
        key: 'createTime'
      },
      {
        title: '签退时间',
        key: 'updateTime'
      },
      {
        title: '工作时长(小时）',
        key: 'workingHours'
      },
      {
        title: "工作状态",
        key: "status",
        render(row) {
          return h(
            NTag,
            {
              type: statusInfo[row.status + 1].type,
              secondary: true,
            },
            {
              default: () => statusInfo[row.status + 1].title,
            }
          );
        },
      },
    ]
  }

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
      leaveNumber,
      dayOffNumber,
      accountData,
      pagination,
      clockInEvent,
      clockOutEvent,
      dayOffEvent,
      getDayOffEvent,
      getLeaveEvent,
      getAttendanceEvent,
      pageChangeEvent
      // pageChangeEvent
    } = useAnalysis();
    
    onMounted(() => {
      //查询早退
      getLeaveEvent();

      //查询缺勤
      getDayOffEvent();

      //出勤列表
      getAttendanceEvent();
    });
    return {
      data: accountData,
      columns: createColumns(),
      pagination,
      leaveNumber,
      dayOffNumber,
      clockInEvent,
      clockOutEvent,
      dayOffEvent,
      getDayOffEvent,
      getLeaveEvent,
      getAttendanceEvent,
      pageChangeEvent
    };
  },
});
</script>

<style lang="scss" scoped>
.header {
  font-size: 18px;
  padding: 35px 0 15px 0;
}
</style>
