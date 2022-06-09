import { getDepartmentList } from "../api/employeeFile";
import { useMessage } from "naive-ui";
import { ref } from "vue";

export default () => { 
  const message = useMessage();

  const employeeData = ref([]);

  const getEmployeeList = () => { 
    const loading = message.loading("正在添加...", { duration: 6000 });
    getDepartmentList(1).then(({ res, succeed }) => {
      loading.destroy();
      if (succeed) {
        console.log(res);
        employeeData.value = res.data;
        
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
  }

  return {
    employeeData,
    getEmployeeList
  }
}