<template>
  <q-page class="  row justify-center   q-pa-sm">
      <div class="col-12 col-md-6 ">
        <q-card square  class=" shadow-3 bg-yellow-1">
          <q-card-section>
             <q-form class=" q-gutter-md">
              <q-input filled clearable v-model="username" type="text" label="insira contacto do crente">
                <template v-slot:prepend>
                  <q-icon name="search" />
                </template>
              </q-input>
            </q-form>
          </q-card-section>
        </q-card>
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
      options: null
    }
  }
}
</script>

<style>

</style>