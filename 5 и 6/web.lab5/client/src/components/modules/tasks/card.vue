<template>
  <div class="content">
    <nav class="navbar">
      <div class="name-page">
        Задача {{task.name}}
      </div>
      <button @click="edit" class="btn-edit-save">
        <img v-if="!isEdit" src="../../../assets/images/edit.png" style="width: 30px;">
        <img v-else src="../../../assets/images/save.png" style="width: 30px;">
      </button>
      <button @click="goToList" class="btn-go-back"><img src="../../../assets/images/back-to.png" alt="" style="width: 40px;"></button>
    </nav>
    <div class="main">
      <div class="data">
        <div class="info-block" :class="{'inp-error':nameError}">
          <label style="opacity: 0.6; width: 120px;">Название: </label>
          <label v-if="!isEdit">{{task.name}}</label>
          <input v-else v-model="task.name" type="text" id="name" class="input" placeholder="название задачи"/>
        </div>
        <label v-if="nameError" class="error" style="font-size: 12pt;">{{nameError}}</label>
        <div class="info-block">
          <label style="opacity: 0.6; width: 120px;">Описание: </label>
          <label v-if="!isEdit">{{task.desc}}</label>
          <textarea v-else v-model="task.desc" type="text" id="desc" class="input" placeholder="описание задачи">
          </textarea>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "card",
      data() {
        return {
          lol: null,
          isEdit: false,
          nameError: ''
        }
      },
      computed: {
        task() {
          return (this.$store.getters.GetTask || {});
        },
      },
      mounted() {
        this.$store.dispatch('FetchTask', this.$route.params.id);
      },
      methods: {
        async edit() {
          if (this.isEdit) {
            if (!this.validation()) {
              return;
            }
            await this.$store.dispatch('UpdateTask', this.task);
            this.nameError = this.$store.getters.GetErrorsTask.nameError || '';
            if (!this.nameError) {
              this.isEdit = !this.isEdit;
            }
          } else {
            this.isEdit = !this.isEdit;
          }
        },
        validation() {
          this.nameError = '';
          this.$store.commit('SetErrorsTask', {});

          if (!this.task.name || !this.task.name.trim()) {
            this.nameError = 'поле является обязательным!';
            return false;
          }
          return true;
        },
        goToList() {
          this.$router.push('/tasks/list');
        },
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
    box-shadow: 0px 2px 2px 0px #8E8E8E;
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
    padding: 10px;
    font-family: Gill Sans, sans-serif;
    font-size: 20px;
  }

  .btn-edit-save {
    margin-left: 20px;
    background-color: transparent;
    border: 0;
    outline: none;
    cursor: pointer;
    opacity: 0.5;
  }
  .btn-edit-save:hover {
    opacity: 1;
  }
  .btn-edit-save:active {
    opacity: 0.5;
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

  .info-block {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }

  .data {
    display: flex;
    flex-direction: column;
    width: 95%;
    margin: 10px auto;
  }
</style>
