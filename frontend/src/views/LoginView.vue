<template>
    <div>
      <main class="col-3 m-auto">
      <form @submit.prevent="procesarFormulario">
        <h1 class="h3 mb-3 fw-normal text-black">Login</h1>
  
        <div class="form-floating">
          <input
            v-model="login.email"
            type="text"
            class="form-control"
            id="floatingInput"
            placeholder="usuario1"
          />
          <label for="floatingInput"><i class="bi bi-person-circle"></i>  Username</label>
        </div>
        <div class="form-floating">
          <input v-model="login.password" type="password" class="form-control" id="floatingPassword" placeholder="Password" />
          <label for="floatingPassword"><i class="bi bi-file-lock"></i> Password</label>
        </div>
  
        <button class="w-100 btn btn-lg text-black" type="submit">Iniciar sesión</button>
        <!-- <p v-if="errorNombre" class="error text-center">{{ errorNombre }}</p>
        <p v-if="errorPassword" class="error text-center">{{ errorPassword }}</p>
        <p class="error text-center">{{ error }}</p> -->
      </form>
      <div class="text-center p-t-136">
            <RouterLink to="/register" @click="bloquearIrALogin" class="btn me-2 text-black">¿No tienes cuenta?
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
const login = reactive({
    email:"",
    password:""
});
const bloquearIrALogin = ()=>{
  store.noRegister()
}

const config = {
    headers: {
    'Content-type': 'application/json',
    }
};

const procesarFormulario = ()=>{

  axios.post("http://localhost:8080/api/v1/auth/authenticate",JSON.stringify(login),config)
        .then((response) => {
            store.setTokens(response.data.access_token,response.data.refresh_token);
            store.login();
            router.push({
            name: "home",
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