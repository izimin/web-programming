import { ChangeStatus } from '../api/api';
import router from '../../../router'

export default async ({commit, dispatch}, task) => {
  dispatch('LOADING_INDICATION__LOCK', 'TASK');

  try {
    await ChangeStatus(task.id);
    task.isCompleted = !task.isCompleted;
    commit('SetUpdatedTask', task);

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
  dispatch('LOADING_INDICATION__UNLOCK', 'TASK');
}
