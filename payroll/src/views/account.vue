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
      <n-button type="info" @click="isShow">工资明细</n-button>
    </n-space>
  </n-card>
  <n-card style="margin-top: 10px">
    <n-h2 align="center">工资条</n-h2>
    <n-data-table
      :columns="columns"
      :data="accountData"
      :pagination="pagination"
      :bordered="false"
      :loading="loading"
    />
  </n-card>
  <!-- 模态框 -->
  <n-modal v-model:show="showModal">
    <n-card
      style="width: 600px"
      title="基本工资"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
    >
      <n-form
        ref="formRef"
        :model="wage"
        :rules="rules"
        :size="normal"
        label-placement="left"
      >
        <n-grid :cols="24" :x-gap="24">
          <n-form-item-gi :span="12" label="普通员工" path="commonWage">
            <n-input v-model:value="wage.commonWage" placeholder="Input" />
          </n-form-item-gi>
          <n-form-item-gi :span="12" label="部门经理" path="managerWage">
            <n-input v-model:value="wage.managerWage" placeholder="Input" />
          </n-form-item-gi>
        </n-grid>
      </n-form>
    </n-card>
  </n-modal>
</template>

<script>
import { defineComponent, h } from "vue";
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
  NModal,
  NForm,
  NGrid,
  NFormItemGi,
} from "naive-ui";
import useAccount from "../componables/account.js";

const statusInfo = [
  { title: "未发放", type: "error" },
  { title: "已发放", type: "success" },
];
const columns = [
  {
    title: "职工ID",
    key: "employeeId",
    align: "center",
  },
  {
    title: "职工姓名",
    key: "name",
    align: "center",
  },
  {
    title: "基本工资",
    key: "basicWage",
    align: "center",
  },
  {
    title: "奖金",
    key: "bonus",
    align: "center",
  },
  {
    title: "罚款",
    key: "fine",
    align: "center",
  },
  {
    title: "税收",
    key: "tax",
    align: "center",
  },
  {
    title: "总工资",
    key: "sumWage",
    align: "center",
  },
  {
    title: "银行卡号",
    key: "accountNumber",
    align: "center",
  },
  {
    title: "状态",
    key: "status",
    align: "center",
    render(row) {
      return h(
        NTag,
        {
          type: statusInfo[row.status].type,
          secondary: true,
        },
        {
          default: () => statusInfo[row.status].title,
        }
      );
    },
  },
];

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
    NModal,
    NForm,
    NGrid,
    NFormItemGi,
  },
  props: {},
  setup() {
    const { accountData, loading, pagination, wage, showModal, isShow } =
      useAccount();
    return {
      wage,
      accountData,
      columns,
      loading,
      pagination,
      showModal,
      isShow,
    };
  },
});
</script>

<style lang="scss" scoped></style>
