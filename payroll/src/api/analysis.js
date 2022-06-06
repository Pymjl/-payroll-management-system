import { ARFactory } from "../utils/axios";

/**
 * @description: 员工打卡
 * @param {Number} userId
 * @param {Number} departmentId
 * @param {Number} facultyId
 * @returns
 */
 export function clockIn(userId, departmentId, facultyId) {
    return ARFactory({
      url: "/employee/clockIn",
      method: "post",
      data: { userId, departmentId, facultyId },
    });
}
  
/**
 * @description: 用户签退
 * @param {Number} data 用户Id
 * @returns
 */
 export function clockOut() {
  return ARFactory({
    url: "/employee/clockOut",
    method: "get",
  });
}