import { ref, reactive } from "vue";
import { getAllWageSheet, getBasicWage } from "@/api/finance";

export default () => {
  const accountData = ref([
    {
      employeeId: 1,
      name: "张三",
      account: "123456789",
      basicWage: "6000",
      bonus: "1000",
      fine: "0",
      tax: "60",
      sumWage: "6940",
      accountNumber: "123456789",
      status: 1,
    },
  ]);
  const loading = ref(false);
  const pagination = reactive({
    page: 1,
    pageNum: 1,
    pageSize: 10,
    pageItem: 0,
  });
  // 获取基本工资
  const wage = ref({
    basicWage: "",
    managerWage: "",
  });
  const getWage = () => {
    getBasicWage(1, 10)
      .then(({ res }) => {
        wage.value = res.records;
        console.log(wage.value);
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
  // 获取所有员工的工资条
  const getAllWage = () => {
    getAllWageSheet(pagination.pageNum, pagination.pageSize).then(({ res }) => {
      accountData.value = res.records;
      pagination.pageItem = res.total;
    });
  };
  getAllWage();

  return {
    wage,
    accountData,
    loading,
    pagination,
    showModal,
    isShow,
  };
};
