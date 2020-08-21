export default (state, data) => {
    const shallowCopy = [...state.validationErrors];

    state.validationErrors = shallowCopy.concat(data);
}
