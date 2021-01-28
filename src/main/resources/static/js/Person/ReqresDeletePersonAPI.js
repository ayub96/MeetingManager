'use strict';

const deletePerson = () => {
    console.log(document.querySelector(`#personId`).value);
    fetch("http://localhost:8081/person/delete/" + document.querySelector(`#personId`).value, {
        method: 'DELETE',
        body: JSON.stringify({
            id: document.querySelector(`#personId`).value
        }),
        headers: {
            'Content-Type': "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(`Seomthing went wrong`));
}
document.querySelector(`#delPersonBtn`).addEventListener('click', deletePerson);