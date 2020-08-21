<template>
  <div class="header">

    <div class="brand">
      TODO List
    </div>

    <div style="flex: 1;"></div>

    <div class="user"
         @click="toggleLogoutVisibility">
      <span class="login">{{login}}</span>

      <div class="menu"
           v-if="isLogoutVisible">
        <div>
          <span class="login">{{login}}</span>
        </div>

        <div class="logout"
             @click="logout">
          Выход
        </div>
      </div>
    </div>

    <div class="cloak"
         v-if="isLogoutVisible"
         @click="toggleLogoutVisibility">
    </div>
  </div>
</template>

<script>

  export default {
    name: 'TodoHeader',
    components: {
    },
    data () {
      return {
        isLogoutVisible: false
      };
    },

    computed: {
      login() {
        return this.$store.getters.GetContextUser !== null ? this.$store.getters.GetContextUser.login : '';
      },
    },

    methods: {
      async logout() {
        await this.$store.dispatch('ClearContext');
        await this.$store.dispatch('Logout');

        this.$router.push('/login');
      },

      toggleLogoutVisibility(){
        this.isLogoutVisible = !this.isLogoutVisible;
      }
    }
  }

</script>

<style scoped>

  .header {
    display: flex;
    flex-direction: row;
    align-items: center;
    height: 60px;
    padding-left: 10px;
    z-index: 20;
    background: black;
    box-shadow: 2px 2px 2px 2px #8E8E8E;
    opacity: 0.9;
  }

  .cloak {
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    position: fixed;
    background: transparent;
  }

  .brand {
    font-size: 20px;
    margin-left: 60px;
    text-shadow: 1px 1px 3px #8B8B8B;
    color: #E4E4E4;
  }

  .user {
    cursor: pointer;
    padding-right: 16px;
    line-height: 50px;
    font-size: 0.93rem;

    position: relative;
    overflow: visible;
    z-index: 10;
  }

  .login {
    color: white;
    padding-right: 16px;
  }

  .logout {
    line-height: 50px;
    padding-left: 16px;
    padding-right: 16px;
    font-weight: bold;
    background-color: black;
    opacity: 0.9;
    cursor: pointer;
    color: white;
  }
</style>
