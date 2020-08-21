import { Login } from '../api/api';
import router from 'router'

export default async ({commit, dispatch}, user) => {
  dispatch('LOADING_INDICATION__LOCK', 'USER');

  try {
    commit('SetContextUser', await Login(user));
  } catch ({response}) {
    switch (response.status) {
      case 400:
        commit('SetErrors', response.data);
        break;
    }
  }

  dispatch('LOADING_INDICATION__UNLOCK', 'USER');
}
