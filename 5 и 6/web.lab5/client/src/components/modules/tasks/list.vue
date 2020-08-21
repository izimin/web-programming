<template>
  <div class="content">
    <nav class="navbar">
      <div class="name-page">
        Список задач
      </div>
      <input v-model="search" type="text" id="search" class="filter-input" placeholder="название задачи"/>
      <div class="filter-cbx">
        <div>
          <input type="checkbox" id="cbx-completed" v-model="isCompleted"/>
          <label for="cbx-completed">завершенные</label>
        </div>
        <div>
          <input type="checkbox" id="cbx-not-completed" v-model="isNotCompleted"/>
          <label for="cbx-not-completed">не завершенные</label>
        </div>
      </div>
      <button @click="addTask" class="add-btn"><img src="../../../assets/images/plus.png" style="width: 50px;"></button>
    </nav>
    <div class="main">
      <ul class="tasks-list">
        <li class="task" v-for="t in filterTasks">
          <div @click="viewCard(t)" class="name-task">{{t.name}}</div>
          <div class="change-btn-block">
            <button @click="changeStatus(t)" class="complete-btn">
              <img v-if="t.isCompleted" src="../../../assets/images/completed.png" style="width: 30px;">
              <img v-else src="../../../assets/images/process.png" style="width: 30px;">
            </button>
            <button @click="deleteTask(t.id)" class="delete-btn">
              <img src="../../../assets/images/delete.png" style="width: 30px;">
            </button>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
    export default {
        name: "list",
        data() {
          return {
            isCompleted: true,
            isNotCompleted: true,
            search: ''
          }
        },
        created() {
          this.$store.dispatch('FetchList');
        },
        computed: {
          tasks() {
            return (this.$store.getters.GetList || []);
          },
          filterTasks() {
            return this.tasks.filter((task) => {
              return task.name.match(this.search)
                && (task.isCompleted && this.isCompleted
                  || !task.isCompleted && this.isNotCompleted);
            })
          }
        },
        methods: {
          addTask() {
            this.$router.push('/tasks/create');
          },
          async changeStatus(task) {
            await this.$store.dispatch('ChangeStatus', task);
          },
          async deleteTask(id) {
            await this.$store.dispatch('DeleteTask', id);
          },
          async viewCard(task) {
            this.$router.push(`/tasks/${task.id}`);
          }
        }
    }
</script>

<style scoped>
  .content {
    overflow: auto;
    width: 100%;
    height: 92%;
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
  .filter-input {
    flex: auto;
    font-size: 20px;
    width: 30px;
    padding: 5px 10px;
    margin: 0 20px;
    border-radius: 4px;
    outline: none;
    border: solid 1px #8B8B8B;
  }

  .filter-input:hover {
    background-color: #eeeeee;
    box-shadow: 0px 2px 2px 0px #8E8E8E;
  }
  .filter-input:focus {
    background-color: #eeeeee;
    box-shadow: 0px 2px 2px 0px #8E8E8E;
  }
  .filter-input:active {
    border: solid 1px black;
    box-shadow: 0px 2px 2px 0px #8E8E8E;
  }

  .filter-cbx {
    margin-left: auto;
    display: flex;
    flex-direction: column;
    justify-content: right;
    margin-right: 10px;
  }

  .add-btn {
    margin: 0 20px;
    cursor: pointer;
    border-radius: 4px;
    outline: none;
    border: 0;
    background-color: transparent;
    opacity: 0.6;
  }

  .add-btn:hover {
    opacity: 1;
  }

  .add-btn:active {
    opacity: 1;
  }

  .main {
    padding: 10px;
    font-family: Gill Sans, sans-serif;
    font-size: 20px;
  }
  .tasks-list {
    list-style-type: none;
    padding: 0;
  }
  .task {
    display: flex;
    background-color: #C1C1C1;
    align-items: center;
    border-radius: 2px;
    cursor: pointer;
    width: 95%;
    margin: 8px auto;
    box-shadow: 0px 2px 2px -1px #8E8E8E;
  }

  .task:hover {
    background-color: #AFAFAF;
  }
  .task:active {
    opacity: 0.7;
  }

  .name-task {
    flex: 1;
    padding: 15px;
  }
  .change-btn-block {
  }

  .complete-btn {
       background-color: transparent;
       border: 0;
       outline: none;
       cursor: pointer;
       opacity: 0.5;
  }

  .complete-btn:hover {
    opacity: 1;
  }
  .delete-btn {
    background-color: transparent;
    border: 0;
    outline: none;
    cursor: pointer;
    opacity: 0.5;
  }
  .delete-btn:hover {
    opacity: 1;
  }
</style>
