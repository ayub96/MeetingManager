'use strict';

const BASE_URL = "http://localhost:8081";

// const getUsers = document.querySelector(`#getUsers`);
// const peeps = document.querySelector(`#peeps`);

// const createURL = "http://localhost:8081/person/create";

// const createbtn = document.querySelector('#createPerson');

function displayOutput(element, {data}) {
    document.getElementById(element).innerText = JSON.stringify(data, undefined, 2);
}

document.getElementById('createRoomButton').addEventListener('click', function () {
    let data = {};
    document.querySelectorAll('#create > input').forEach(el => data[el.name] = el.value);
    axios.post(BASE_URL + '/room/create', data)
        .then(res => displayOutput("createOutput", res)
        ).catch(err => console.error(err));
});

// const retrieveData = () => {
//     fetch("http://localhost:8082/customer/getAll")
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

// getUsers.addEventListener(`click`, retrieveData);

// function createUsers() {
//     fetch(createURL, {
//         method: "POST",
//         body: JSON.stringify({
//             "firstname": "John",
//             "surname": "Smith",
//             "email": "me@hotmail.com"
//         }),
//         headers: {
//             "Content-Type": "application/json"
//         }
//     })
//         .then(response => response.json())
//         .then(json => console.log(json))
//         .catch(err => console.error(`Error!!`, err));
// }

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