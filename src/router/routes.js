import { Cookies, Loading } from 'quasar'
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/belivers/Register.vue'), 
      beforeEnter: (to, from, next) => {
         Loading.show({ spinnerColor: 'primary', backgroundColor: 'yellow-3'
         }); 
         if (Cookies.has('token')) next()
          else next('/login')
      } },
      { path: 'register', component: () => import('pages/belivers/Register.vue'),
          beforeEnter: (to, from, next) => {
            Loading.show({ spinnerColor: 'primary', backgroundColor: 'yellow-3'
            }); 
            if (Cookies.has('token')) next()
            else next('/login')
        }
      },
      { path: 'newBeliver', component: () => import('pages/belivers/Save.vue'),
          beforeEnter: (to, from, next) => {
            Loading.show({ spinnerColor: 'primary', backgroundColor: 'yellow-3'
            }); 
            if (Cookies.has('token')) next()
            else next('/login')
         }
      },
      { path: 'search', component: () => import('pages/belivers/Seach.vue'),
          beforeEnter: (to, from, next) => {
            Loading.show({ spinnerColor: 'primary', backgroundColor: 'yellow-3'
            }); 
            if (Cookies.has('token')) next()
            else next('/login')
         }
      }
    ]
  },
  {
    path: '/login',
    component: () => import('layouts/ClearLayout.vue'),
    children: [
      { path: '', component: () => import('pages/login.vue'), beforeEnter: (to, from, next) => { Loading.show({ spinnerColor: 'primary', backgroundColor: 'yellow-3' }); next() } }
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
