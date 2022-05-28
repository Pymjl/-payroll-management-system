import router from "../router";
export default () => {
  const jump = () => {
    router.push("/register");
  };

  return {
    jump,
  };
};
