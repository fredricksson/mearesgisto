<template>
    <div>
        <q-card class="my-card">
      <q-item>
        <q-item-section avatar  @click="expanded = !expanded" >
          <q-avatar>
            <q-icon name="person"/>
          </q-avatar>
        </q-item-section>

        <q-item-section class="col-12"  @click="expanded = !expanded">
          
          <q-item-label class="text-bold" style="font-size: 12px" caption>
            Codigo: {{beliver.id}}
          </q-item-label>
          <q-item-label class="text-bold q-pa-none q-ma-none" style="font-size: 17px">{{beliver.name}}</q-item-label>
        </q-item-section>
         <q-item-section>
            <q-btn    class="absolute-right " flat  icon="more_vert">
              <q-menu cover auto-close>
                <q-list>
                  <q-item clickable  @click="info = true">
                    <q-item-section >Actualizar</q-item-section>
                  </q-item>
                  <q-item clickable @click="presence = true">
                    <q-item-section>Presencas</q-item-section>

                  </q-item>
                </q-list>
              </q-menu>
            </q-btn>
        </q-item-section>
      </q-item>
      <q-slide-transition>
        <div v-show="expanded">
          <q-separator />
          <q-list dark class="bg-dark" bordered separator>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>Contacto Pessoal</q-item-label>
                <q-item-label caption>{{beliver.contact}}</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>Contacto de familiar</q-item-label>
                <q-item-label caption>{{beliver.parent_contact}}</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>Quarteirão número</q-item-label>
                <q-item-label caption>{{beliver.num_block}}</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>Casa número</q-item-label>
                <q-item-label caption>{{beliver.num_home}}</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </div>
      </q-slide-transition>
        </q-card>
        <q-dialog v-model="info" transition-show="fade"  persistent>
            <single-beliver :beliver="beliver" />
        </q-dialog>
        <q-dialog v-model="presence" transition-show="fade" >
           <presences :beliver="beliver" />
        </q-dialog>
</div>
</template>

<script>
import singleBeliver from './singleBeliver.vue'
import presences from './Presences.vue'
export default {
  components: { singleBeliver, presences },
    props: ['beliver'],
    data () {
        return {
            expanded: false,
            info: false,
            presence: false
        }
    }
}
</script>

<style>

</style>