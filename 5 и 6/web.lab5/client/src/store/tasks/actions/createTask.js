import { Create } from '../api/api';
import router from '../../../router'

export default async ({commit, dispatch}, task) => {
  dispatch('LOADING_INDICATION__LOCK', 'TASK');

  try {
    await Create(task);
  } catch ({response}) {
    switch (response.status) {
      case 400:
        commit('SetErrorsTask', response.data);
        break;
      case 401:
        commit('SetContextUser', null);
        router.push('/login');
        break;
      case 403:
        break;
      case 404:
        router.push('/notFound');
        break;
    }
  }
  dispatch('LOADING_INDICATION__UNLOCK', 'TASK');
}
