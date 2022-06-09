import { ARFactory } from "@/utils/axios";

// 组长查看部门的出勤情况
export function getDeptRecord(deptId) {
  return ARFactory({
    url: "/department/record",
    method: "get",
    params: { deptId },
  });
}
// 获取部门信息
/**
 * @description: 获取部门列表
 * @returns
 */
export function getDepartmentList(facultyId) {
  return ARFactory({
    url: "/QueryDepartmentInformationServlet",
    method: "get",
    params: { facultyId },
  });
}
