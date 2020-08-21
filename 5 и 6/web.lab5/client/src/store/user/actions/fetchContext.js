import { Context } from '../api/api';

export default async ({commit, getters}) => {
  const isLoaded = getters.GetContextIsLoaded;

  if (!isLoaded) commit('SetContextUser', await Context());
}
