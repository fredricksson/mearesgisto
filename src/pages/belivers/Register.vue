<template>
  <q-page class="  row justify-center   q-pa-sm">
      <div class="col-12 col-md-6 ">
        <q-card square  class=" shadow-3 bg-yellow-1">
          <q-card-section>
               <q-card-section>
            <span class="text-overline  text-yellow-10 q-my-xs" >Registados: {{ quantity }}</span>
          </q-card-section>
             <q-form class="q-px-sm  q-pb-lg q-gutter-md">
                <q-select
                    ref="cult"
                    filled
                    v-model="cult"
                    clearable
                    use-chips
                    label="Cultos"
                    :options="options"
                    @filter="filterFn"
                    @filter-abort="abortFilterFn"
                    @input="saveCultInLocalStorage"
                    :rules="[val => !!val || 'Codigo é obrigatoria!']"
                >
                    <template v-slot:no-option>
                    <q-item>
                        <q-item-section class="text-grey">
                        No results
                        </q-item-section>
                    </q-item>
                    </template>
                </q-select>
              <q-input filled clearable v-model="id"  type="number" label="Codigo do crente"
              ref="id"
              :rules="[val => !!val || 'Codigo é obrigatoria!']"
              >
                <template v-slot:prepend>
                  <q-icon name="vpn_key" />
                </template>
              </q-input>
              <q-input filled  clearable v-model="temperature" type="number" label="Temperatura"
                ref="temperature"
                :rules="[val => !!val || 'Temperatura é obrigatoria!']">
                <template v-slot:prepend>
                  <q-icon name="thermostat" />
                </template>
              </q-input>
            </q-form>
          </q-card-section>
          <q-card-actions class="q-px-md">
            <q-btn unelevated :loading="loading" color="yellow-10" icon="check" @click="registerBeliverToCult" size="md" class="full-width" label="Registar" >
                <template v-slot:loading>
                <q-spinner-hourglass class="on-left" />
                carregando...
            </template>
            </q-btn>
          </q-card-actions>
        </q-card>
      </div>
      <q-dialog v-model="confirm" persistent>
      <q-card>
        <q-card-section class="row justify-center items-center">
          
          <p ><q-icon name="help_outline" size="38px"  /></p>
          <p class="q-ml-sm text-h6">{{ confirmName }}</p>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn icon="close" label="Cancelar" @click="loading = false" color="red" v-close-popup />
          <q-btn icon="check" label="Registar" @click="registerBeliver" color="blue" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
      <q-dialog v-model="error" >
      <q-card>
        <q-card-section class="row justify-center items-center">
          
          <p ><q-icon name="dangerous" size="38px" color="red"  /></p><br>
          <span class="q-ml-sm text-bold">Temperatura inválida!</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn icon="check" class="full-width" label="Ok" color="blue" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
      <q-dialog v-model="errorId" >
      <q-card>
        <q-card-section class="row justify-center items-center">
          
          <p ><q-icon name="dangerous" size="38px" color="red"  /></p><br>
          <p class="q-ml-sm text-bold">Codigo inexistente!</p>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn icon="check" class="full-width"  label="Ok" color="blue" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>
<script>
import Vue from 'vue'
export default {
  created () {
    this.$q.loading.hide()
    if (this.$q.localStorage.has('cult')) {
      this.cult = this.$q.localStorage.getItem('cult')
    }
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
    setInterval(this.quantityBelivers, 1000*60*4)
  },
  data () {
    return {
      quantity: '...',
      cult: null,
      options: null,
      id: '',
      temperature: '',
      loading: false,
      confirm: false,
      error: false,
      errorId: false,
      confirmName: '',
      headers: null
    }
  },

  methods: {
    saveCultInLocalStorage () {
      if (this.cult !== null) {
      this.quantityBelivers()
      this.$q.localStorage.set("cult", this.cult)
      }
    },
   async filterFn (val, update, abort) {
      if (this.options !== null) {
        // already loaded
        update()
        return
      }
        const headers = this.headers
        const { data } = await Vue.prototype.$axios.get(`${process.env.API}cults`, { headers } )
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
        update(() => {
          this.options = data.data.map(cult => cult.name)
        })
     
    },
    async quantityBelivers () {
      
      try{
        const headers = this.headers
        const { data } = await Vue.prototype.$axios.get(`${process.env.API}presences/quantity/${this.cult}`, { headers } )

        if (data.error) {
          this.$router.push('/login')
        } else {
          this.quantity = data.data
        }
      }catch (err) {
        this.$q.notify({
            type: 'negative',
            message: "Ocorreu um erro, Pode ser conexão a internet.",
            icon: 'warning'
          })
      }
    },
    abortFilterFn () {
      // console.log('delayed filter aborted')
    },
    async registerBeliver () {
      try {
        const headers = this.headers
        const { data } = await Vue.prototype.$axios.post(`${process.env.API}presence/${this.id}/${this.cult}`, {} ,{ headers } )
          if (data.error) {
            this.$q.notify({
                type: 'negative',
                message: data.message,
                icon: 'warning'
              })
          } else {
            this.$q.notify({
                type: 'positive',
                message: data.message,
                icon: 'check'
              })
              this.quantityBelivers()
          }
      } catch (error) {
        this.$q.notify({
            type: 'negative',
            message: "Ocorreu um erro, autentique-se",
            icon: 'warning'
          })
        this.$router.push('/login')
      }
      this.loading = false
    },
    async registerBeliverToCult () {
        // verify if exists
        
        this.$refs.id.validate()
        this.$refs.temperature.validate()
        this.$refs.cult.validate()
        if (this.$refs.id.hasError || this.$refs.temperature.hasError || this.$refs.cult.hasError) {
          this.formHasError = true
        } else {
            if (!(this.temperature > 25 && this.temperature < 40)) {
                this.error = true
                return
            }
            this.loading = true
            const headers = this.headers
            const { data } = await Vue.prototype.$axios.get(`${process.env.API}belivers/${this.id}`, { headers } )
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
              // id inexistente
              this.loading = false
              this.$q.notify({
                type: 'negative',
                message: data.message,
                icon: 'warning'
              })
              
            } else {
              this.confirmName = data.data.name
              this.confirm = true
            }

        }
    }
  }
}
</script>

<style>

</style>