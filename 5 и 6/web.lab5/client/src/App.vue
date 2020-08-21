<template>
  <div id="app">
    <div class="cloack" v-if="!loaded"></div>
    <div v-else style="height: 100%;">
      <router-view/>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'App',
    created() {
      this.$store.dispatch('FetchContext');
    },
    computed: {
      loaded(){
        return this.$store.getters.GetContextIsLoaded;
      },
    }
  }
</script>

<style>

  .cloack {
    background: white;
  }

  #app {
      position: fixed;
      top: 0;
      bottom: 0;
      left: 0;
      right: 0;
      z-index: 9999;
  }

  html   {
    width: 100%;
    height: 100%;
    /*background: #F9F9F9;*/
    background-image: url("./assets/images/back1.jpg");
    background-size: 100%;
    }

</style>
