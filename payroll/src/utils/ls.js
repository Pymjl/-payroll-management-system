/**
 * 存数据
 * @param {String} key
 * @param {Object, String} obj
 */

const setItem = (key, obj) => {
  if (typeof obj === "object") {
    obj = JSON.stringify(obj);
    localStorage.setItem(key, obj);
  } else {
    localStorage.setItem(key, obj);
  }
};

const getItem = (key) => {
  return JSON.parse(localStorage.getItem(key));
};

const removeItem = (key) => {
  localStorage.removeItem(key);
};

const clear = () => {
  localStorage.clear();
};

export default { setItem, getItem, removeItem, clear };
