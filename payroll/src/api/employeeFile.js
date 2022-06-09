import { ARFactory } from "../utils/axios";

/**
 * @description: 添加部门
 * @returns
 */
 export function addDepartment(facultyId, departmentId, departmentName, departmentBossId) {
    return ARFactory({
      url: "/AddDepartmentServlet",
      method: "post",
      data: {facultyId, departmentId, departmentName, departmentBossId}
    });
}

/**
 * @description: 删除部门
 * @returns
 */
 export function deleteDepartment(departmentId) {
  return ARFactory({
    url: "/DeleteDepartmentServlet",
    method: "post",
    data: {departmentId}
  });
}

/**
 * @description: 获取部门列表
 * @returns
 */
 export function getDepartmentList(facultyId) {
  return ARFactory({
    url: '/QueryDepartmentInformationServlet',
    method: "get",
    params: {facultyId}
  });
}