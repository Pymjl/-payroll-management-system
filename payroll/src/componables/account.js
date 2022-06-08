import { ref, reactive } from "vue";
import { useMessage } from "naive-ui";
import {
  getAllWageSheet,
  getBasicWage,
  updateBasicWage,
  getWageSheetById,
} from "@/api/finance";

export default () => {
  const message = useMessage();

  const accountData = ref([]);
  const loading = ref(false);
  const pagination = reactive({
    page: 1,
    pageSize: 10, //每页条数
    pageCount: 0, //总页数
    itemCount: 0, //总条数
  });
  // 获取基本工资
  const wage = ref({
    commonWage: "",
    managerWage: "",
  });
  const getWage = () => {
    getBasicWage()
      .then(({ res }) => {
        wage.value.commonWage = res.data.commonWage;
        wage.value.managerWage = res.data.managerWage;
      })
      .catch((err) => {
        // 更好理解的一种写法->解构err->message
        const {
          data: { message: msg },
        } = err.response;
        console.log(msg);
      });
  };
  getWage();
  // 查看基本工资
  const showModal = ref(false);
  const isShow = () => {
    showModal.value = true;
  };
  // 修改基本工资
  const updateWageEvent = () => {
    updateBasicWage(1, wage.value.commonWage, wage.value.managerWage).then(
      ({ succeed }) => {
        if (succeed) {
          message.success("修改成功");
          getWage();
          showModal.value = false;
        }
      }
    );
  };
  // 取消
  const cancel = () => {
    showModal.value = false;
  };

  // 获取所有员工的工资条
  const getAllWage = () => {
    loading.value = true;
    getAllWageSheet(pagination.page, pagination.pageSize).then(({ res }) => {
      loading.value = false;
      accountData.value = res.records;
      pagination.itemCount = res.totalElements;
      console.log(res);
    });
  };
  getAllWage();
  // 翻页
  const updatePage = (page) => {
    pagination.page = page;
    getAllWage();
  };

  // 根据id获取某个员工的工资条
  const userId = ref(null);
  const getWageById = () => {
    loading.value = true;
    getWageSheetById(userId.value).then(({ res }) => {
      loading.value = false;
      accountData.value = [res.data];
      pagination.itemCount = 1;
    });
  };

  return {
    wage,
    accountData,
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
};
