import { useMessage } from "naive-ui";
import { clockIn, clockOut, dayOff, getLeaveNumber, getDayOffNumber } from "../api/analysis";


export default () => {
  const message = useMessage();

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


  return {
    clockInEvent,
    clockOutEvent,
    dayOffEvent,
    getLeaveNumberEvent,
    getDayOffNumberEvent
  }
}