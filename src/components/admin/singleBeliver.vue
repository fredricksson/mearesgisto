<template>
<q-card class="my-card">
   <div class="text-bold text-center q-pa-sm bg-primary" >
            <span class="text-white text-overline">Alterar crente</span>
        </div>
    <div class="row q-pa-sm q-gutter-y-sm">
      <div class="col-12">
          <q-input outlined v-model="beliver2.name" :readonly="info" label="Nome:" stack-label />
      </div>
      <div class="col-12">
          <q-input outlined v-model="beliver2.contact" :readonly="info" label="Contacto:" stack-label />
      </div>
      <div class="col-12">
          <q-input outlined v-model="beliver2.contact" :readonly="info" label="Contacto de familiar:" stack-label />
      </div>
      <div class="col-12">
          <q-input outlined v-model="beliver2.contact" :readonly="info" label="Morada/bairro:" stack-label />
      </div>
      <div class="col-12">
          <q-input outlined v-model="beliver2.contact" :readonly="info" label="Numero de quarteirao:" stack-label />
      </div>
      <div class="col-12">
          <q-input outlined v-model="beliver2.contact" :readonly="info" label="Numero de casa:" stack-label />
      </div>
      <div class="col-6 q-pa-xs">
          <q-btn class="full-width" :icon="info ? 'edit' : 'check'" @click="info = false" color="primary" :label=" info ? 'Editar' : 'salvar'" />
      </div>
      <div class="col-6 q-pa-xs">
          <q-btn class="full-width" color="red" label="fechar" v-close-popup/>
      </div>
  </div>
</q-card>    
  
</template>

<script>
export default {
    props: ['beliver'],
    data () {
        return {
            info: true,
            beliver2: this.beliver
        }
    }
}
</script>

<style>

</style>