import { ARFactory } from "@/utils/axios";

export function updateInfo(nickname, age, account, phone) {
  return ARFactory({
    url: "/update/userInfo",
    method: "post",
    data: { nickname, age, account, phone },
  });
}
// 修改新密码
export function updatePassword(password) {
  return ARFactory({
    url: "/update/password",
    method: "post",
    data: { password },
  });
}
