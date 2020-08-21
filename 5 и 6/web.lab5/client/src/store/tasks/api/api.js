import axios from 'axios';

export const  GetById = id => axios.get(`/task/${id}`)
  .then(({ data }) => data);

export const  List = () => axios.get('/task/list')
  .then(({ data }) => data);

export const Create = task => axios.post('/task/create', task)
  .then(({ data }) => data);

export const Update = task => axios.put('/task/update', task)
  .then(({ data }) => data);

export const Delete = id => axios.delete(`/task/delete/${id}`)
  .then(({ data }) => data);

export const ChangeStatus = id => axios.put(`/task/changeStatus/${id}`)
  .then(({ data }) => data);
