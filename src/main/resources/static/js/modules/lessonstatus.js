/**
 * save lesson's status 
 * @param {*} userLessons 
 */
savingLessonStatus(userLessons);
function savingLessonStatus(userLessons) {
    userLessons.forEach(lesson => {
        let courseId = lesson.courseId;
        let lessonId = lesson.id;
        
        localStorage.setItem(`lesson-${courseId}-${lessonId}`, true);
    });
}
