<template>
  <n-card>
    <n-space justify="space-around">
      <n-input-group style="width: 800px">
        <n-select
          :style="{ width: '10%' }"
          :options="selectOptions"
          placeholder="职工ID"
        />
        <n-input
          :style="{ width: '50%' }"
          placeholder="请输入需要查询的内容..."
          v-model:value="userId"
        />
        <n-button @click="getWageById"> 搜索 </n-button>
        <n-button type="primary" @click="isShow">工资明细</n-button>
      </n-input-group>
    </n-space>
  </n-card>
  <n-card style="margin-top: 10px">
    <n-h2 align="center">工资条</n-h2>
    <n-data-table
      remote
      :columns="columns"
      :data="accountData"
      :pagination="pagination"
      :bordered="false"
      :loading="loading"
      @update-page="updatePage"
    />
  </n-card>
  <!-- 模态框 -->
  <n-modal v-model:show="showModal">
    <n-card
      style="width: 650px"
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
        :size="small"
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
      <n-space justify="flex-end">
        <n-button type="primary" @click="updateWageEvent">保存</n-button>
        <n-button style="margin-left: 5px" @click="cancel">取消</n-button>
      </n-space>
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
    const {
      accountData,
      loading,
      pagination,
      wage,
      showModal,
      userId,
      isShow,
      updateWageEvent,
      cancel,
      updatePage,
      getWageById,
    } = useAccount();
    return {
      wage,
      accountData,
      columns,
      loading,
      pagination,
      showModal,
      userId,
      isShow,
      updateWageEvent,
      cancel,
      updatePage,
      getWageById,
    };
  },
});
</script>

<style lang="scss" scoped></style>
