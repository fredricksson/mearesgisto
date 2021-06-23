<template>
    <q-page class="container ">
        
          <div v-if="beliver" class="row  justify-center q-gutter-y-md q-pa-sm">
            <div class="col-12">
              <nav-bar/>
            </div>
            <belivers-card :beliver="beliver" class=" col-12 " v-for="i in 30" :key="i"/>
          </div>
          <div v-else>
              <p class="  text-overline text-center"> <q-icon color="primary" name="hourglass_disabled" /> {{ msg }} </p>
          </div>

          <div class="q-pa-lg flex flex-center">
          <q-pagination
            v-model="current"
            color="teal"
            :max="10"
            :max-pages="5"
            :ellipses="false"
            :boundary-numbers="false"
          />
        </div>
    </q-page>
</template>

<script>
import BeliversCard from 'src/components/admin/BeliversCard.vue'
import NavBar from 'src/components/admin/NavBar.vue'
export default {
  components: { BeliversCard, NavBar },
  created () {
      this.$q.loading.hide()
  },
    data () {
        return {
            beliver: { id: 1, name: "fredricksson Narciso", contact: "84217430", parent_contact: "837338333", num_block: "sds", num_home:"sds"},
            msg: "",
            current: 2
        }
    }
}
</script>

<style>
.sticky {
  position: sticky;
  top: 10px;
}
</style>