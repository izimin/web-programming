export default async (state, task) => {
  const idx = state.tasks.findIndex(task1 => task1.id === task.id);
  if (idx === -1) {
    return;
  }
  state.tasks[idx] = task;
}
