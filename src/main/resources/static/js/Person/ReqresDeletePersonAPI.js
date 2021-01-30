'use strict';

const deletePerson = () => {
    fetch("http://localhost:8081/person/delete/" + document.querySelector(`#personId`).value, {
        method: 'DELETE',
        body: JSON.stringify({
            id: document.querySelector(`#personId`).value
        }),
        headers: {
            'Content-Type': "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(`Seomthing went wrong`));
}
document.querySelector(`#delPersonBtn`).addEventListener('click', deletePerson);

const deleteAllPeople = () => {
    fetch(`http://localhost:8081/person/readAll`)
        .then((response) => {
            console.log(response);
            if (response.status !== 200) {
                console.error(`There was an error! Status code: ` + response.status);
                return;
            } else {
                response.json().then(json => {
                    for(let i=1; i<json.length+1; i++){
                        fetch("http://localhost:8081/person/delete/" + i, {
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
                            .catch(err => console.error(`Seomthing went wrong`));
                    }
                })
            }
        }).catch(err => (`There was an error! ${err}`));
}
document.querySelector(`#delAllPeople`).addEventListener('click', deleteAllPeople);