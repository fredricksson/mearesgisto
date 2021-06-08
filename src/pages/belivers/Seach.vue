<template>
  <q-page class="  row ">
     <q-ajax-bar
      ref="bar"
      position="top"
      color="accent"
      size="10px"
      skip-hijack
    />
      <div class="col-12 col-md-6 q-mt-md ">
        <q-card square  :class="this.$q.dark.isActive ?  'shadow-3 bg-grey-10' : 'shadow-3 bg-yellow-1'">
          <q-card-section>
              <q-input filled clearable @input="search" v-model="contact" type="text" label="insira contacto do crente"
                      mask="(##) ## ## ###"
                        ref="contact"
                        lazy-rules
                        :rules="[ val => val && val.length === 14 && !val.includes('_')   || `contacto  inválido!`]"
                        fill-mask
              >
                <template v-slot:prepend>
                  <q-icon name="search" @click="search" />
                </template>
              </q-input>
          </q-card-section>
          
           <q-inner-loading :showing="visible">
        <q-spinner-gears size="50px" color="primary" />
      </q-inner-loading>
        </q-card>
        <q-slide-transition>
    <q-card v-if="loading && visible" class=" q-mt-md bg-yellow-10" >

      <q-card-section>
        <q-skeleton type="text"  class="text-subtitle1" />
        <q-skeleton type="text" width="50%" class="text-subtitle1" />>
      </q-card-section>
    </q-card>
    <q-card v-else-if="name"   class="my-card  q-mt-md bg-yellow-10">
      <q-card-section>
        <div class="row items-center no-wrap">
          <div class="col">
            <div class="text-h6 text-grey-1">Codigo: {{ id }}</div>
            <div class="text-subtitle2 text-grey-1">{{ name }}</div>
          </div>

         
        </div>
      </q-card-section>
    </q-card>
        </q-slide-transition>
      </div>
  </q-page>
</template>
<script>
import Vue from 'vue'
export default {
  created () {
    this.$q.loading.hide()
    if (!this.$q.cookies.has('token')){
      this.$router.push('/login')
      this.$q.notify({
            type: 'negative',
            message: "Sem autorização autentique-se",
            icon: 'warning'
          })
      return
    }  
    this.headers = {
      Authorization: `Bearer ${this.$q.cookies.get('token')}`
    }
  },
  data () {
    return {
      contact: '',
      loading: false,
      visible: false,
      name: '',
      id: '',
      headers: null
    }
  },
  methods: {
   async search () {
        if (this.contact.length === 14 && !this.contact.includes('_')) {
         this.visible = true
         this.loading = true
          const headers = this.headers
          try {
            const { data } = await Vue.prototype.$axios.get(`${process.env.API}belivers/findByContact/${this.contact}`, { headers } )
            this.loading = false
            this.visible = false
            if (data.message === 'Você não está permitido a acessar este recurso. Por favor autentique-se!') {
                this.loading = false
                this.$q.notify({
                  type: 'negative',
                  message: data.message,
                  icon: 'warning'
                })
                this.$router.push('login')
                return
            }

            if (data.error) {
              this.name = ''
              this.$q.notify({
                type: 'negative',
                message: data.message,
                icon: 'warning',
                timeout: 20000,
                actions: [
                  { label: 'fechar', color: 'white', handler: () => { /* ... */ } }
                ]
              })
              
            } else {
              this.name = data.data.name
              this.id = data.data.id
            }
          } catch (error) {
            this.$q.notify({
                type: 'negative',
                message: "pode ser conexão a internet",
                icon: 'warning',
                timeout: 20000,
                actions: [
                  { label: 'fechar', color: 'white', handler: () => { /* ... */ } }
                ]
              })
          }
           
        } else {
          
          this.formHasError = true

        }
    }
  }
}
</script>

<style>

</style>