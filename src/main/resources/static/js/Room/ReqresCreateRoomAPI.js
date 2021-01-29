'use strict';

function createRoom() {
    fetch(`http://localhost:8081/room/create`, {
        method: "POST",
        body: JSON.stringify({
            roomNumber: document.querySelector(`#createRoomNo`).value,
            roomType: document.querySelector(`#createRoomType`).value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(`Error!!`, err));
}
document.querySelector('#createRoomButton').addEventListener('click', createRoom);