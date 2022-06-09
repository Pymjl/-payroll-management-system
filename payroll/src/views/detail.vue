<template>
  <n-card style="margin-top: 10px">
    <n-h2 align="center">部门人员列表</n-h2>
    <n-data-table
      remote
      :columns="columns"
      :data="accountData"
      :pagination="pagination"
      :bordered="false"
    />
  </n-card>
</template>

<script>
import { defineComponent, reactive } from 'vue'
import { NCard, NH2, NDataTable } from 'naive-ui'

// const employeeData = ref([])

const pagination = reactive({
    page: 1,
    pageSize: 10, //每页条数
    pageCount: 0, //总页数
    itemCount: 0, //总条数
  });

const columns = [
  {
    title: "职工ID",
    key: "staffId",
    align: "center",
  },
  {
    title: "职工姓名",
    key: "staffName",
    align: "center",
  },
  {
    title: "部门ID",
    key: "staffDepartmentId",
    align: "center",
  },
  {
    title: "所属部门",
    key: "staffDepartmentName",
    align: "center",
  },
  {
    title: "职位",
    key: "staffPosition",
    align: "center",
  },
];


export default defineComponent({
  name: 'DetailVue',
  components: {
    NCard,
    NH2,
    NDataTable
  },
  props: {},
  setup() {

    return {
      columns,
      pagination,
    }
  },
})
</script>

<style lang="scss" scoped>
</style>