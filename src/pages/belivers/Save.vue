<template>
  <q-page class="  row justify-center   ">
      <div class="col-12 col-md-6 ">
  <div class="q-pa-xs ">
    <q-stepper
      v-model="step"
      vertical
      color="primary"
      class="bg-yellow-1"
      animated
    >
      <q-step
        :error="error"
        :name="1"
        title="Dados Pessoais"
        icon="person"
        :done="step > 1"
      >
      <div class="q-gutter-md">
              <q-input filled clearable v-model="name" type="text" label="nome completo"
              ref="name"
              :rules="[val => !!val || 'nome é obrigatorio']"
              >
                <template v-slot:prepend>
                  <q-icon name="person" />
                </template>
              </q-input>
              <q-input filled clearable v-model="contact" prefix="+258:"
                    mask="(##) ## ## ###"
                        ref="contact"
                        lazy-rules
                        :rules="[ val => val && val.length === 14 && !val.includes('_')   || `contacto invalido!`]"
                        fill-mask>
                        <template v-slot:prepend>
                            <q-icon name="local_phone" />
                        </template>
                    </q-input>
                    <q-input filled clearable v-model="parentContact" prefix="+258:"
                    mask="(##) ## ## ###"
                        ref="parentContact"
                        lazy-rules
                        :rules="[ val => val && val.length === 14 && !val.includes('_')   || `contacto de familiar invalido!`]"
                        fill-mask>
                        <template v-slot:prepend>
                            <q-icon name="local_phone" />
                        </template>
                    </q-input>
      </div>

        <q-stepper-navigation>
          <q-btn @click="next" color="primary" label="Continuar" />
        </q-stepper-navigation>
      </q-step>

      <q-step
      :error="error2"
        :name="2"
        title="Localização"
        caption="Opcional(parcialmente)"
        icon="location_on"
        
        :done="step > 2"
      >
       <div class="q-gutter-md">
           <q-input filled clearable v-model="adress" type="text" label="Morada/Bairro"
             ref="adress"
            :rules="[val => !!val || 'Morada/bairo  é obrigatoria!']">
                <template v-slot:prepend>
                  <q-icon name="location_on" />
                </template>
              </q-input>
              <q-input filled clearable v-model="numQuater" type="number" suffix="Opcional" label="Número de quarteirao">
                <template v-slot:prepend>
                  <q-icon name="note_add" />
                </template>
              </q-input>
              <q-input filled clearable v-model="numHome" type="number"  suffix="Opcional"  label="Número de Casa">
                <template v-slot:prepend>
                  <q-icon name="note_add" />
                </template>
              </q-input>
       </div>

        <q-stepper-navigation>
          <q-btn :loading="loading" @click="registeBeliver" color="primary" icon="person_add" label="CADASTRAR" >
              <template v-slot:loading>
                <q-spinner-hourglass class="on-left" />
                Registando...
            </template>
            </q-btn>
          <q-btn flat @click="step = 1" color="primary" label="Voltar" class="q-ml-sm" />
        </q-stepper-navigation>
      </q-step>
    </q-stepper>
  </div>
      </div>
  </q-page>
</template>
<script>
const stringOptions = [
  'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
]
export default {
  data () {
    return {
      model: null,
      options: null,
      step: 1,
      name: '',
      contact: '',
      parentContact: '',
      adress: '',
      numQuater: '',
      numHome: '',
      loading: false,
      error: false,
      error2: false
    }
  },

  methods: {
    next () {
        this.$refs.name.validate()
        this.$refs.parentContact.validate()
        this.$refs.contact.validate()
        if (this.$refs.name.hasError || this.$refs.parentContact.hasError || this.$refs.contact.hasError) {
          this.formHasError = true
          this.error = true
        } else {
            this.error = false
            this.step++
        }
    },
    registeBeliver () {
      this.$refs.adress.validate()
      if (this.$refs.adress.hasError) {
          this.formHasError = true
          this.error2 = true
        } else {
          this.error2 = false
          this.loading = true
        }
    }
  }
}
</script>

<style>

</style>