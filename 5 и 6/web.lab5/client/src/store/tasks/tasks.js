import FetchList from './actions/fetchList';
import CreateTask from './actions/createTask';
import DeleteTask from './actions/deleteTask';
import SaveTask from './actions/saveTask'
import UpdateTask from './actions/updateTask'
import FetchTask from './actions/fetchTask'
import ChangeStatus from './actions/changeStatus'

import SetList from './mutations/setList';
import RemoveTask from './mutations/removeTask';
import SetTask from './mutations/setTask'
import SetUpdatedTask from './mutations/setUpdatedTask'
import SetErrorsTask from './mutations/setErrors'

import GetList from './getters/getList'
import GetTask from './getters/getTask'
import GetErrorsTask from './getters/getErrors'

export default {
  state: {
    tasks: [],
    task: null,
    errors: {}
  },
  actions: {
    FetchList,
    CreateTask,
    DeleteTask,
    SaveTask,
    UpdateTask,
    ChangeStatus,
    FetchTask
  },
  getters: {
    GetList,
    GetTask,
    GetErrorsTask,
  },
  mutations: {
    SetList,
    RemoveTask,
    SetTask,
    SetUpdatedTask,
    SetErrorsTask,
  }
}
