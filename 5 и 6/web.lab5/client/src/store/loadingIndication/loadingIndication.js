import { STORE_NAME } from './loadingIndication.config';
import LoadLock from './actions/loadLock';
import LoadUnlock from './actions/loadUnlock';
import GetIsDisplayed from './getters/getIsDisplayed';
import PopLoading from './mutations/popLoading';
import PushLoading from './mutations/pushLoading';

export default {
    state: {
        queue: []
    },
    getters: {
        [`${STORE_NAME}__IS_DISPLAYED`]: GetIsDisplayed
    },
    actions: {
        [`${STORE_NAME}__LOCK`]: LoadLock,
        [`${STORE_NAME}__UNLOCK`]: LoadUnlock
    },
    mutations: {
        [`${STORE_NAME}__PUSH_LOADING`]: PushLoading,
        [`${STORE_NAME}__POP_LOADING`]: PopLoading
    }
}
