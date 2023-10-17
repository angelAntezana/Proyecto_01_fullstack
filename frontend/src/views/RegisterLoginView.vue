<template>
    <div>
        <main class="col-3 m-auto">
      <form @submit.prevent="procesarFormulario">
        <h1 class="h3 mb-3 fw-normal text-white">{{ acceder ? 'Login':'Registro' }}</h1>
  
        <div class="form-floating">
          <input
            v-model="data.username"
            type="text"
            class="form-control"
            id="floatingInput"
            placeholder="usuario1"
          />
          <label for="floatingInput"><i class="bi bi-person-circle"></i>  Username</label>
        </div>
        <div class="form-floating">
          <input v-model="data.password" type="password" class="form-control" id="floatingPassword" placeholder="Password" />
          <label for="floatingPassword"><i class="bi bi-file-lock"></i> Password</label>
        </div>
  
        <button class="w-100 btn btn-lg text-white" type="submit">Iniciar sesión</button>
        <p v-if="errorNombre" class="error text-center">{{ errorNombre }}</p>
        <p v-if="errorPassword" class="error text-center">{{ errorPassword }}</p>
        <p class="error text-center">{{ error }}</p>
      </form>
      <div class="text-center p-t-136">
            <RouterLink to="/register" class="btn me-2 text-white">Registrar
              <i class="bi bi-arrow-right"></i>
            </RouterLink>

					</div>

    </main>
    </div>
</template>

<script setup>
  import { reactive, ref } from 'vue';

const acceder = ref(true);
const registrar = reactive({
    firstname:"",
    lastname:"",
    email:"",
    password:""
});
const login = reactive({
    email:"",
    password:""
});


const procesarFormulario = ()=>{

}
</script>

<style lang="scss" scoped>

</style>