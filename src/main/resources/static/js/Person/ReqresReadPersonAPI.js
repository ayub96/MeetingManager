'use strict';

const createPersonButton = document.querySelector('#createPersonButton');
const readAllPersonBtn = document.querySelector(`#readAllPersonBtn`);
const readPersonBtn = document.querySelector(`#readPersonBtn`);

const retrieveData = () => {
    fetch(`http://localhost:8081/person/readAll`)
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
                        p.setAttribute("class", "person");
                        let info = document.createTextNode(json[i].firstname + ` ` + json[i].surname + ` ` + json[i].email);
                        p.appendChild(info);
                        peeps.appendChild(p);
                    }
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
readAllPersonBtn.addEventListener(`click`, retrieveData);

const retrieveOneData = () => {
    fetch(`http://localhost:8081/person/read/` + document.querySelector(`#personId`).value)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`There was an error! Status code: ` + response.status);
                return;
            } else {
                let personId = parseInt(document.querySelector(`#personId`).value);
                response.json().then(json => {
                        let q = document.createElement("p");
                        let info = document.createTextNode(json[personId].firstname + ` ` + json[personId].surname + ` ` + json[personId].email);
                        q.appendChild(info);
                        peep.appendChild(q);
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
readPersonBtn.addEventListener(`click`, retrieveOneData);