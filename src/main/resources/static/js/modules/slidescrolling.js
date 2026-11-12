/**
 * scrolling slide
 */
scrollSlide();
function scrollSlide() {
    let slideCourseContainer = document.querySelector('.courseSection__slideContainer__courses');
    let slideCourse = document.querySelector('.courseSection__slideContainer__course');
    let coursesBtns = document.querySelectorAll('.coursesBtn');

    coursesBtns.forEach(btn => {
        let body = document.querySelector('body');
        let bodyWidth = body.clientWidth;
        
        btn.onclick = function() {
            let btnType = btn.id;
            let scrollWidth = slideCourse.clientWidth;
            let scrollAmount = btnType == "courseBtn-left"? scrollWidth * (-1): scrollWidth;

            if (bodyWidth > 480) {
                scrollAmount = btnType == "courseBtn-left"? (scrollWidth + 20) * (-1): scrollWidth + 20;
            }

            slideCourseContainer.scrollBy({left: scrollAmount, behavior: "smooth"});
        }
    });
}