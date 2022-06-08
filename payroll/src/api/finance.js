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
