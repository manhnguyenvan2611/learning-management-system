/**
 * sign up form
 */
let signupFormSubmitBtn = document.querySelector('.signup-btn');

/**
 * events happen when the user click on sign up button:
 * 1. retrieving user's information: getFormInputInformation()
 * 2. validating user's inputs: validateFormInputInformation(username, email, password)
 * 3. submiting user's information to server: submitToUser(username, email, password)
 */
signupFormSubmitBtn.addEventListener('click', (event) => { 
    event.preventDefault(); 

    let userObject = getFormInputInformation();
    let username = userObject.username;
    let email = userObject.email;
    let password = userObject.password;

    if (validateFormInputInformation(username, email, password)) { 
        submitToUser(username, email, password);
    }
});

/**
 * 
 * @returns an object with three properties: username, email, password
 */
function getFormInputInformation() {
    let signupFormInput = document.querySelectorAll('.signup__form__input');
    let userObject = {};

    signupFormInput.forEach(input => {
        let inputName = input.name;

        if (inputName == "username") {
            userObject['username'] = input.value;
        } 
        
        if (inputName == "email") {
            userObject['email'] = input.value;
        } 
        
        if (inputName == "password") {
            userObject['password'] = input.value;
        }
    });

    return userObject;
}

/**
 * 
 * @param {*} username 
 * @param {*} email 
 * @param {*} password 
 * @returns true if three fields are not empty at the same time
 */
function validateFormInputInformation(username, email, password) {
    return username != '' && email != '' && password != ''; 
}

function submitToUser(username, email, password) {
    fetch(`/registeruser`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username,
            email: email,
            password: password
        })
    })
    .then(response => response.json())
    .then(data => {
        handlingSigupErrors(data);
    })
    .catch(error => console.error('Error:', error));
}

/**
 * display signup result to the user 
 * @param {*} data 
 */
function handlingSigupErrors(data) {
    let signupErrors = document.querySelector('.signup-errors'); 
    let signupNoti = document.querySelector('.signup-noti'); 
    let success = data.success;
    let errors = data.errors;
    let errorHtml = '';

    if (success == "true") {
        signupNoti.innerHTML = `You have registered successfully! <a style="font-weight: 750; 
            cursor: pointer;" onclick="window.location.href='/login'">Login now</a>`;
    } else {
        errors.forEach(error => {
            errorHtml += `<div>${error}</div>`
        });
    }

    signupErrors.innerHTML = errorHtml;
}