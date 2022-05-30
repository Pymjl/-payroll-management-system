import { ref } from "vue";
import router from "../router";
import { useMessage } from "naive-ui";
import { getEmailCode, login } from "../api/load";
import axios from "axios";

export default () => {
  const message = useMessage();

  const userInfo = ref({
    username: "",
    password: "",
    code: "",
    emailCode: "",
  });
  // 图片验证码
  const verifyCode = ref(null);
  const isLoading = ref(false);
  // 获取验证码
  const getCode = () => {
    isLoading.value = true;
    if (!userInfo.value.username) {
      message.error("请先输入账号");
    } else {
      axios({
        method: "get",
        url: "http://localhost:8080/code/image",
        params: {
          key: userInfo.value.username,
        },
        responseType: "blob",
      }).then((res) => {
        isLoading.value = false;
        verifyCode.value = window.URL.createObjectURL(res.data);
      });
    }
  };
  // 获取邮箱验证码
  const getEmailCodeEvent = () => {
    const loading = message.loading("正在获取验证码...", { duration: 6000 });
    getEmailCode(
      userInfo.value.username,
      userInfo.value.username,
      userInfo.value.code
    ).then(({ res, succeed }) => {
      loading.destroy();
      if (succeed) {
        message.success("验证码已发送");
      } else {
        message.error(res.message);
      }
    });
  };
  // 登录
  const signIn = () => {
    const loading = message.loading("正在登录中...", { duration: 0 });
    login(
      userInfo.value.username,
      userInfo.value.password,
      userInfo.value.emailCode
    ).then(({ res, succeed }) => {
      loading.destroy();
      if (succeed) {
        message.success("登录成功");
        localStorage.setItem("token", res.data);
        router.push("/");
      } else {
        message.error(res.message);
      }
    });
  };

  const jump = () => {
    router.push("/register");
  };

  return {
    userInfo,
    verifyCode,
    isLoading,
    jump,
    getCode,
    signIn,
    getEmailCodeEvent,
  };
};
