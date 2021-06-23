<template>
  <q-layout view="hHh lpR fFf">

    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="left = !left" />

        <q-toolbar-title>
          <q-avatar>
            <img src="~assets/logo.png">
          </q-avatar>
          Mearegisto-Admin
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer show-if-above v-model="left" side="left" bordered>
      <!-- drawer content -->
      <essential-link
        v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
       />
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
const linksData = [
  {
    title: 'Crentes',
    caption: 'crentes registados',
    icon: 'collections_bookmark',
    link: '/admin/mybooks'
  },
  {
    title: 'Registadores',
    caption: 'gerencie registadores',
    icon: 'card_giftcard',
    link: '/admin/requests'
  },
  {
    title: 'cultos',
    caption: 'gerencie cultos',
    icon: 'card_giftcard',
    link: '/admin/requests'
  },
  {
    title: 'relatorio',
    caption: 'relatorios de presencas',
    icon: 'summarize',
    link: '/admin/requests'
  }
]
import EssentialLink from 'src/components/EssentialLink.vue'
export default {
  components: { EssentialLink },
  data () {
    return {
      left: false,
      essentialLinks: linksData
    }
  }
}
</script>