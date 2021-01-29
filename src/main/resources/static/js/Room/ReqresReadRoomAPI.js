'use strict';

const retrieveData = () => {
    fetch(`http://localhost:8081/room/readAll`)
        .then((response) => {
            console.log(response);
            if (response.status !== 200) {
                console.error(`There was an error! Status code: ` + response.status);
                return;
            } else {
                response.json().then(json => {
                    console.log(json);
                    for (let i = 0; i < json.length; i++) {
                        let p = document.createElement("p");
                        let br = document.createElement("br");
                        let info = `[(ID): ` + json[i].id + `,`
                                    + ` (Room Number): ` + json[i].roomNumber + `,`
                                    + ` (Room Type): ` + json[i].roomType + `] `;
                        let infoNode = document.createTextNode(info);
                        p.appendChild(infoNode);
                        
                        let people = "";
                        for(let j=0; j<json[i].people.length; j++){
                            people = `[(ID): ` + json[i].people[j].id + `,`
                                    + ` (First name): ` + json[i].people[j].firstname + `,`
                                    + ` (Surname): ` + json[i].people[j].surname + `,`
                                    + ` (Email): ` + json[i].people[j].email + `] `;
                            let peopleNode = document.createTextNode(people);
                            p.appendChild(peopleNode);
                        }
                        
                        peeps.appendChild(p);
                    }
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
document.querySelector(`#readAllRoomsBtn`).addEventListener(`click`, retrieveData);

const getPeople = (i) => {

    for(let j=0; j<json[i].people.length; j++){
        let p = document.createElement("p");
        let info = document.createTextNode(` First name: ` + json[i].people[j].firstname);
        p.appendChild(info);
    }


}

const retrieveOneData = () => {
    fetch(`http://localhost:8081/room/read/` + document.querySelector(`#readRoomId`).value)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`There was an error! Status code: ` + response.status);
                return;
            } else {
                response.json().then(json => {
                    let p = document.createElement("p");
                    p.setAttribute("class", "room");
                    let info = document.createTextNode(`id: ` + json.id
                                                        + ` Room Number: ` + json.roomNumber
                                                        + ` Room Type: ` + json.roomType);
                    p.appendChild(info);





                    peep.appendChild(p);
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
document.querySelector(`#readRoomBtn`).addEventListener(`click`, retrieveOneData);

// let info = document.createTextNode(`id: ` + json.id + `Room Number: ` + json.roomNumber 
//                                                             + `Room Type: ` + json.roomType);
