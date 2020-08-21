<template>
  <div class="content">
    <nav class="navbar">
      <div class="name-page">
        Новая задача
      </div>
      <button @click="goToList" class="btn-go-back"><img src="../../../assets/images/back-to.png" alt="" style="width: 40px;"></button>
    </nav>
    <div class="main">
      <div class="info-block" :class="{'inp-error':nameError}">
        <label style="opacity: 0.6; width: 120px;">Название: </label>
        <input v-model="name" type="text" id="name" class="input" placeholder="название задачи" autofocus="autofocus"/>
      </div>
      <label v-if="nameError" class="error" style="font-size: 12pt;">{{nameError}}</label>
      <div class="info-block">
        <label style="opacity: 0.6; width: 120px;">Описание: </label>
        <textarea v-model="desc" type="text" id="desc" class="input" placeholder="описание задачи"></textarea>
      </div>
      <button @click="createTask" type="submit" id="btn-add" class="btn-add">Добавить</button>
    </div>
  </div>
</template>

<script>
    export default {
      name: "create",
      data() {
        return {
          name: null,
          desc: null,
          nameError: ''
        }
      },
      methods: {
        goToList() {
          this.$router.push('/tasks/list');
        },
        async createTask() {
          if (!this.validation()) {
            return;
          }
          let task = {
            name: this.name,
            desc: this.desc
          };
          await this.$store.dispatch('CreateTask', task);
          this.nameError = this.$store.getters.GetErrorsTask.nameError || '';
          if (!this.nameError) {
            this.goToList();
          }
        },
        validation() {
          this.nameError = '';
          this.$store.commit('SetErrorsTask', {});
          if (!this.name || !this.name.trim()) {
            this.nameError = 'поле является обязательным!';
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

  .inp-error {
    background-color: #FFD5D5;
  }

  .info-block {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }

  .content {
    width: 100%;
    height: 100%;
  }
  .navbar {
    display: flex;
    height: 50px;
    padding: 10px;
    background-color: #AFAFAF;
    align-items: center;
    box-shadow: 0 2px 2px 0 #8E8E8E;
    font-family: Gill Sans, sans-serif;
  }
  .name-page {
    font-size: 25px;
    margin-left: 10px;
    text-shadow: 1px 1px 3px #8B8B8B;
    color: #212020;
  }

  .main {
    overflow: auto;
    padding: 15px 30px;
    font-family: Gill Sans, sans-serif;
    font-size: 20px;
    display: flex;
    flex-direction: column;
  }

  .btn-go-back {
    font-size: 20px;
    margin-right: 20px;
    margin-left: auto;
    cursor: pointer;
    outline: none;
    border: 0;
    padding: 10px;
    background-color: transparent;
    opacity: 0.6;
  }

  .btn-go-back:hover {
    opacity: 1;
  }

  .btn-go-back:active {
    opacity: 0.5;
  }

  .input {
    flex: auto;
    font-size: 20px;
    width: 70%;
    padding: 10px;
    margin: 10px auto;
    border-radius: 2px;
    outline: none;
    border: solid 1px #8B8B8B;
    font-family: Gill Sans, sans-serif;
  }

  .btn-add {
    width: 20%;
    margin: 10px auto;
    padding: 10px;
    font-size: 20px;
    border-radius: 8px;
    border: solid 1px #8B8B8B;
    outline: none;
    cursor: pointer;
    background-color: #AFAFAF;
  }

  .btn-add:hover {
    background-color: #D5D5D5;
  }

  .btn-add:active {
    background-color: #CACACA;
  }

  .btn-add:focus {
    background-color: #CACACA;
  }
</style>
