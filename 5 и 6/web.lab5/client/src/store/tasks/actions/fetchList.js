import { List } from '../api/api';
import router from '../../../router'

export default async ({commit, dispatch}) => {
    dispatch('LOADING_INDICATION__LOCK', 'TASKS_LIST');

    try {
      commit('SetList', await List());
    } catch ({response}) {
      switch (response.status) {
        case 400:
          break;
        case 401:
          commit('SetContextUser', null);
          router.push('/login');
          break;
        case 403:
          router.push('/noAccess');
          break;
        case 404:
          router.push('/notFound');
          break;
      }
    }

    dispatch('LOADING_INDICATION__UNLOCK', 'TASKS_LIST');
}
