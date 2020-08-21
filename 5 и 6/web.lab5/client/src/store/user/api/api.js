import axios from 'axios';

export const Login = user => axios.post('/login', user)
  .then(({ data }) => data);

export const Logout = () => axios.post('/logout')
  .then(({ data }) => data);

export const Create = user => axios.post('/registration', user)
  .then(({ data }) => data);

export const Context = () => axios.get('/context')
  .then(({ data }) => data);
