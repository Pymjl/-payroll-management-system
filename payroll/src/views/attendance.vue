<template>
  <n-space justify="center">
    <n-H2>部门列表</n-H2>
  </n-space>
  <n-space vertical :size="12" class="table">
    <n-data-table
      :bordered="false"
      :columns="columns"
      :data="attData"
      :pagination="pagination"
    />
  </n-space>
</template>

<script>
import { defineComponent, h, ref } from "vue";
import {
  NSpace,
  NDataTable,
  NH2,
  NButton,
  useMessage
} from "naive-ui";
import useAtten from '../componables/attendance.js';
import router from '../router';
import ls from '../utils/ls';


const createColumns = ({
  checkMore
}) => {
  return [
    {
      title: "部门ID",
      key: "departmentID",
      align: "center",
    },
    {
      title: "部门名",
      key: "departmentName",
      align: "center",
    },
    {
      title: "部门人数",
      key: "departmentNum",
      align: "center",
    },
    {
      title: "部门经理",
      key: "departmentBossId",
      align: "center",
    },
    {
      title: "操作",
      key: "actions",
      align: "center",
      render(row) {
        return h(NButton, {
          size: "small",
          type: "info",
          secondary: true,
          onClick: () => checkMore(row)
        }, 
        { default: () => "查看" })
      }
    }
  ];
};


export default defineComponent({
  name: "RecordVue",
  components: {
    NSpace,
    NDataTable,
    NH2
  },
  props: {},
  setup() {
    const message = useMessage();
    const { attData, pagination } = useAtten();
    return {
      router,
      attData,
      columns: createColumns({
        checkMore(rowData) {
          if (ls.getItem("user").id === rowData.departmentBossId) {
            router.push(`/attendance/${rowData.departmentID}`)
          } else {
            message.error("您不是该部门经理，无法查看该部门的考勤记录")
          }
        }
      }),
      pagination,
      bodyStyle: {
        width: "600px"
      },
      segmented: {
        content: "soft",
        footer: "soft"
      },
      showModal: ref(false),
    };
  },
});
</script>

<style lang="scss" scoped>
.table {
  margin-top: 20px;
}
</style>

