window.onload = function () {
    loadProgrammeurs();
};

async function loadProgrammeurs() {
    try {
        // fetching data from the API (the infos about the programmers/employees)
        const response = await fetch('http://localhost:8000/api/employees');

        // checking if the data fetching has been successful
        if (!response.ok) {
            throw new Error('failed to fetch employees :(');
        }

        // Parse the JSON response
        const programmers = await response.json();

        // display the employees in the HTML file
        displayProgrammeurs(programmers);

    } catch (error) {
        // display error in console and in the html page
        console.error('Error loading employees:', error);
        document.getElementById('employeeContainer').innerHTML =
            '<p>Error loading employees :(</p>';
    }
}

function displayProgrammeurs(programmers) {
    // where the programmers are going to be displayed
    const container = document.getElementById('programmeurContainer');

    // loop through each employee and create a div
    programmers.forEach(programmer => {
        // creating a div + adding a class for styling
        const programmerDiv = document.createElement('div');
        programmerDiv.className = 'widget';

        // display the data retrieved from the database
        programmerDiv.innerHTML = `
            <img src="default-pfp.jpg"/>
            <h3>${programmer.prenom} ${programmer.nom} #<span>${programmer.id}</span></h3>
            <p>Pseudo : ${programmer.pseudo}<p/>
            <p>Année de naissance : ${programmer.anneeNaissance}<p/>
            <p>Adresse : ${programmer.adresse}</p>
            <p>Hobby : ${programmer.hobby}<p/>
            <p>Responsable : ${programmer.responsable}</p>
            <p>Salaire : ${programmer.salaire.toLocaleString()}€/mois</p>
            <p>Prime : ${programmer.prime}€<p>
            <button type="button" onclick=deleteProgrammeur(${programmer.id})>Supprimer</button>
        `;

        // adding the new widget to the container
        container.appendChild(programmerDiv);
    });
}

async function deleteProgrammeur(id) {
    // demander confirmation à l'utilisateur
    if (confirm(`Voulez-vous supprimer le programmeur d'ID ${id}?`)) {
        // envoie une requête à l'API
        const response = await fetch(`http://localhost:8000/api/employees/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        const result = await response.json();

        // rafraîchit la page si la requête a bien été effectuée
        if (result.success) {
            alert("Le programmeur a bien été supprimé ! :D");
            location.reload()
        }
        else {
            alert("Il y a eu une erreur dans la suppression...");
        }
    }
}

function searchProgrammeur() {
    // recupère l'input en barre de recherche
    let input = document.getElementById('searchBar');
    let filter = input.value;
    let txtValue;

    // liste des programmeurs
    let programmeurList = document.getElementsByClassName('widget');
    console.log(programmeurList);

    for (let i = 0; i < programmeurList.length; i++) {
        // cherche l'ID du programmeur
        id = programmeurList[i].getElementsByTagName("span")[0];
        txtValue = id.textContent || id.innerText;

        // affiche le programmeur avec l'ID correspondant, cache les autres
        if (txtValue.indexOf(filter) > -1) {
            programmeurList[i].style.display = "";
        } else {
            programmeurList[i].style.display = "none";
        }
    }
}

function addProgrammeur() {
    const dialog = document.getElementById('dialog-box');
    dialog.showModal();

    window.onclick = function (event) {
        if (event.target == dialog) {
            dialog.style.display = "none";
        }
    }
}