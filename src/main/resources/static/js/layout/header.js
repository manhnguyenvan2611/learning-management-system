/**
 * open menu in header
 */
let headerMenuBtn = document.querySelector('.header__menu__btn');
let navLeft = document.querySelector('.nav-left');
let body = document.querySelector('body');
let bodyWidth = body.clientWidth;

if (bodyWidth <= 768) {
    updateNavigationLayout();
}

function updateNavigationLayout() {
    headerMenuBtn.onclick = function() {
        navLeft.style.display = 'flex';
    }

    document.addEventListener('click', (event) => {
        if (headerMenuBtn.contains(event.target)) {
            navLeft.style.display = 'flex'; 
        } else {
            navLeft.style.display = 'none';
        }
    });
}