let list = document.getElementById('comments-list');

function setLoading(loading) {
    let loadingElement = document.getElementById('loading');
    loadingElement.style.display = loading ? '' : 'none';
}

function getComments(id) {
    this.setLoading(true);
    fetch(`https://jsonplaceholder.typicode.com/posts/${id}/comments`, { method: 'get', referrer: '' })
        .then(data => {
                data.json().then(res => {
                    list.innerHTML = "";
                    for (let i of res) {
                        let elem = document.createElement('li');
                        elem.innerHTML = `<h5><b>${i.email}</b></h5> ${i.body} <br>`;
                        list.append(elem);
                    }
                    this.setLoading(false);
                })
            }
        );
}

getComments(list.getAttribute("data"));