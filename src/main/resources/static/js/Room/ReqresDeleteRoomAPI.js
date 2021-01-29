'use strict';

const deleteRoom = () => {
    fetch("http://localhost:8081/room/delete/" + document.querySelector(`#deleteRoomId`).value, {
        method: 'DELETE',
        body: JSON.stringify({
            id: document.querySelector(`#deleteRoomId`).value
        }),
        headers: {
            'Content-Type': "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(`Seomthing went wrong`));
}
document.querySelector(`#delRoomBtn`).addEventListener('click', deleteRoom);