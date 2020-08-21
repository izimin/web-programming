import { Logout } from '../api/api';
import router from "router";

export default async ({commit, dispatch}) => {
  dispatch('LOADING_INDICATION__LOCK', 'USER');

  try {
    await Logout();
    commit('SetContextUser', null);
  } catch ({status, statusText, errorText}) {
    switch (status) {
      case 400:
        dispatch('ShowValidationErrors', errorText);
        break;
    }
  }

  dispatch('LOADING_INDICATION__UNLOCK', 'USER');
}
