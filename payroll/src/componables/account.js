import { ref } from "vue";

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
  const pagination = ref({
    page: 1,
    pageNum: 1,
    pageSize: 10,
    pageItem: 0,
  });

  return {
    accountData,
    loading,
    pagination,
  };
};
