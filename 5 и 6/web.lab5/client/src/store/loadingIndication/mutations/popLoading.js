export default (state, id) => state.queue = state.queue.filter(loading => loading !== id);
