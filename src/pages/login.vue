<template>
  <q-page class="bg-yellow-9 window-height window-width row justify-center items-center">
    <div class="column">
      <div class="row">
      </div>
      <h4 class="text-h5 text-white q-my-md text-center">MEA &amp; Login</h4>
      <div class="row">
          
        <q-card square bordered class=" shadow-1" >
            <q-card-section v-show="true" class="bg-red">
                <span class="row justify-center text-overline text-white">nome/contacto invalido</span>
            </q-card-section>
          <q-card-section>
              
             <q-form class="q-px-sm q-pt-xl q-pb-lg q-gutter-md">
        
              <q-input square clearable v-model="username" type="username" label="Nome"
              ref="username"
            :rules="[val => !!val || 'nome esta vazio!']"
              >
                <template v-slot:prepend>
                  <q-icon name="person" />
                </template>
              </q-input>
              <q-input square clearable v-model="password" type="password" label="Contacto"
              ref="contact"
            :rules="[val => !!val || 'Contacto esta vazio!']"
              >
                <template v-slot:prepend>
                  <q-icon name="lock" />
                </template>
              </q-input>
            </q-form>
          </q-card-section>
          <q-card-actions class="q-px-md">
            <q-btn unelevated :loading="loading" @click="loading = true" color="yellow-9" size="lg" class="full-width" label="Login" >
                <template v-slot:loading>
                <q-spinner-hourglass class="on-left" />
                Verificando...
            </template>
            </q-btn>
          </q-card-actions>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      username: '',
      password: '',
      loading: false
    }
  }
}
</script>

<style>
.q-card {
  width: 360px;
}
</style>