import { useMessage } from "naive-ui";
import { clockIn, clockOut, dayOff, getLeaveNumber, getDayOffNumber, getPersonalAttendance } from "../api/analysis";


export default () => {
  const message = useMessage();

  const createColumns = () => {
    return [
      {
        title: '部门',
        key: 'department',
      },
      {
        title: '签到时间',
        key: 'in'
      },
      {
        title: '签退时间',
        key: 'out'
      },
      {
        title: '工作时长',
        key: 'hours'
      },
      {
        title: '工作状态',
        key: 'status'
      }
    ]
  }

  //签到
  const clockInEvent = () => {
    clockIn(34, 1, 1).then(({ res, succeed }) => {
      if (succeed) {
        message.success("签到成功");
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

  //查询早退次数
  const getLeaveNumberEvent = () => {
    getLeaveNumber().then(({ res, succeed }) => {
      if (succeed) {
        message.success("查询成功");
        console.log(res);
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
  
  //查询早退次数
  const getDayOffNumberEvent = () => {
    getDayOffNumber().then(({ res, succeed }) => {
      if (succeed) {
        message.success("查询成功");
        console.log(res);
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

  //查询个人考勤表
  const getPersonalAttendanceEvent = () => {
    getPersonalAttendance(1,10).then(({ res, succeed }) => {
      if (succeed) {
        message.success("查询成功");
        console.log(res);
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


  return {
    createColumns,
    clockInEvent,
    clockOutEvent,
    dayOffEvent,
    getLeaveNumberEvent,
    getDayOffNumberEvent,
    getPersonalAttendanceEvent
  }
}