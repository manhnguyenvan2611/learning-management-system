let currentLessonIdx = localStorage.getItem('currentLessonIdx');
let courseLessons = JSON.parse(localStorage.getItem('courseLessons'));
let lessonUnits = courseLessons.length;

if (!currentLessonIdx) {
    currentLessonIdx = 0;
}

/**
 * display lesson's content
 * @param {*} currentLessonIdx 
 * @param {*} lessonUnits 
 */
displayLessonContent(currentLessonIdx, lessonUnits);
function displayLessonContent(currentLessonIdx, lessonUnits) {
    let lessonContentDisplay = document.querySelector('.lessonContent__display');
    let lessonContentHeading = document.querySelector('.lessonContent__heading');
    let currentLesson = courseLessons[currentLessonIdx];
    let file = currentLesson.file;
    let lessonName = currentLesson.lessonName;

    lessonContentDisplay.src = file;
    lessonContentHeading.innerText = lessonName;

    if (currentLessonIdx == (lessonUnits - 1)) {
        document.getElementById('right-button').innerText = 'Complete';
    } else {
        let arrowIcon = document.createElement('img');
        arrowIcon.src = "/assets/icons/arrow-right.png";
        document.getElementById('right-button').appendChild = 'Next lesson ' + arrowIcon;
    }
}

/**
 * navigate between lessons
 */
navigateLesson();
function navigateLesson() {
    let navigationBtns = document.querySelectorAll('.navigationBtns__btn');
    let userToLessonId = localStorage.getItem('userToLessonId');

    navigationBtns.forEach(btn => {
        btn.onclick = function() {
            let btnType = btn.id;
            let lesson = courseLessons[currentLessonIdx];
            let currentLessonProgress = lesson.progress;
            let currentLessonId = lesson.id;
            let currentCourseId = lesson.courseId;

            if (btnType == "right-button") {
                if (currentLessonIdx < lessonUnits - 1) {
                    currentLessonIdx = parseInt(currentLessonIdx, 10) + 1;
                }

                let nextLesson = courseLessons[currentLessonIdx];
                let courseId = nextLesson.courseId;
                let nextLessonId = nextLesson.id;

                saveUserLesson(currentLessonId, currentLessonProgress, 
                    nextLessonId, courseId);
                saveLessonStatus(courseId, nextLessonId)
            } else if (btnType == "left-button" && currentLessonIdx > 0) {
                currentLessonIdx = parseInt(currentLessonIdx, 10) - 1;
            }

            displayLessonContent(currentLessonIdx);
            localStorage.setItem('currentLessonIdx', currentLessonIdx);
            location.reload();
        }
    });
}

/**
 * if a lesson has done studied
 * we save its status to localstorage for changing lock icon 
 * @param {*} courseId 
 * @param {*} lessonId 
 */
function saveLessonStatus(courseId, lessonId) {
    localStorage.setItem(`lesson-${courseId}-${lessonId}`, true);
}

/**
 * saving lessons the user has learned to database
 * @param {*} currentLessonId 
 * @param {*} currentLessonProgress 
 * @param {*} nextLessonId 
 * @param {*} courseId 
 */
function saveUserLesson(currentLessonId, 
    currentLessonProgress, nextLessonId, courseId) {
    currentLessonId = currentLessonId.toString();
    currentLessonProgress = currentLessonProgress.toString();
    nextLessonId = nextLessonId.toString();
    courseId = courseId.toString();
    
    fetch(`/save/userlesson`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            currentLessonId: currentLessonId,
            currentLessonProgress: currentLessonProgress,
            nextLessonId: nextLessonId,
            courseId: courseId
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
    })
    .catch(error => console.error('Error:', error));
}

