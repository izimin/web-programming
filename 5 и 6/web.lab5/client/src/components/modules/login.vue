<template>
  <div class="login-container">
    <form class="login-form">
      <h1 class="h-action">Вход в TODO list</h1>
      <input class="input" :class="{'inp-error':emailError}"
             type="text"
             v-model="email"
             placeholder="Электронная почта"/>
      <label v-if="emailError" class="error">{{emailError}}</label>

      <input class="input" :class="{'inp-error':passwordError}"
             type="password"
             v-model="password"
             placeholder="Пароль"/>
      <label v-if="passwordError" class="error">{{passwordError}}</label>

      <button class="btn-login" type="submit" @click="logIn">Войти</button>
      <label class="to-registration">Нет аккаунта?
        <router-link :to="'/registration'">Зарегистрироваться</router-link>
      </label>
    </form>
  </div>
</template>

<script>
    export default {
      name: "login",
      data() {
        return {
          email: '',
          password: '',
          emailError: '',
          passwordError: '',
        }
      },
      beforeCreate() {
        if (this.$store.getters.GetContextUser && this.$store.getters.GetContextUser.login) {
          this.$router.push('/tasks/list');
        }
      },
      methods: {
        async logIn() {
          if (this.validation()) {
            return;
          }
          let user = {
            login: this.email,
            password: this.password
          };
          await this.$store.dispatch('LoginUser', user);
          this.emailError = this.$store.getters.GetErrors.emailError || '';
          this.passwordError = this.$store.getters.GetErrors.passwordError || '';
          if (!this.emailError && !this.passwordError) {
            this.$router.push('/tasks/list');
          }
        },
        validation() {
          this.emailError = '';
          this.passwordError = '';
          this.$store.dispatch('ClearErrors');
          this.validationEmail();
          this.validationPassword();
          return this.emailError || this.passwordError;
        },

        validationEmail() {
          if (!this.email || !this.email.trim()) {
            this.emailError = 'поле является обязательным!';
            return false;
          } else if (!/.+@.+/i.test(this.email)) {
            this.emailError = 'введите корректный адрес!';
            return false;
          }
          return true;
        },

        validationPassword() {
          if (!this.password || !this.password.trim()) {
            this.passwordError = 'поле является обязательным!';
            return false;
          }
          return true;
        },
      },
    }
</script>

<style scoped>
  .error {
    color: red;
    text-align: center;
    padding-bottom: 7px;
  }

  .login-container {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    background: url("../../assets/images/background-login.jpg");
    background-size: 100%;
  }

  .login-form {
    display: flex;
    flex-direction: column;
    width: 25%;
    margin: 0 auto;
    background-color: #EEEEEE;
    padding: 70px 40px;
    box-shadow: 0 2px 2px 2px gray;
    justify-content: center;
    opacity: 0.95;
    border-radius: 2px;
  }

  .h-action {
    text-align: center;
    font-family: 'Roboto', sans-serif;
    color: #3B3B3B;
    margin-bottom: 50px;
  }

  .input {
    background-color: transparent;
    border-width: 0 0 1px 0;
    border-bottom-color: gray;
    font-size: 18pt;
    margin-bottom: 20px;
    padding: 5px;
    color: black;
  }

  .input::placeholder {
    color: #A6A6A6;
  }

  .input:focus {
    outline: none;
    caret-color: black;
  }

  .inp-error {
    border-bottom-color: red;
    padding-bottom: 0;
    margin-bottom: 0;
  }

  .btn-login {
    margin: 20px auto;
    padding: 5px;
    width: 100px;
    font-size: 16pt;
    background-color: gray;
    border: 0;
    border-radius: 10px;
    color: #F0F0F0;
  }

  .btn-login:hover {
    opacity: 0.8;
    cursor: pointer;
  }

  .btn-login:focus {
    opacity: 0.8;
    outline: none;
  }

  .to-registration {
    font-family: 'Roboto', sans-serif;
    margin-top: 30px;
    font-size: 16pt;
    color: #3E3E3E;
  }

  a {
    color: #A6A6A6;
  }

  a:visited {
    color: #A6A6A6;
  }

  a:hover {
    color: #888888;
  }
</style>
