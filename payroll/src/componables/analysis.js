import { useMessage } from "naive-ui";
import { clockIn, clockOut } from "../api/analysis";


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

  return {
    clockInEvent,
    clockOutEvent
  }
}