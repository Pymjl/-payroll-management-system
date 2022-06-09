import { ref, reactive } from 'vue';
import { getDepartmentList } from '@/api/public'
import { useMessage } from 'naive-ui'
// import router from '../router'

export default () => {
  const message = useMessage()
  const attData = ref([
    // {
    //   departmentID: '1',
    //   departmentName: '小王',
    //   departmentNum: '10',
    //   departmentBossId: '1',
    // }
  ])
  const pagination = reactive({
    page: 1,
    pageNum: 1,
    pageSize: 10,
    pageItem: 0,
  });
  const getDept = () => {
    const loading = message.loading('正在获取数据...', { duration: 0 })
    getDepartmentList(1).then( ({res}) => {
      let arr = []
      for (let i in res) {
        if (res[i] != null) {
          arr.push({
            departmentID: res[i].departmentId,
            departmentName: res[i].departmentName,
            departmentNum: res[i].departmentNum ? res[i].departmentNum : 0,
            departmentBossId: res[i].departmentBossId,
          })
        }        
      }
      attData.value = arr
      loading.destroy()
    }).catch(err => {
      console.log(err)
      loading.destroy()
    })
  }
  getDept();
  

  return {
    attData,
    pagination
  }
}