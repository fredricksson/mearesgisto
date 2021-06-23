<template>
<q-card class="my-card">
   <div class="text-bold text-center q-pa-sm bg-primary" >
            <span class="text-white text-overline">Lista de presencas</span>
        </div>
   <div class="text-bold q-mt-md" >
            <span class="text-primary text-bold q-pa-sm"> {{beliver2.name}}</span>
    </div>
    <div class="row q-pa-sm  justify-center q-gutter-y-sm">
        <div class="col-12">
            <q-markup-table>
      <thead>
        <tr>
          <th class="text-left">Culto</th>
          <th class="text-right">Registador</th>
          <th class="text-right">Data</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td class="text-left">Frozen Yogurt</td>
          <td class="text-right">159</td>
          <td class="text-right">6</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
        <tr>
          <td class="text-left">Ice cream sandwich</td>
          <td class="text-right">237</td>
          <td class="text-right">9</td>
        </tr>
      </tbody>
      </q-markup-table>
      
  </div>
  <div class="q-pa-xs flex flex-center">
          <q-pagination
            v-model="current"
            color="teal"
            :max="10"
            :max-pages="5"
            :ellipses="false"
            :boundary-numbers="false"
          />
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
            beliver2: this.beliver,
            current: 2
        }
    }
}
</script>

<style>

</style>