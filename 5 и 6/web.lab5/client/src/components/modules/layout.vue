<template>
  <div class="layout">
    <todo-header></todo-header>
    <div class="main">
      <router-view/>
    </div>
    <loading-locker />
  </div>
</template>

<script>
  import LoadingLocker from '@/components/common/loadingLocker';
  import TodoHeader from '@/components/common/header';

  export default {
    name: 'layout',
    components: {
      LoadingLocker,
      TodoHeader
    },
    beforeCreate() {
      if (!this.$store.getters.GetContextUser || !this.$store.getters.GetContextUser.login) {
        this.$router.push('/login');
      }
    }
  }
</script>

<style scoped>
  .layout {

    height: 100%;
    width: 100%;
  }
  .main {
    height: 100%;
    width: 75%;
    display: flex;
    margin: 0 auto;
    background-color: #eeeeee;
  }
</style>
