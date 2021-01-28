'use strict';

const BASE_URL = "http://localhost:8081";

const updatePersonButton = document.querySelector('#updatePersonButton');
const updatePersonId = document.querySelector(`#updatePersonId`);

const updateFunction = () => {  
    fetch(BASE_URL + `/person/update/` + document.querySelector(`#updatePersonId`).value, {
        method: `PUT`,
        body: JSON.stringify({
            firstname: document.querySelector(`#updateFirstName`),
            surname: document.querySelector(`#updateSurname`),
            email: document.querySelector(`#updateEmail`),
            room_id: document.querySelector(`#updateRoomId`)
        }),
        headers:{
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(err));
}

// const updatePet = () => {
//     fetch(createURL, {
//         method: 'PUT',
//         body: JSON.stringify({
//             id: '1',
//             name: "Zane"
//         }),
//         headers: {
//             'Content-Type': "application/json"
//         }
//     })
//         .then(response => response.json())
//         .then(json => console.log(json))
//         .catch(err => console.error(`Seomthing went wrong`));
// }

updatePersonButton.addEventListener('click', updateFunction);

// function createUsers() {
//     fetch(BASE_URL + `/person/create`, {
//         method: "POST",
//         body: JSON.stringify({
//             firstname: document.querySelector(`#createFirstName`).value,
//             surname: document.querySelector(`#createSurname`).value,
//             email: document.querySelector(`#createEmail`).value,
//             room_id: document.querySelector(`#roomId`).value
//         }),
//         headers: {
//             "Content-Type": "application/json"
//         }
//     })
//         .then(response => response.json())
//         .then(json => console.log(json))
//         .catch(err => console.error(`Error!!`, err));
// }
// createPersonButton.addEventListener('click', createUsers);

// const retrieveData = () => {
//     console.log("hello");
//     fetch(BASE_URL + `/person/readAll`)
//         .then((response) => {
//             console.log(response);
//             if (response.status !== 200) {
//                 console.error(`There was an error! Status code: ` + response.status);
//                 return;
//             } else {
//                 response.json().then(json => {
//                     console.log(json);
//                     for (let i = 0; i < json.length; i++) {
//                         let p = document.createElement("p");
//                         p.setAttribute("class", "customer");
//                         let info = document.createTextNode(json[i].firstname);
//                         p.appendChild(info);
//                         peeps.appendChild(p);
//                     }
//                 })
//             }
//         }).catch(err => (`There was an error! ${err}`));
// }

// readAllPersonBtn.addEventListener(`click`, retrieveData);


// const createbtn = document.querySelector('#createPerson');

// function displayOutput(element, {data}) {
//     document.getElementById(element).innerText = JSON.stringify(data, undefined, 2);
// }

// document.getElementById('createPersonButton').addEventListener('click', function () {
//     let data = {};
//     data[createFirstName] = document.querySelector('#createFirstName').value;
//     data[createSurname] = document.querySelector('#createSurname').value;
//     data[createEmail] = document.querySelector('#createEmail').value;
//     axios.post(BASE_URL + '/person/create', data)
//         .then(res => displayOutput("createOutput", res)
//         ).catch(err => console.error(err));
// });

// document.getElementById('createButton').addEventListener('click', function () {
//     let data = {};
//     document.querySelectorAll('#create > input').forEach(el => data[el.name] = el.value);
//     axios.post(BASE_URL + '/duck/createDuck', data)
//         .then(res => displayOutput("createOutput", res)
//         ).catch(err => console.error(err));
// });



