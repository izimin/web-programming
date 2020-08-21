<template>
  <div class="registration-container">
    <form class="registration-form">
      <h1 class="h-action">Регистрация</h1>
      <input class="input" :class="{'inp-error':emailError}"
             type="text"
             v-model="email"
             placeholder="Электронная почта"
             autofocus="autofocus"/>
      <label v-if="emailError" class="error">{{emailError}}</label>

      <input class="input" :class="{'inp-error':passwordError}"
             type="password"
             v-model="password"
             placeholder="Пароль"/>
      <label v-if="passwordError" class="error">{{passwordError}}</label>

      <input class="input" :class="{'inp-error':confirmPasswordError}"
             type="password"
             v-model="confirmPassword"
             placeholder="Повторный пароль"/>
      <label v-if="confirmPasswordError" class="error">{{confirmPasswordError}}</label>

      <input class="input"
             type="text"
             v-model="name"
             placeholder="ФИО"/>
      <button class="btn-registration" type="submit" @click="register">Ок</button>

      <label class="to-login">Уже зарегистрированы?
        <router-link :to="'/login'">Войти</router-link>
      </label>
    </form>
  </div>
</template>

<script>
    export default {
      name: "registration",

      data() {
        return {
          name: "",
          email: "",
          password: "",
          confirmPassword: "",
          emailError: '',
          passwordError: '',
          confirmPasswordError: ''
        }
      },
      beforeCreate() {
        if (this.$store.getters.GetContextUser && this.$store.getters.GetContextUser.login) {
          this.$router.push('/tasks/list');
        }
      },
      methods: {
        async register() {
          if (this.validation()) {
            return;
          }
          let user = {
            login: this.email,
            password: this.password,
            confirmPassword: this.confirmPassword,
            fio: this.name
          };
          await this.$store.dispatch('CreateUser', user);
          this.emailError = this.$store.getters.GetErrors.emailError || '';
          if (!this.emailError) {
            this.$router.push('/login');
          }
        },

        validation() {
          this.emailError = '';
          this.passwordError = '';
          this.confirmPasswordError = '';
          this.$store.dispatch('ClearErrors');
          this.validationEmail();
          this.validationPassword();
          this.validationConfirmPassword();
          return this.emailError || this.passwordError || this.confirmPasswordError;
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

        validationConfirmPassword() {
          if (!this.confirmPassword || !this.confirmPassword.trim()) {
            this.confirmPasswordError = 'поле является обязательным!';
            return false;
          } else if (this.password !== this.confirmPassword) {
            this.confirmPasswordError = 'пароли не совпадают!';
            return false;
          }
          return true;
        }
      }
    }
</script>

<style scoped>
  .error {
    color: red;
    text-align: center;
    padding-bottom: 7px;
  }

  .registration-container {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    background: url("../../assets/images/background-login.jpg");
    background-size: 100%;
  }

  .registration-form {
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

  .btn-registration {
    margin: 20px auto;
    padding: 5px;
    width: 100px;
    font-size: 16pt;
    background-color: gray;
    border: 0;
    border-radius: 10px;
    color: #F0F0F0;
  }

  .btn-registration:hover {
    opacity: 0.8;
    cursor: pointer;
  }

  .btn-registration:focus {
    opacity: 0.8;
    outline: none;
  }

  .to-login {
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
