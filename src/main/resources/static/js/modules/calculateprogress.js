/**
 * display user progress 
 * @param {*} courses 
 */
calculateProgress(courses);
function calculateProgress(courses) {
    let userProgress = [];

    courses.forEach((course, index) => {
        let courseId = course.id;
        let currentCourseProgress = 0;

        userLessons.forEach(lesson => {
            let lessonCourseId = lesson.courseId;

            if (courseId == lessonCourseId) {
                currentCourseProgress += parseInt(lesson.progress);
            }
        });

        userProgress[index] = currentCourseProgress;
        document.querySelector(`.progress-course-${courseId}`).value = userProgress[index];
        document.querySelector(`.progress-value-${courseId}`).innerText = userProgress[index] + '%';
    });
}