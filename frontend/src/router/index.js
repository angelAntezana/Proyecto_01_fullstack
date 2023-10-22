import { createRouter, createWebHistory } from 'vue-router';
import { useLoginStore } from '../stores/counter';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import RegisterView from '../views/RegisterView.vue';
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView
    },
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
})

router.beforeEach((to,from) => {
  const store = useLoginStore()
  // const registrado = true;

  if (store.email === '') {
    if ((to.path == '/login' || to.path =='/') && !store.registrado) {
      return '/register';
    }
    else{
      if((to.path =='/register' || to.path =='/') && store.registrado)
      return '/login'
    }
  } else {
    if (to.path == '/login' && store.logeado) {
      return '/';
    }
  

  }
})

export default router
