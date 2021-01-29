'use strict';

const updateFunction = () => {  
    fetch(`http://localhost:8081/person/update/` + document.querySelector(`#updatePersonId`).value, {
        method: `PUT`,
        body: JSON.stringify({
            id: document.querySelector(`#updatePersonId`).value,
            firstname: document.querySelector(`#updateFirstName`).value,
            surname: document.querySelector(`#updateSurname`).value,
            email: document.querySelector(`#updateEmail`).value,
            room_id: document.querySelector(`#updateRoomId`).value
        }),
        headers:{
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(err));
}

document.querySelector('#updatePersonButton').addEventListener('click', updateFunction);