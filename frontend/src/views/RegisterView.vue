<template>
   <div>
      <main class="col-3 m-auto">
      <form @submit.prevent="procesarFormulario">
        <h1 class="h3 mb-3 fw-normal text-black">Registrar</h1>
  
        <div class="form-floating">
          <input
            v-model="registrar.firstname"
            type="text"
            class="form-control"
            id="floatingInput1"
            placeholder="usuario1"
          />
          <label for="floatingInput1"><i class="bi bi-person-circle"></i> Firstname</label>
        </div>
        <div class="form-floating">
          <input
            v-model="registrar.lastname"
            type="text"
            class="form-control"
            id="floatingInput2"
            placeholder="usuario1"
          />
          <label for="floatingInput2"><i class="bi bi-person-circle"></i> Lastname</label>
        </div>
        <div class="form-floating">
          <input
            v-model="registrar.email"
            type="text"
            class="form-control"
            id="floatingInput3"
            placeholder="usuario1"
          />
          <label for="floatingInput3"><i class="bi bi-person-circle"></i> Email</label>
        </div>
        <div class="form-floating">
          <input v-model="registrar.password" type="password" class="form-control" id="floatingPassword" placeholder="Password" />
          <label for="floatingPassword"><i class="bi bi-file-lock"></i>Password</label>
        </div>
  
        <button class="w-100 btn btn-lg text-black" type="submit">Registrar</button>
        <!-- <p v-if="errorNombre" class="error text-center">{{ errorNombre }}</p>
        <p v-if="errorPassword" class="error text-center">{{ errorPassword }}</p>
        <p class="error text-center">{{ error }}</p> -->
      </form>
      <div class="text-center p-t-136">
            <RouterLink to="/login" @click="permiterIrALogin" class="btn me-2 text-black">¿Ya tienes cuenta?
              <i class="bi bi-arrow-right"></i>
            </RouterLink>
            <RouterLink to="/" class="btn me-2 text-black">HOME
              <i class="bi bi-house"></i>
            </RouterLink> 

					</div>

    </main>
    </div>
</template>

<script setup>
import axios from 'axios';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useLoginStore } from '../stores/counter';

const router = useRouter();
const store = useLoginStore();
const registrar = reactive({
    firstname:"",
    lastname:"",
    email:"",
    password:""
});

const permiterIrALogin = ()=>{
  store.register()
}

const config = {
    headers: {
    'Content-type': 'application/json',
    }
};
const procesarFormulario = ()=>{
    console.log(registrar)

axios.post("http://localhost:8080/api/v1/auth/register",JSON.stringify(registrar),config)
        .then((response) => {
            store.setTokens(response.data.access_token,response.data.refresh_token);
            router.push({
            name: "login",
            query: {
              at: response.data.access_token,
              rt: response.data.refresh_token,
              state: "todo OK",
              preview: true
            }
          })
        }).catch(e => {
        console.log(`OCURRIO UN ERROR AL CREAR Usuario ${e}`)
        });
}
</script>

<style lang="scss" scoped>

</style>