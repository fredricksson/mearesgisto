<template>
  <div class="q-mt-md ">
     <q-card >
      <q-card-section class="row q-gutter-md justify-center">
     <div class="col-12 col-md-6 col-sm-7 ">
         <q-input v-model="search"  filled  label="Pesquise por nome ou codigo do crente" hint="Pesquisar" type="search">
        <template v-slot:append>
          <q-icon name="search" />
        </template>
      </q-input>
     </div>
      </q-card-section>
    </q-card>
   </div>
</template>

<script>
export default {

}
</script>

<style>

</style>