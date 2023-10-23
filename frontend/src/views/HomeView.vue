<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useLoginStore } from '../stores/counter';
const store = useLoginStore();
const route = useRoute();
const router = useRouter();
const hello = ref("");
const logout = ()=>{
  store.logout();
  router.push({
            name: "register"
          })
}
const config = {
    headers: {
    'Accept': 'application/json',
    'Authorization': `Bearer ${localStorage.getItem('ACCESS_TOKEN')}`
    }
};
const sayHello = async ()=>{
  await axios.get('http://localhost:8080/api/v1/demo-controller',config)
  .then((response) => {
            hello.value = response.data;
        }).catch(e => {
        console.log(`OCURRIO UN ERROR sayHello ${e}`)
        });
}
</script>

<template>
  <main>
    <h1>hola</h1>
    <h2>{{ store.ACCESS_TOKEN }} {{ store.count }}</h2>
    <h3>{{ route.query.state }}</h3>

    <div class="text-center p-t-136">
            <RouterLink to="/login" class="btn me-2 text-black">Login
              <i class="bi bi-arrow-right"></i>
            </RouterLink>
            <RouterLink to="/register" class="btn me-2 text-black">Registro
              <i class="bi bi-house"></i>
            </RouterLink> 
            <button @click="logout">Cerrar Sesión</button>
            <button @click="sayHello">Hola mundo</button>

			</div>
      <p>{{ hello }}</p>
  </main>
</template>
