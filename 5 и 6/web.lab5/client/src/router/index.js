import Vue from 'vue'
import Router from 'vue-router'

import Layout from '@/components/modules/layout';
import Login from '@/components/modules/login';
import Registration from '@/components/modules/registration';
import ListTask from '@/components/modules/tasks/list';
import CardTask from '@/components/modules/tasks/card';
import CreateTask from '@/components/modules/tasks/create';
import NotFound from '@/components/modules/notFound';
import NoAccess from '@/components/modules/noAccess';

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '',
      redirect: '/tasks/list'
    },
    {
      path: '/tasks',
      component: Layout,
      children: [
        {
          path: 'list',
          component: ListTask,
        },
        {
          path: 'create',
          component: CreateTask,
        },
        {
          path: ':id',
          component: CardTask,
        }
      ]
    },
    {
      path: '/login',
      component: Login,
    },
    {
      path: '/registration',
      component: Registration,
    },
    {
      path: '/noAccess',
      component: NoAccess
    },
    {
      path: '/notFound',
      component: NotFound
    },
    {
      path: '/**',
      redirect: '/notFound'
    }
    // {
    //   path: '/tasks',
    //   component: Layout,
    //   // children: [
      //   {
      //     path: ':taskId',
      //     component: TitleList
      //   },
      // ]
    // },
    //
    // {
    //   path: '/login',
    //   component: Login,
    // },
    // {
    //   path: '/registration',
    //   component: Registration,
    // }
  ]
});

export default router;
