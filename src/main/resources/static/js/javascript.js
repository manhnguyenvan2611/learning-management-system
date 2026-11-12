/**
 * clear local storage when the user log out
 */
resetLocalStorageOnLogout();
function resetLocalStorageOnLogout() {
    let logoutLink = document.querySelector('.logout-link');

    logoutLink.onclick = function() {
        localStorage.clear();
    }
}