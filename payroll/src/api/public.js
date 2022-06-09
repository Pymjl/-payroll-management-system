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

// 管理员获取用户列表
export function getUsersList(pageNum, pageSize) {
  return ARFactory({
    url: "/admin/users",
    method: "get",
    params: { pageNum, pageSize },
  });
}
// 管理员删除用户
export function deleteUser(id) {
  return ARFactory({
    url: "/del/user",
    method: "post",
    params: { id },
  });
}
