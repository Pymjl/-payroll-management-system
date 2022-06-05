import { ARFactory } from "../utils/axios";

export function getBasicWage(pageNum, pageSize) {
  return ARFactory({
    url: "/basicWage",
    method: "get",
    params: { pageNum, pageSize },
  });
}
