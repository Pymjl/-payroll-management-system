<template>
  <n-card>
    <n-space justify="center">
      <n-h2>部门列表</n-h2>
    </n-space>
    <n-space vertical :size="12" class="table">
      <n-data-table
        remote
        :bordered="false"
        :columns="columns"
        :data="userData"
        :pagination="pagination"
        :loading="loading"
        @update-page="handlePage"
      />
    </n-space>
  </n-card>
</template>

<script>
import { defineComponent, h, ref, onMounted } from "vue";
import {
  NSpace,
  NDataTable,
  NH2,
  NButton,
  useMessage,
  NCard,
  NTag,
  NAvatar,
} from "naive-ui";
import { getUsersList, deleteUser } from "@/api/public";

const status = [
  {
    identity: "普通员工",
    type: "primary",
  },
  {
    identity: "部门经理",
    type: "info",
  },
];
const createColumns = ({ banUser, delUser }) => {
  return [
    {
      title: "头像",
      key: "avatar",
      align: "center",
      render(row) {
        return h(
          NAvatar,
          {
            size: "small",
            round: true,
            src: row.avatar,
          },
          { default: () => row.avatar }
        );
      },
    },
    {
      title: "ID",
      key: "id",
      align: "center",
    },
    {
      title: "姓名",
      key: "nickname",
      align: "center",
    },
    {
      title: "性别",
      key: "gender",
      align: "center",
    },
    {
      title: "身份",
      key: "identity",
      align: "center",
      render(row) {
        return h(
          NTag,
          {
            type: status[row.identity].type,
            secondary: true,
          },
          { default: () => status[row.identity].identity }
        );
      },
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
              type: "warning",
              secondary: true,
              onClick: () => banUser(row),
            },
            { default: () => "封禁" }
          ),
          h(
            NButton,
            {
              style: "margin-left: 6px",
              size: "small",
              type: "error",
              secondary: true,
              onClick: () => delUser(row),
            },
            { default: () => "删除" }
          ),
        ];
      },
    },
  ];
};

export default defineComponent({
  name: "UserManagement",
  components: { NSpace, NDataTable, NH2, NCard },
  props: {},
  setup() {
    const message = useMessage();
    const userData = ref([]);
    const pagination = ref({
      page: 1,
      pageSize: 10,
      itemCount: 0,
    });
    const loading = ref(false);
    const getUser = () => {
      loading.value = true;
      getUsersList(pagination.value.page, pagination.value.pageSize)
        .then(({ res }) => {
          userData.value = res.records;
          pagination.value.itemCount = res.totalElements;
          loading.value = false;
        })
        .catch((err) => {
          message.error(err.message);
          loading.value = false;
        });
    };
    onMounted(() => {
      getUser();
    });
    const handlePage = (page) => {
      pagination.value.page = page;
      getUser();
    };

    return {
      columns: createColumns({
        delUser(row) {
          message.info(row.id);
          deleteUser(row.id)
            .then(() => {
              message.success("删除成功");
              getUser();
            })
            .catch((err) => {
              message.error(err.message);
            });
        },
      }),
      userData,
      pagination,
      handlePage,
    };
  },
});
</script>

<style scoped></style>
