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
        .catch(err => console.error(`Something went wrong`));
}
document.querySelector(`#delRoomBtn`).addEventListener('click', deleteRoom);

const deleteAllRooms = () => {
    fetch(`http://localhost:8081/room/readAll`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`There was an error! Status code: ` + response.status);
                return;
            } else {
                response.json().then(json => {
                    for(let i=1; i<json.length+1; i++){
                        fetch("http://localhost:8081/room/delete/" + i, {
                            method: 'DELETE',
                            body: JSON.stringify({
                                id: i
                            }),
                            headers: {
                                'Content-Type': "application/json"
                            }
                        })
                            .then(response => response.json())
                            .then(json => console.log(json))
                            .catch(err => console.error(`Something went wrong`));
                    }
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
document.querySelector(`#delAllRoomsBtn`).addEventListener('click', deleteAllRooms);