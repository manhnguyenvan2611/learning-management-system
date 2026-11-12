/**
 * showing password
 */
showingPassword();
function showingPassword() {
    let showPasswordBtn = document.querySelector('.show__password__btn');
    let eyeImg = document.querySelector('.eye__img');
    let inputPassword = document.querySelector('.input__password');

    showPasswordBtn.onclick = function(event) {
        event.preventDefault();
        
        let inputType = inputPassword.type;

        if (inputType === "password") {
            inputPassword.type = "text";
            eyeImg.src = "/assets/icons/eye-open.png";
        } else {
            inputPassword.type = "password";
            eyeImg.src = "/assets/icons/eye-close.png";
        }
    }
}