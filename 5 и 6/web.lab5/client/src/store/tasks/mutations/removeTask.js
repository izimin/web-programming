export default async (state, id) => {
  const idx = state.tasks.findIndex(task => task.id === id);
  if (idx === -1) {
    return;
  }
  state.tasks.splice(idx, 1);
}
