<template>
  <q-page class="  row justify-center   q-pa-sm">
      <div class="col-12 col-md-6 ">
        <q-card square  class=" shadow-3 bg-yellow-1">
          <q-card-section>
               <q-card-section>
            <span class="text-overline  text-yellow-10 q-my-xs" >Registados: 20</span>
          </q-card-section>
             <q-form class="q-px-sm  q-pb-lg q-gutter-md">
                <q-select
                    filled
                    v-model="cult"
                    clearable
                    use-chips
                    label="Cultos"
                    :options="options"
                    @filter="filterFn"
                    @filter-abort="abortFilterFn"
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
          <span class="q-ml-sm text-h6">Fredrickson Bande</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn icon="close" label="Cancelar" color="red" v-close-popup />
          <q-btn icon="check" label="Registar" color="blue" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
      <q-dialog v-model="error" >
      <q-card>
        <q-card-section class="row justify-center items-center">
          
          <p ><q-icon name="dangerous" size="38px" color="red"  /></p>
          <span class="q-ml-sm text-bold">Temperatura inválida!</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn icon="check" class="full-width" label="Ok" color="blue" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>
<script>
const stringOptions = [
  'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
]
export default {
  data () {
    return {
      cult: null,
      options: null,
      id: '',
      temperature: '',
      loading: false,
      confirm: false,
      error: false
    }
  },

  methods: {
    filterFn (val, update, abort) {
      if (this.options !== null) {
        // already loaded
        update()
        return
      }

      setTimeout(() => {
        update(() => {
          this.options = stringOptions
        })
      }, 2000)
    },

    abortFilterFn () {
      // console.log('delayed filter aborted')
    },
    registerBeliverToCult () {
        
        
        this.$refs.id.validate()
        this.$refs.temperature.validate()
        if (this.$refs.id.hasError || this.$refs.temperature.hasError) {
          this.formHasError = true
        } else {
            if (!(this.temperature > 25 && this.temperature < 40)) {
                this.error = true
                return
            }
            this.loading = true
            this.confirm = true
        }
    }
  }
}
</script>

<style>

</style>