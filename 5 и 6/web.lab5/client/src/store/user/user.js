import CreateUser from './actions/createUser';
import LoginUser from './actions/loginUser';
import Logout  from './actions/logout';
import FetchContext from './actions/fetchContext';
import ClearContext from './actions/clearContext';
import ClearErrors from './actions/clearErrors';

import SetContextUser from './mutations/setContextUser';
import ResetIsLoadedFlag from './mutations/resetIsLoadedFlag';
import SetErrors from './mutations/setErrors';

import GetContextUser from './getters/getContextUser';
import GetContextIsLoaded from './getters/getContextIsLoaded';
import GetErrors from './getters/getErrors';

export default {
  state: {
    user: {
      login: null,
      password: null,
      fio: null
    },
    isLoaded: false,
    errors: {},
  },
  actions: {
    CreateUser,
    LoginUser,
    Logout,
    FetchContext,
    ClearContext,
    ClearErrors,
  },
  getters: {
    GetContextUser,
    GetContextIsLoaded,
    GetErrors,
  },
  mutations: {
    SetContextUser,
    ResetIsLoadedFlag,
    SetErrors,
  }
}
