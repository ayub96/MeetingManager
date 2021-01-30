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
                    let p = document.createElement("p");
                    p.appendChild(document.createElement('br'));
                    document.getElementById(`peeps`).innerHTML = ``;
                    for (let i = 0; i < json.length; i++) {
                        p.appendChild(document.createTextNode(`[Room ID]: ` + json[i].id));
                        p.appendChild(document.createElement('br'));
                        p.appendChild(document.createTextNode(` Room Number: ` + json[i].roomNumber));
                        p.appendChild(document.createElement('br'));
                        p.appendChild(document.createTextNode(` Room Type: ` + json[i].roomType));
                        p.appendChild(document.createElement('br'));
                        
                        p.appendChild(document.createTextNode(` People:`));
                        p.appendChild(document.createElement('br'));
                        let people = "";
                        for(let j=0; j<json[i].people.length; j++){
                            p.appendChild(document.createTextNode(`  [Person ID]: ` + json[i].people[j].id));
                            p.appendChild(document.createElement('br'));
                            p.appendChild(document.createTextNode(`   First name: ` + json[i].people[j].firstname));
                            p.appendChild(document.createElement('br'));
                            p.appendChild(document.createTextNode(`   Surname: ` + json[i].people[j].surname));
                            p.appendChild(document.createElement('br'));
                            p.appendChild(document.createTextNode(`   Email: ` + json[i].people[j].email));
                            p.appendChild(document.createElement('br'));
                        }
                        peeps.appendChild(p);
                        
                    }
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
document.querySelector(`#readAllRoomsBtn`).addEventListener(`click`, retrieveData);

const retrieveOneData = () => {
    fetch(`http://localhost:8081/room/read/` + document.querySelector(`#readRoomId`).value)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`There was an error! Status code: ` + response.status);
                return;
            } else {
                response.json().then(json => {
                    document.getElementById(`peep`).innerHTML = ``;
                    let p = document.createElement("p");
                    p.appendChild(document.createElement('br'));
                    p.appendChild(document.createTextNode(`[Room ID]: ` + json.id));
                    p.appendChild(document.createElement('br'));
                    p.appendChild(document.createTextNode(` Room Number: ` + json.roomNumber));
                    p.appendChild(document.createElement('br'));
                    p.appendChild(document.createTextNode(` Room Type: ` + json.roomType));
                    p.appendChild(document.createElement('br'));
                    
                    p.appendChild(document.createTextNode(` People:`));
                    p.appendChild(document.createElement('br'));
                    let people = "";
                        for(let j=0; j<json.people.length; j++){
                            p.appendChild(document.createTextNode(`  [Person ID]: ` + json.people[j].id));
                            p.appendChild(document.createElement('br'));
                            p.appendChild(document.createTextNode(`   First name: ` + json.people[j].firstname));
                            p.appendChild(document.createElement('br'));
                            p.appendChild(document.createTextNode(`   Surname: ` + json.people[j].surname));
                            p.appendChild(document.createElement('br'));
                            p.appendChild(document.createTextNode(`   Email: ` + json.people[j].email));
                            p.appendChild(document.createElement('br'));
                        }
                        peep.appendChild(p);
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
document.querySelector(`#readRoomBtn`).addEventListener(`click`, retrieveOneData);