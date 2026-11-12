/**
 * register a course 
 */
registerCourse();
function registerCourse() {
    let courseInforProgress = document.querySelector('.courseInfor__progress');
    let courseElement = document.querySelector('.course-id');
    let statusElement = document.querySelector('.status');
    
    courseInforProgress.addEventListener('click', (event) => {
        event.preventDefault();
        let courseId = COURSE_ID.toString();
        let status = statusElement.value

        fetch(`/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                courseId: courseId,
                status: status
            })
        })
        .then(response => response.json())
        .then(data => {
            displayLoading();
            setTimeout(() => {notifyRegisterResult(data)}, 1500);
        })
        .catch(error => console.error('Error:', error));
    });
}

/**
 * enter lesson
 */
joiningLesson();
function joiningLesson() {
    let lessonOptionsBtns = document.querySelectorAll('.lesson__options__btn');
    let lessonLessonIds = document.querySelectorAll('.lesson-lesson-id');

    lessonOptionsBtns.forEach((btn, index) => {
        btn.addEventListener('click', (event) => {
            event.preventDefault();
            
            let lessonIdx = btn.id;
            let courseId = COURSE_ID.toString();
            let lessonId = lessonLessonIds[index].value;

            localStorage.setItem('currentLessonIdx', lessonIdx);

            fetch(`/joinlesson`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    courseId: courseId,
                    lessonId: lessonId
                })
            })
            .then(response => response.json())
            .then(data => {
                displayLoading();
                setTimeout(() => {enteringLesson(data)}, 1500);
            })
            .catch(error => console.error('Error:', error));
        });
    });
}

/**
 * notification for registering a course
 * @param {*} response 
 */
function notifyRegisterResult(response) {
    let lessonsNoti = document.querySelector('.lessonsNoti');
    let lessonsNotiHeading = document.querySelector('.lessonsNoti__heading');
    let registerStatusElement = document.querySelector('.courseInfor__progress__value span');
    let registerBtn = document.querySelector('.courseInfor__progress__registerBtn');
    let reponseObj = response;
    let registerMessage = reponseObj.registerMessage;
    let success = reponseObj.success;
    let registerStatus = reponseObj.registerStatus;
    let registerState = reponseObj.registerState;
    let lessonId = reponseObj.lessonId;
    let courseId = reponseObj.courseId;

    lessonsNoti.style.display = 'flex';
    lessonsNotiHeading.innerText = registerMessage;
    lessonsNotiHeading.style.fontSize = '24px';
    registerStatusElement.innerText = registerStatus;
    registerBtn.innerText = registerState;

    if (success == "true") {
        lessonsNotiHeading.style.color = "#00FF7F";
        localStorage.setItem(`lesson-${courseId}-${lessonId}`, true);
        document.querySelector(`.lesson-${courseId}-${lessonId}`).src = "/assets/icons/unlocked.png";
    } else {
        lessonsNotiHeading.style.color = "#9DA7E6"
    }

    setTimeout(() => lessonsNoti.style.display = 'none', 2000);
}

/**
 * display status of each lesson
 * @param {*} courseLessons 
 */
displayStatus(courseLessons);
function displayStatus(courseLessons) {
    courseLessons.forEach(lesson => {
        let lessonStatus = localStorage.getItem(`lesson-${COURSE_ID}-${lesson.id}`);

        if (lessonStatus) {
            document.querySelector(`.lesson-${COURSE_ID}-${lesson.id}`).src = "/assets/icons/unlocked.png";   
        }
    });
}

/**
 * display loading
 */
function displayLoading() {
    let loadingContainer = document.querySelector('.loadingContainer');
    loadingContainer.style.display = 'flex';
    setTimeout(() => loadingContainer.style.display = 'none', 1000);
}

/**
 * redirect to lesson page
 */
function redirectLesson() {
    window.location.href = "/lessson.html";
}

/**
 * notification for entering lesson
 * @param {*} data 
 */
function enteringLesson(data) {
    let enterLessonNoti = document.querySelector('.enterLessonNoti');
    let enterLessonNotiHeading = document.querySelector('.enterLessonNoti__heading');
    let enteringMessage = data.lessonRegisterMessage;

    if (enteringMessage) {
        enterLessonNoti.style.display = 'flex';
        enterLessonNotiHeading.innerText = enteringMessage;
        enterLessonNotiHeading.style.color = "#DC143C";

        setTimeout(() => enterLessonNoti.style.display = 'none', 2000);
    } else {
        let lesson = data[0];

        localStorage.setItem('lesson', JSON.stringify(lesson));
        window.location.href = "/lesson"
    }
}

/**
 * calculating progress of the user
 * @param {*} userLessons 
 * @returns current user's course progress
 */
calculateUserProgress(userLessons);
function calculateUserProgress(userLessons) {
    let currentCourseProgress = 0;
    let courseId = localStorage.getItem('courseId');

    userLessons.forEach(lesson => {
        let lessonCourseId = lesson.courseId;
        if (lessonCourseId == courseId) {
            currentCourseProgress += lesson.progress;
        }
    });

    document.querySelector('.courseInfor__progress__value__percentage')
    .innerText = currentCourseProgress + '%';

    return currentCourseProgress;
}

/**
 * taking the test
 * @param {*} currentCourseProgress 
 */
takingTest(calculateUserProgress(userLessons));
function takingTest(currentCourseProgress) {
    let courseLessonsTestBtn = document.querySelector('.courseLessons__testBtn');

    courseLessonsTestBtn.addEventListener('click', (event) => {
        event.preventDefault();
        let courseId = COURSE_ID.toString();
        let userProgress = currentCourseProgress.toString();
        
        fetch(`/test/${courseId}/${userProgress}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => response.json())
        .then(data => {
            displayLoading();
            setTimeout(() => {retrieveTestData(data)}, 1500);
        })
        .catch(error => console.error('Error:', error));
    });
}

/**
 * getting test's data from response
 * @param {*} data 
 */
function retrieveTestData(data) {
    let testMessage = data.testMessage;

    if (testMessage == '') {
        let test = data.test;
        let currentQuestions = data.currentQuestions;

        localStorage.setItem('test', JSON.stringify(test));
        localStorage.setItem('currentQuestions', JSON.stringify(currentQuestions));
        localStorage.setItem('timeAmount', test[0].time);
        localStorage.setItem('threshold', test[0].threshold);
        localStorage.setItem('testName', test[0].testName);

        window.location.href = '/test';
    } else {
        let enterLessonNoti = document.querySelector('.enterLessonNoti');
        let enterLessonNotiHeading = document.querySelector('.enterLessonNoti__heading');

        enterLessonNoti.style.display = 'flex';
        enterLessonNotiHeading.innerText = testMessage;
        enterLessonNotiHeading.style.color = "#DC143C";

        setTimeout(() => enterLessonNoti.style.display = 'none', 2000);
    }
}

/**
 * change to lock icon for the lesson the user hasn't joined
 * @param {*} userLessons 
 */
changeLockIcon(userLessons);
function changeLockIcon(userLessons) {
    let courseLessonElements = document.querySelectorAll('.courseLessons__lesson');

    courseLessonElements.forEach(lesson => {
        let courseIdLessonId = lesson.id;
        let isCompleted = false;

        userLessons.forEach(userLesson => {
            let courseId = userLesson.courseId;
            let userLessonId = userLesson.lessonId;
            let userCourseIdLessonId = `lesson-${courseId}-${userLessonId}`;

            if (courseIdLessonId == userCourseIdLessonId) {
                isCompleted = true;
            }
        }); 

        if (!isCompleted) {
            localStorage.setItem(`${courseIdLessonId}`, false);   
            document.querySelector(`.${courseIdLessonId}`).src = "/assets/icons/locked.png";
        } else {
            localStorage.setItem(`${courseIdLessonId}`, true);   
            document.querySelector(`.${courseIdLessonId}`).src = "/assets/icons/unlocked.png";  
        }
    });
}

