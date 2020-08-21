import {STORE_NAME} from '../loadingIndication.config';

export default ({ commit }, id) => commit(`${STORE_NAME}__POP_LOADING`, id);
