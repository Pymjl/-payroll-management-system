import { ref } from "vue";
import { useMessage } from "naive-ui";
import { getEmailCode, register } from "../api/load";
import router from "../router";
import axios from "axios";

export default () => {
  const message = useMessage();
  // 表单信息
  const model = ref({
    username: "",
    password: "",
    nickname: "",
    gender: null,
    age: null,
    account: "",
    phone: "",
    code: "",
    emailCode: "",
  });
  // 表单验证规则
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
  // 随机生成字符串
  const randomString = () => {
    const chars = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
    const maxPos = chars.length;
    let str = "";
    for (let i = 0; i < 6; i++) {
      str += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return str;
  };
  // 图片验证码
  const verifyCode = ref(null);
  const isLoading = ref(false);
  const key = randomString();
  // 获取验证码
  const getCode = () => {
    isLoading.value = true;
    axios({
      method: "get",
      url: "http://localhost:8080/code/image",
      params: {
        key: key,
      },
      responseType: "blob",
    })
      .then((res) => {
        isLoading.value = false;
        verifyCode.value = window.URL.createObjectURL(res.data);
      })
      .catch((err) => {
        const {
          data: { message: msg },
        } = err.response;
        message.error(msg);
      });
  };
  getCode();
  // 获取邮箱验证码
  const getEmailCodeEvent = () => {
    const loading = message.loading("正在获取验证码...", { duration: 4000 });
    getEmailCode(model.value.username, key, model.value.code)
      .then(({ res, succeed }) => {
        loading.destroy();
        if (succeed) {
          message.success("验证码已发送");
        } else {
          message.error(res.message);
        }
      })
      .catch((err) => {
        const {
          data: { message: msg },
        } = err.response;
        message.error(msg);
      });
  };
  // 注册
  const registerEvent = () => {
    const registering = message.loading("正在注册中...", { duration: 6000 });
    register({
      username: model.value.username,
      password: model.value.password,
      nickname: model.value.nickname,
      gender: model.value.gender,
      age: model.value.age,
      account: model.value.account,
      phone: model.value.phone,
      code: model.value.emailCode,
    })
      .then(({ res, succeed }) => {
        registering.destroy();
        if (succeed) {
          message.success("注册成功");
          router.push("/login");
        } else {
          message.error(res.message);
        }
      })
      .catch((err) => {
        registering.destroy();
        const {
          data: { message: msg },
        } = err.response;
        message.error(msg);
      });
  };
  return {
    router,
    model,
    rules,
    verifyCode,
    getCode,
    registerEvent,
    getEmailCodeEvent,
  };
};
