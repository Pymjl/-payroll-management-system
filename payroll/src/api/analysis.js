import { ARFactory } from "../utils/axios";

/**
 * @description: 员工打卡
 * @returns
 */
 export function clockIn() {
    return ARFactory({
      url: "/employee/clockIn",
      method: "get",
    });
}
  
/**
 * @description: 用户签退
 * @returns
 */
 export function clockOut() {
  return ARFactory({
    url: "/employee/clockOut",
    method: "get",
  });
}

/**
 * @description: 用户请假
 * @returns
 */
 export function dayOff() {
  return ARFactory({
    url: "/employee/dayOff",
    method: "get",
  });
}

/**
 * @description: 获得员工自己的早退次数
 * @returns
 */
 export function getLeaveNumber() {
  return ARFactory({
    url: "/employee/leaveNumber",
    method: "get",
  });
}

/**
 * @description: 获得员工自己的缺勤次数
 * @returns
 */
 export function getDayOffNumber() {
  return ARFactory({
    url: "/employee/dayOffNumber",
    method: "get",
  });
}

/**
 * @description: 获得员工自己的出勤列表
 * @returns
 */
 export function getPersonalAttendance(pageNum, pageSize) {
  return ARFactory({
    url: "/employee/attendanceList",
    method: "get",
    params: { pageNum, pageSize },
  });
}