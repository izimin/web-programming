import ShowValidationErrors from './actions/showValidationErrors';
import DismissValidationErrors from './actions/dismissValidationErrors';

import GetValidationErrors from './getters/getValidationErrors';

import FlushValidationErrors from './mutations/flushValidationErrors';
import PushValidationErrors from './mutations/pushValidationErrors';

export default {
    state: {
        validationErrors: []
    },
    actions: {
        ShowValidationErrors,
        DismissValidationErrors
    },
    getters: {
        GetValidationErrors
    },
    mutations: {
        FlushValidationErrors,
        PushValidationErrors
    }
}
