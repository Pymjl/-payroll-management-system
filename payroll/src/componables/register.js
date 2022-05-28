import { ref } from "vue";

export default () => {
  const model = ref({
    username: "",
    password: "",
    nickname: "",
    gender: null,
    age: null,
    account: "",
    phone: "",
    code: "",
  });

  const rules = {
    username: {
      required: true,
      trigger: ["blur", "input"],
      message: "请输入账号",
    },
    password: {
      required: true,
      trigger: ["blur", "input"],
      message: "请输入密码",
    },
    nickname: {
      required: true,
      trigger: ["blur", "input"],
      message: "请输入昵称",
    },
    gender: {
      required: true,
      trigger: ["blur", "input"],
      message: "请填写性别",
    },
    age: {
      required: true,
      trigger: ["blur", "input"],
      message: "请填写年龄",
    },
    account: {
      required: true,
      trigger: ["blur", "input"],
      message: "请填写银行卡号",
    },
    phone: {
      required: true,
      trigger: ["blur", "input"],
      message: "请填写电话号码",
    },
  };
  return {
    model,
    rules,
  };
};
