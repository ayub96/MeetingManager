'use strict';

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
                    let p = document.createElement("p");
                    p.appendChild(document.createElement('br'));
                    document.getElementById(`peeps`).innerHTML = ``;
                    for (let i = 0; i < json.length; i++) {
                        p.appendChild(document.createTextNode(`[ID: ` + json[i].id + `]`));
                        p.appendChild(document.createElement('br'));
                        p.appendChild(document.createTextNode(` first name: ` + json[i].firstname));
                        p.appendChild(document.createElement('br'));
                        p.appendChild(document.createTextNode(` surname: ` + json[i].surname));
                        p.appendChild(document.createElement('br'));
                        p.appendChild(document.createTextNode(` email: ` + json[i].email));
                        p.appendChild(document.createElement('br'));
                        peeps.appendChild(p);
                    }
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
document.querySelector(`#readAllPersonBtn`).addEventListener(`click`, retrieveData);

const retrieveOneData = () => {
    fetch(`http://localhost:8081/person/read/` + document.querySelector(`#readPersonId`).value)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`There was an error! Status code: ` + response.status);
                return;
            } else {
                response.json().then(json => {
                    document.getElementById(`peep`).innerHTML = ``;
                    let p = document.createElement("p");
                    p.appendChild(document.createElement('br'));
                    p.appendChild(document.createTextNode(`[ID: ` + json.id + `]`));
                    p.appendChild(document.createElement('br'));
                    p.appendChild(document.createTextNode(` first name: ` + json.firstname));
                    p.appendChild(document.createElement('br'));
                    p.appendChild(document.createTextNode(` surname: ` + json.surname));
                    p.appendChild(document.createElement('br'));
                    p.appendChild(document.createTextNode(` email: ` + json.email));
                    p.appendChild(document.createElement('br'));
                    peep.appendChild(p);
            })
            }
        }).catch(err => (`There was an error! ${err}`));
}
document.querySelector(`#readPersonBtn`).addEventListener(`click`, retrieveOneData);