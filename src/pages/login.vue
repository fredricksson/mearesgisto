<template>
  <q-page class="bg-yellow-9 window-height window-width row justify-center items-center">
    <div class="column">
      <h4 class="text-h5 text-white q-my-md text-center">MEA &amp; Login</h4>
      <div class="row">
          <q-form class="q-px-sm q-pt-xl q-pb-lg q-gutter-md" @submit.prevent.stop="login">
        <q-card square bordered class=" shadow-1" >
            <q-card-section v-show="msg" class="bg-red">
                <span class="row justify-center text-overline text-white">{{ msg }}</span>
            </q-card-section>
          <q-card-section>
              
             
        
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
            
          </q-card-section>
          <q-card-actions class="q-px-md">
            <q-btn unelevated :loading="loading" icon="login" type="submit" color="yellow-9" size="lg" class="full-width" label="Login" >
                <template v-slot:loading>
                <q-spinner-hourglass class="on-left" />
                Verificando...
            </template>
            </q-btn>
          </q-card-actions>
        </q-card>
        </q-form>
      </div>
    </div>
  </q-page>
</template>

<script>
import Vue from 'vue'
export default {
  name: 'Login',
  created () {
    this.$q.loading.hide()
    this.$q.notify.setDefaults({
      position: 'top',
    })
  },
  data () {
    return {
      username: '',
      password: '',
      loading: false,
      msg: ''
    }
  },
  methods: {
    async login () {
      this.msg = ''
      this.$refs.username.validate()
        this.$refs.contact.validate()
        if (this.$refs.username.hasError || this.$refs.contact.hasError) {
          this.formHasError = true
          return
        }
      this.loading = true
      const dataSend = {
        username: this.username,
        password: this.password
      }
      try {
        
        const { data } = await Vue.prototype.$axios.post(`${process.env.API}authenticate`, dataSend)
        if (data.error) {
          this.msg = data.message
          this.$q.notify({
            type: 'negative',
            message: data.message
          })          
        } else {
          this.msg = ''
          this.$q.loading.show()
          this.$q.cookies.set('token', data.data.token, {
            secure: true,
            expires: 86400000
          })
          this.$router.push("/")
        }
      } catch (error) {
        this.$q.notify({
            type: 'negative',
            message: "Ocorreu um erro pode ser conexão a internet",
            icon: 'wifi_off'
          })
      }
      this.loading = false
    }
  }
}
</script>

<style>
.q-card {
  width: 360px;
}
</style>