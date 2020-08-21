export default (state, id) => {
    if (!state.queue.includes(id)) {
        state.queue = [...state.queue, id];
    }
}
