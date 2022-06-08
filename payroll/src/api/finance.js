import { ARFactory } from "../utils/axios";

// 获取基本工资
export function getBasicWage(pageNum, pageSize) {
  return ARFactory({
    url: "/query/basicWage",
    method: "get",
    params: { pageNum, pageSize },
  });
}
// 获取所有员工的工资条
export function getAllWageSheet(pageNum, pageSize) {
  return ARFactory({
    url: "/admin/getAllWageSheet",
    method: "get",
    params: { pageNum, pageSize },
  });
}
// 修改基本工资
export function updateBasicWage(id, commonWage, managerWage) {
  return ARFactory({
    url: "/update/basicWage",
    method: "post",
    data: { id, commonWage, managerWage },
  });
}
// 根据id获取某个员工的工资条
export function getWageSheetById(id) {
  return ARFactory({
    url: "/admin/getWageSheetById",
    method: "get",
    params: { id },
  });
}
