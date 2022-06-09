import { ARFactory } from "../utils/axios";

/**
 * @description: 获取员工列表
 * @returns
 */
 export function employeeList(departmentBossId,departmentId) {
    return ARFactory({
      url: "/CheckStaffServlet",
      method: "post",
      data: {departmentBossId,departmentId}
    });
}

/**
 * @description: 根据Id查员工信息
 * @returns
 */
 export function employeeDtail (departmentBossId, departmentId) {
  return ARFactory({
    url: "/CheckStaffInformationServlet",
    method: "post",
    data: {departmentBossId, departmentId}
  });
}