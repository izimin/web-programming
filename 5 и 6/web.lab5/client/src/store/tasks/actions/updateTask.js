import { Update } from '../api/api';
import router from '../../../router'

export default async ({commit, dispatch}, task) => {
  dispatch('LOADING_INDICATION__LOCK', 'TASK');

  try {
    await Update(task);
    commit('SetUpdatedTask', task);

  } catch ({response}) {
    switch (response.status) {
      case 400:
        commit('SetErrorsTask', response.data);
        break;
      case 401:
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
