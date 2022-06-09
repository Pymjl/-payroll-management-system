<template>
  <n-card style="margin-top: 10px">
    <n-h2 align="center">部门人员考勤列表</n-h2>
    <n-data-table
      remote
      :columns="columns"
      :data="checkData"
      :pagination="pagination"
      :bordered="false"
    />
  </n-card>
</template>

<script>
import { defineComponent, reactive, onMounted, ref } from 'vue'
import { NCard, NH2, NDataTable, useMessage } from 'naive-ui'
import { getDeptRecord } from '../api/public'
import router from '../router'

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
    const checkData = ref([])
    const message = useMessage()
    const pagination = reactive({
    page: 1,
    pageSize: 10, //每页条数
    pageCount: 0, //总页数
    itemCount: 0, //总条数
    });
    const deptId = router.currentRoute.value.params.id;
    console.log(deptId);
    const getRecord = () => {
      getDeptRecord(deptId).then(({ res }) => {
        checkData.value = res.data;
      }).catch(err => {
        message.error(err.response.message);
      })
    }
    onMounted(() => {
      getRecord();
    })

    return {
      router,
      checkData,
      columns,
      pagination,
    }
  },
})
</script>

<style lang="scss" scoped>
</style>