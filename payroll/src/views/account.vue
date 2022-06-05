<template>
  <n-card>
    <n-space justify="space-around">
      <n-input-group style="width: 800px">
        <n-select
          :style="{ width: '10%' }"
          :options="selectOptions"
          placeholder="部门"
        />
        <n-input :style="{ width: '50%' }" placeholder="根据ID查询" />
        <n-button type="primary"> 搜索 </n-button>
      </n-input-group>
      <n-button type="info">工资明细</n-button>
    </n-space>
  </n-card>
  <n-card style="margin-top: 10px">
    <n-h2 align="center">工资条</n-h2>
    <n-data-table
      :columns="columns"
      :data="data"
      :pagination="pagination"
      :bordered="false"
      :loading="loading"
    />
  </n-card>
</template>

<script>
import { defineComponent, ref, onMounted, toRaw, h } from "vue";
import { getBasicWage } from "../api/finance.js";
import {
  NCard,
  NInputGroup,
  NInput,
  NButton,
  NSelect,
  NSpace,
  NDataTable,
  NTag,
  NH2,
} from "naive-ui";
import useAccount from "../componables/account.js";

const statusInfo = [
  { title: "未发放", type: "error" },
  { title: "已发放", type: "success" },
];
const createColumns = ({ sendMail }) => {
  return [
    {
      title: "职工ID",
      key: "employeeId",
    },
    {
      title: "职工姓名",
      key: "name",
    },
    {
      title: "基本工资",
      key: "basicWage",
    },
    {
      title: "奖金",
      key: "bonus",
    },
    {
      title: "罚款",
      key: "fine",
    },
    {
      title: "税收",
      key: "tax",
    },
    {
      title: "总工资",
      key: "sumWage",
    },
    {
      title: "银行卡号",
      key: "accountNumber",
    },
    {
      title: "状态",
      key: "status",
      render(row) {
        return h(
          NTag,
          {
            style: {
              marginRight: "6px",
            },
            type: statusInfo[row.status].type,
            secondary: true,
          },
          {
            default: () => statusInfo[row.status].title,
          }
        );
      },
    },
    {
      title: "Action",
      key: "actions",
      render(row) {
        return h(
          NButton,
          {
            size: "small",
            onClick: () => sendMail(row),
          },
          { default: () => "Send Email" }
        );
      },
    },
  ];
};

export default defineComponent({
  name: "AccountVue",
  components: {
    NCard,
    NInputGroup,
    NInput,
    NButton,
    NSelect,
    NSpace,
    NDataTable,
    NH2,
  },
  props: {},
  setup() {
    const { accountData, loading, pagination } = useAccount();
    const wage = ref(null);
    onMounted(() => {
      getBasicWage(1, 10)
        .then(({ res }) => {
          wage.value = toRaw(res.records);
          console.log(wage.value);
        })
        .catch((err) => {
          // 更好理解的一种写法->解构err->message
          const {
            data: { message: msg },
          } = err.response;
          console.log(msg);
        });
    });
    return {
      wage,
      data: accountData,
      columns: createColumns({
        sendMail: () => {},
      }),
      loading,
      pagination,
    };
  },
});
</script>

<style lang="scss" scoped>
// .n-input-group {
//   margin-left: 150px;
// }
</style>
