import { GetById } from '../api/api';
import router from '../../../router'

export default async ({commit, dispatch}, id) => {
  dispatch('LOADING_INDICATION__LOCK', 'TASK');

  try {
    commit('SetTask', await GetById(id));
  } catch ({response}) {
    switch (response.status) {
      case 400:
        router.push('/notFound');
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
  dispatch('LOADING_INDICATION__UNLOCK', 'TASK');
}
