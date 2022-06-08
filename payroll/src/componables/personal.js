import { ref } from "vue";
import { useMessage } from "naive-ui";
import ls from "@/utils/ls";
import { updateInfo, updatePassword } from "@/api/personal";
import { getUserInfo } from "@/api/load";

export default () => {
  const message = useMessage();
  const userInfo = ref({
    id: "",
    age: "",
    username: "",
    nickname: "",
    identity: "",
    deptName: "",
    account: "",
    phone: "",
    facultyName: "",
  });
  const rules = {
    id: {
      required: true,
    },
    age: {
      required: true,
      message: "请输入年龄",
      trigger: ["input", "blur"],
    },
    username: {
      required: true,
    },
    nickname: {
      required: true,
      message: "请输入昵称",
      trigger: ["input", "blur"],
    },
    identity: {
      required: true,
    },
    deptName: {
      required: true,
    },
    account: {
      required: true,
      message: "请输入银行账户",
      trigger: ["input", "blur"],
    },
    phone: {
      required: true,
      message: "请输入联系电话",
      trigger: ["input", "blur"],
    },
    facultyName: {
      required: true,
    },
  };
  userInfo.value = ls.getItem("user");

  // 修改个人信息
  const updateEvent = () => {
    const loading = message.loading("正在修改...", { duration: 6000 });
    updateInfo(
      userInfo.value.nickname,
      userInfo.value.age,
      userInfo.value.account,
      userInfo.value.phone
    )
      .then(({ res, succeed }) => {
        loading.destroy();
        if (succeed) {
          ls.removeItem("user");
          message.success("修改成功");
          getUserInfo().then(({ res, succeed }) => {
            if (succeed) {
              ls.setItem("user", res.data);
            } else {
              message.error(res.message);
            }
          });
        } else {
          message.error(res.message);
        }
      })
      .catch((err) => {
        // 更好理解的一种写法->解构err->message
        // const {
        //   data: { message: msg },
        // } = err.response;
        loading.destroy();
        message.error(err);
      });
  };

  // 修改密码
  const password = ref(null);
  const updatePsw = () => {
    const loading = message.loading("正在修改...", { duration: 6000 });
    updatePassword(password.value)
      .then(({ succeed }) => {
        loading.destroy();
        if (succeed) {
          message.success("修改成功");
          password.value = null;
        }
      })
      .catch((err) => {
        // 更好理解的一种写法->解构err->message
        // const {
        //   data: { message: msg },
        // } = err.response;
        loading.destroy();
        message.error(err);
      });
  };
  return {
    userInfo,
    rules,
    password,
    updateEvent,
    updatePsw,
  };
};
