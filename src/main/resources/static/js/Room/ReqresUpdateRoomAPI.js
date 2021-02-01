'use strict';

const updateFunction = () => {  
    fetch(`http://localhost:8081/room/update/` + document.querySelector(`#RoomId`).value, {
        method: `PUT`,
        body: JSON.stringify({
            roomNumber: document.querySelector(`#updateRoomNumber`).value,
            roomType: document.querySelector(`#updateRoomType`).value
        }),
        headers:{
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(err));
}
document.querySelector('#updateRoomButton').addEventListener('click', updateFunction);