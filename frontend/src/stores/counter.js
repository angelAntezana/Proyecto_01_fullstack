import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useLoginStore = defineStore('login', () => {
  const ACCESS_TOKEN = ref("");
  const REFRESH_TOKEN = ref("");
  const email = ref("");
  const count = ref(0)
  const registrado = ref(false);
  const logeado = ref(false);
  // const doubleCount = computed(() => count.value * 2)
  function setTokens(access_token,refresh_token) {
    localStorage.setItem("ACCESS_TOKEN",access_token);
    localStorage.setItem("REFRESH_TOKEN",refresh_token);
    ACCESS_TOKEN.value = localStorage.getItem("ACCESS_TOKEN");
    REFRESH_TOKEN.value = localStorage.getItem("REFRESH_TOKEN");
    email.value = "angel";
    registrado.value = true;
    count.value++;
  }
  function register(){
    registrado.value = true;
  }
  function noRegister(){
    registrado.value = false;
  }
  function login(){
    logeado.value = true;

  }
  function clearAccessToken(){
    localStorage.removeItem("ACCESS_TOKEN");
    ACCESS_TOKEN.value = "";
  }
  function logout(){
    registrado.value = false;
    email.value = "";
    logeado.value = false;
  }
  function increment(){
    count.value++;
  }
  // function getTokens(){
  //   console.log(`Valor access_token: ${ACCESS_TOKEN.value}`)
  //   console.log(`Valor refresh_token: ${REFRESH_TOKEN.value}`);
  //   console.log(`Contador ${count.value}`)
  // }

  return { ACCESS_TOKEN,REFRESH_TOKEN,email,count,registrado,logeado,
    setTokens,clearAccessToken,login,logout,register,noRegister,increment }
})
