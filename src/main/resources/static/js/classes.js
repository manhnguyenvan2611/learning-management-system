/**
 * open course information
 */
let courseInfor = document.querySelector('.courseInfor');
let courseInforCloseBtn = document.querySelector('.courseInfor__close__btn');
let classListCourseInforMore = document.querySelector('.classList__courseInfor__more');

openCourseInfor();
function openCourseInfor() {
    classListCourseInforMore.onclick = function() {
        courseInfor.style.display = 'flex';
        updateCourseDOM();
        closeCourseInfor(courseInfor, courseInforCloseBtn);
    }
}

function closeCourseInfor(courseInfor, courseInforCloseBtn) {
    courseInforCloseBtn.onclick = function() {
        courseInfor.style.display = 'none';
    }
}

function updateCourseDOM() {
    courseInfor = document.querySelector('.courseInfor');
    courseInforCloseBtn = document.querySelector('.courseInfor__close__btn');
    classListCourseInforMore = document.querySelector('.classList__courseInfor__more');
}

/**
 * scroll course navigation
 */
let classListNavNavigateBtn = document.querySelectorAll('.classList__nav__navigateBtn');
let classListNavBtnContainer = document.querySelector('.classList__nav__btns');

classListNavNavigateBtn.forEach((btn) => {
    btn.onclick = function() {
        let btnType = btn.id;
        let scrollAmount = btnType == "list-left"? -100: 100;

        classListNavBtnContainer.scrollBy({left: scrollAmount, behavior: "smooth"});
    }
});

/**
 * navigate to see student list of each course
 * and course's information
 * each time clicking on a Course button
 */
let classListNavBtns = document.querySelectorAll('.classList__nav__btn');
let buttonNumber = classListNavBtns.length;

classListNavBtns[0].style.backgroundColor = '#9DA7E6';
classListNavBtns[0].style.color = 'white';

classListNavBtns.forEach((btn, index) => {
    btn.onclick = function() {
        displayStudentList(index);
        displayCourseInfor(index);
        openCourseInfor();

        btn.style.backgroundColor = '#9DA7E6';
        btn.style.color = 'white';

        for (let i = 0; i < buttonNumber; i++) {
            if (i != index) {
                classListNavBtns[i].style.backgroundColor = 'white';
                classListNavBtns[i].style.color = 'black';
            }
        }
    }
});

/**
 * display student list for each course
 * @param {*} currentListIdx 
 */
displayStudentList(0);
function displayStudentList(currentListIdx) {
    let currentStudentList = enrolledUsers[currentListIdx];
    let classListStudents = document.querySelector('.classList__students');
    let currentStudentListHtml = '';

    currentStudentList.forEach(student => {
        // let avatarPath = student.avatar == ''? '': '/assets/avatar/' + student.avatar; 
        let avatarPath = ""; 

        currentStudentListHtml += `
            <div class="classList__students__student">
                <div class="student__appearence">
                    <div class="student__appearence__avatar">
                        <img class="student__avatar__img" src="${avatarPath}" alt="avatar"/>
                    </div>
                    <h4 class="student__name">
                        ${student.username}
                    </h4>
                </div>

                <div class="student__contact">
                    <span class="student__learning__status">${student.courseStatus}</span>
                    <h4 class="student__email">${student.email}</h4>
                </div>
            </div>
        `;
    });

    classListStudents.innerHTML = currentStudentListHtml;
}

/**
 * display course's information
 * @param {*} currentCourseIdx 
 */
displayCourseInfor(0);
function displayCourseInfor(currentCourseIdx) {
    let currentCourse = courses[currentCourseIdx];
    let courseThumbnail = currentCourse.thumbnail;
    let courseInfor = document.querySelector('.courseInfor');
    let courseContentHtml = `
        <h2 class="courseInfor__heading">${currentCourse.course_name}</h2>
        <div class="courseInfor__details">
            <time class="courseInfor__details__term">Updated on ${currentCourse.timestamp}</time>
            <div class="courseInfor__details__term">Language: English</div>
            <div class="courseInfor__details__term">Author: Phani Avagaddi</div>
            <div class="courseInfor__details__term">${currentCourse.description}</div>
            <div class="courseInfor__details__term">
                <span>4.6</span>
                <img class="icon-small" src="/assets/icons/star.png" alt="star"/>
            </div>
        </div>
        <div class="courseInfor__thumbnail">
            <img class="thumbnail__img" src="/assets/courses/${courseThumbnail}" alt="thumbnail"/>
        </div>

        <button class="courseInfor__close__btn">Close</button>
    `;

    courseInfor.innerHTML = courseContentHtml;
}

/**
 * display number of student
 * @param {*} studentList 
 * @returns number of student have register at least one course
 */
function countStudent(studentList) {
    let studentNumber = 0;
    let uniqueStudentEmails = [];

    studentList.forEach(list => {
        list.forEach(student => {
            if (!uniqueStudentEmails.includes(student.email)) {
                uniqueStudentEmails.push(student.email);
            }
        });
    });

    studentNumber = uniqueStudentEmails.length;
    return studentNumber;
}

let classListCourseInforHeading = document.querySelector('.classList__courseInfor__heading');
classListCourseInforHeading.innerText = countStudent(enrolledUsers) + ' Student';

