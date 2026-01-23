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


function displayProgrammeurs(programmeurs) {
    // where the programmers are going to be displayed
    const container = document.getElementById('programmeurContainer');

    // loop through each employee and create a div
    programmeurs.forEach(programmeur => {
        // creating a div + adding a class for styling
        const programmerDiv = document.createElement('div');
        programmerDiv.className = 'widget';

        // display the data retrieved from the database
        programmerDiv.innerHTML = `
            <img src="default-pfp.jpg"/>
            <h3>${programmeur.prenom} ${programmeur.nom} <span>#${programmeur.id}</span></h3>
            <p>Adresse : ${programmeur.adresse}</p>
            <p>Salaire : ${programmeur.salaire.toLocaleString()}€/mois</p>
            <button type="button">Supprimer</button>
        `;

        // adding the new widget to the container
        container.appendChild(programmerDiv);
    });
}