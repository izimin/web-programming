document.getElementById("del").setAttribute("disabled", 'disabled');

function setLoading(loading) {
    let loadingElement = document.getElementById('loading');
    loadingElement.style.display = loading ? '' : 'none';
}

function changeStatusTask(id) {
    let btnComplete = document.getElementById(`btnComplete${id}`);
    let task = document.getElementById(id);
    let alertDesc = document.getElementById(`alert${id}`);

    this.setLoading(true);
    fetch(`/task/changeStatus/${id}`, { method: 'put' })
        .then(() => {
            if (task.getAttribute("data") === 'false') {
                btnComplete.setAttribute("value", 'Вернуть в работу');
                task.setAttribute("class", 'list-item task-completed');
                alertDesc.setAttribute("class", 'alert alert-success');
                task.setAttribute("data", 'true');
                filterInputed();
            } else {
                btnComplete.setAttribute("value", 'Завершить');
                task.setAttribute("class", 'list-item task-not-completed');
                alertDesc.setAttribute("class", 'alert alert-info');
                task.setAttribute("data", 'false');
                filterInputed();
            }
        });
    this.setLoading(false);
}

function removeTask(id) {
    if (!confirm('Вы уверены?')) {
        return;
    }

    this.setLoading(true);
    fetch(`/task/del/${id}`, { method: 'delete' })
        .then(() => {
            document.getElementById(id).remove();
            this.setLoading(false);
        });
}

function filterInputed() {
    const filterText = document.getElementById('filter').value;
    const tasks = document.querySelectorAll('.list-item');
    const status = document.getElementById('radioAll').checked ? 1
        : document.getElementById('radioOnlyCompleted').checked ? 2 : 3;
    for (const task of tasks) {
        const taskName = task.querySelector('.name-task').textContent;
        if (taskName.indexOf(filterText) !== -1 ) {
            task.style.display = '';
        } else {
            task.style.display = 'none';
        }

        if (task.getAttribute("data") === 'true' && status === 3) {
            task.style.display = 'none';
        }
        if (task.getAttribute("data") === 'false' && status === 2) {
            task.style.display = 'none';
        }
    }
}