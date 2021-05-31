
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/belivers/Register.vue') },
      { path: 'register', component: () => import('pages/belivers/Register.vue') },
      { path: 'newBeliver', component: () => import('pages/belivers/Save.vue') },
      { path: 'search', component: () => import('pages/belivers/Seach.vue') }
    ]
  },
  {
    path: '/login',
    component: () => import('layouts/ClearLayout.vue'),
    children: [
      { path: '', component: () => import('pages/login.vue') }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
