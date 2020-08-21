import Vue from 'vue'
import Vuex from 'vuex'

import user from './user/user';
import tasks from './tasks/tasks';
import validationErrors from './validationErrors/validationErrors';
import loadingIndication from './loadingIndication/loadingIndication';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    user,
    tasks,
    loadingIndication,
    validationErrors
  }
})

