'use strict';

function createUsers() {
    let data = {};
    data["id"] = document.querySelector(`#roomId`).value;
    fetch(`http://localhost:8081/person/create`, {
        method: "POST",
        body: JSON.stringify({
            firstname: document.querySelector(`#createFirstName`).value,
            surname: document.querySelector(`#createSurname`).value,
            email: document.querySelector(`#createEmail`).value,
            room: data
        }),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(`Error!!`, err));
}
document.querySelector('#createPersonButton').addEventListener('click', createUsers);

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



// const updateFunction = () => {  // PUT updates one thing
//     fetch("http://jsonplaceholder.typicode.com/posts/1", {
//         method: "PUT",
//         body: JSON.stringify({
//             title:"the quick brown fox"
//         }),
//         headers:{
//             "Content-Type": "application/json"
//         }
//     })
//         .then(response => response.json())
//         .then(json => console.log(json))
//         .catch(err => console.error(err));
// }

// createbtn.addEventListener('click', createPerson);