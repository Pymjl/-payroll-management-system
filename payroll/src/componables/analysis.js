import { useMessage } from "naive-ui";
import { clockIn, clockOut, dayOff, getLeaveNumber, getDayOffNumber, getPersonalAttendance } from "../api/analysis";
import { ref, toRaw, reactive } from "vue";
import loading from "naive-ui/es/_internal/loading";


export default () => {
  const message = useMessage();

  const leaveNumber = ref(0);
  const dayOffNumber = ref(0);
  const accountData = ref([]);

  const pagination = reactive({
    page: 1,
    pageSize: 10, //每页条数
    pageCount: 0, //总页数
    itemCount: 0, //总条数
  });

  //签到
  const clockInEvent = () => {
    clockIn().then(({ res, succeed }) => {
      if (succeed) {
        message.success("签到成功");
        getAttendanceEvent();
      } else {
        message.error(res.message);
      }
    })
      .catch((err) => {
        const {
          data: { message: msg },
        } = err.response;
        message.error(msg);
      });
  };

  //签退
  const clockOutEvent = () => {
    clockOut().then(({ res, succeed }) => {
      if (succeed) {
        message.success("签退成功");
        getAttendanceEvent();
        getDayOffEvent();
        getLeaveEvent();
      } else {
        message.error(res.message);
      }
    })
      .catch((err) => {
        const {
          data: { message: msg },
        } = err.response;
        message.error(msg);
      });
  };

  //请假
  const dayOffEvent = () => {
    dayOff().then(({ res, succeed }) => {
      if (succeed) {
        message.success("请假成功");
      } else {
        message.error(res.message);
      }
    })
      .catch((err) => {
        const {
          data: { message: msg },
        } = err.response;
        message.error(msg);
      });
  };

  //查询缺勤情况
  const getDayOffEvent = () => {
    getDayOffNumber().then(({ res }) => {
      dayOffNumber.value = toRaw(res.data);
    })
    .catch((err) => {
      const {
        data: { message: msg },
      } = err.response;
      console.log(msg);
    });
  }

  //查询早退
  const getLeaveEvent = () => { 
    getLeaveNumber().then(({ res }) => {
      console.log(res.data);
      leaveNumber.value = toRaw(res.data);
    })
    .catch((err) => {
      const {
        data: { message: msg },
      } = err.response;
      console.log(msg);
    });
  }

  //查询出勤
  const getAttendanceEvent = () => { 
    getPersonalAttendance(pagination.page,pagination.pageSize).then(({ res }) => {
      loading.value = false;
      accountData.value = res.records;
      pagination.itemCount = res.totalElements;
      console.log(res);
    })
    .catch((err) => {
      const {
        data: { message: msg },
      } = err.response;
      console.log(msg);
    });
  }

  // 翻页
  const pageChangeEvent = (page) => { 
    pagination.page = page;
    getAttendanceEvent();
  }

  return {
    leaveNumber,
    dayOffNumber,
    accountData,
    pagination,
    clockInEvent,
    clockOutEvent,
    dayOffEvent,
    getDayOffEvent,
    getLeaveEvent,
    getAttendanceEvent,
    pageChangeEvent
  }
}