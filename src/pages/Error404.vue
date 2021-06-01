<template>
  <q-layout view="hHh lpR fFf" class="bg">
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="../assets/logo.png">
          </q-avatar>
          Mea
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <div class="full-height  text-primary text-center q-pa-md flex flex-center">
    <div>
      <div style="font-size: 30vh">
        <q-icon name="search_off"/>
      </div>

      <div class="text-h2" style="opacity:.4">
        Oops. Não há nada aqui...
      </div>

      <q-btn
        class="q-mt-xl"
        color="primary"
        text-color="white"
        unelevated
        to="/"
        label="Pagina inicial"
        no-caps
      />
    </div>
  </div>
    </q-page-container>

  </q-layout>
</template>

<script>
export default {
  name: 'Error404'
}
</script>
