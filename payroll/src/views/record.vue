<template>
  <n-space justify="right">
    <n-button type="info" @click="showModal = true">添加部门</n-button>
  </n-space>
  <!-- 模态框 -->
  <n-modal
    v-model:show="showModal"
    class="custom-card"
    preset="card"
    :style="bodyStyle"
    title="添加部门"
    size="huge"
    :bordered="false"
    :segmented="segmented"
  >
    <n-form ref="formRef" :model="department" label-placement="left">
      <n-grid :cols="24" :x-gap="22">
        <n-form-item-gi :span="12" label="系ID" path="facultyId">
          <n-input v-model:value="department.facultyId" placeholder="Input" />
        </n-form-item-gi>
        <n-form-item-gi :span="12" label="部门名" path="departmentName">
          <n-input
            v-model:value="department.departmentName"
            placeholder="Input"
          />
        </n-form-item-gi>
        <n-form-item-gi :span="12" label="部门ID" path="departmentId">
          <n-input
            v-model:value="department.departmentId"
            placeholder="Input"
          />
        </n-form-item-gi>
        <n-form-item-gi :span="12" label="部门经理" path="departmentBossId">
          <n-input
            v-model:value="department.departmentBossId"
            placeholder="Input"
          />
        </n-form-item-gi>
      </n-grid>
      <n-space justify="right">
        <n-button @click="cancelEvent">取消</n-button>
        <n-button type="success" @click="addDepartmentEvent">添加</n-button>
      </n-space>
    </n-form>
  </n-modal>
  <n-space vertical :size="12" class="table">
    <n-data-table :bordered="false" :columns="columns" :data="data" />
  </n-space>
</template>

<script>
import { defineComponent, h, ref } from "vue";
import {
  NSpace,
  NButton,
  NDataTable,
  NModal,
  NForm,
  NInput,
  NFormItemGi,
  NGrid,
  useMessage,
} from "naive-ui";
import {
  addDepartment,
  deleteDepartment,
  getDepartmentList,
} from "@/api/employeeFile";
import router from "../router";
import useEmployee from "../componables/employee";
import { onMounted } from "vue";

const department = ref({
  facultyId: null,
  departmentId: null,
  departmentName: "",
  departmentBossId: null,
});

const message = useMessage();

const createColumns = ({ checkMore }) => {
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
        return [
          h(
            NButton,
            {
              size: "small",
              type: "info",
              secondary: true,
              onClick: () => checkMore(),
            },
            { default: () => "查看" }
          ),
          h(
            NButton,
            {
              style: "margin-left: 6px",
              size: "small",
              type: "warning",
              secondary: true,
              onClick: () => deleteDepartemntEvent(row),
            },
            { default: () => "删除" }
          ),
        ];
      },
    },
  ];
};

//添加部门
const addDepartmentEvent = () => {
  addDepartment(
    department.value.facultyId,
    department.value.departmentId,
    department.value.departmentName,
    department.value.departmentBossId
  )
    .then(({ res, succeed }) => {
      if (succeed) {
        message.success("添加成功");
        cancelEvent();
      } else {
        message.error(res.message);
      }
    })
    .catch((err) => {
      const {
        data: { message: msg },
      } = err.response;
      console.log(msg);
    });
};
//清除表单
const cancelEvent = () => {
  department.value = {
    facultyId: null,
    departmentId: null,
    departmentName: "",
    departmentBossId: null,
  };
};
//删除部门
const deleteDepartemntEvent = () => {
  deleteDepartment(department.value.departmentId)
    .then(({ res }) => {
      message.success("删除成功");
      console.log(res);
    })
    .catch((err) => {
      const {
        data: { message: msg },
      } = err.response;
      console.log(msg);
    });
};

const checkMore = () => {
  router.push({
    path: "/record/",
    query: {
      departmentBossId: 1,
    },
  });
};

export default defineComponent({
  name: "RecordVue",
  components: {
    NSpace,
    NButton,
    NDataTable,
    NModal,
    NForm,
    NInput,
    NFormItemGi,
    NGrid,
  },
  props: {},
  setup() {
    const { getEmployeeList, employeeData } = useEmployee();
    onMounted(() => {
      getDepartmentList();
    });
    return {
      data: employeeData,
      columns: createColumns({
        checkMore,
      }),
      // pagination,
      bodyStyle: {
        width: "600px",
      },
      segmented: {
        content: "soft",
        footer: "soft",
      },
      showModal: ref(false),
      department,
      addDepartmentEvent,
      cancelEvent,
      getEmployeeList,
    };
  },
});
</script>

<style lang="scss" scoped>
.table {
  margin-top: 20px;
}
</style>
