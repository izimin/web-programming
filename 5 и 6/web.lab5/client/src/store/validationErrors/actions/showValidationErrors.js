export default ({ commit }, data) => commit('PushValidationErrors', Array.isArray(data) ? data : [data]);
