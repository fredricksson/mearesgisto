<template>
  <q-layout view="hHh lpR fFf">

    <q-header elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="~assets/logo.png">
          </q-avatar>
          Mearegisto <i>2.0</i>
        </q-toolbar-title>
         <div class="col-auto">
            <q-btn color="grey-1" round flat icon="more_vert">
              <q-menu cover auto-close>
                <q-list>
                  <q-item clickable  @click="logout()">
                      <q-item-section avatar>
                        <q-icon color="primary" name="logout" />
                      </q-item-section>
                    <q-item-section >Sair</q-item-section>
                  </q-item>
                  <q-item clickable @click="mode">
                     <q-item-section avatar>
                      <q-icon color="primary" name="invert_colors" />
                    </q-item-section>
                    <q-item-section v-if="this.$q.dark.isActive" >light-mode</q-item-section>
                    <q-item-section v-else >dark-mode</q-item-section>

                  </q-item>
                </q-list>
              </q-menu>
            </q-btn>
          </div>
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="/register" icon="how_to_reg"  label="Registar" />
        <q-route-tab to="/newBeliver" icon="person_add" label="Cadastrar" />
        <q-route-tab to="/search" icon="search" label="Procurar" />
      </q-tabs>
      
    </q-header>
<q-slide-transition>
    <q-page-container>
      
      <router-view />
      
    </q-page-container>
</q-slide-transition>
  </q-layout>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  methods: {
    logout () {
      this.$q.cookies.remove('token')
      this.$router.push('/login')
    },
    mode () {
      this.$q.dark.set(!this.$q.dark.isActive)
    }
  }
}
</script>