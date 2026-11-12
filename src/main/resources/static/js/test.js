var test = JSON.parse(localStorage.getItem('test'));
var currentQuestions = JSON.parse(localStorage.getItem('currentQuestions'));
var timeAmount = localStorage.getItem('timeAmount');
var threshold = localStorage.getItem('threshold');
var testName = localStorage.getItem('testName');
var COURSE_ID = localStorage.getItem('courseId');

let currentQuestionId = 1;
let chosenAnswers = [];
let numberOfQuestions = currentQuestions.length;

/**
 * generating html for questions
 */
let testNameHeading = document.querySelector('.testName__heading');
let testQuestion = document.querySelector('.testQuestion');
testNameHeading.innerText = 'Course ' + testName;

/**
 * update html of a question
 */
updateQuestion(currentQuestionId);
function updateQuestion(currentQuestionId) {
    let currentQuestion = currentQuestions[currentQuestionId - 1];

    testQuestion.innerHTML = `
        <h2 class="testQuestion__heading">Question ${currentQuestionId}</h2>

        <p class="testQuestion__para">
            ${currentQuestion.questionContent}
        </p>

        <div class="testQuestion__answer">
            <div class="testQuestion__answer__container">
                <input class="icon-medium radio__input question-${COURSE_ID}-${currentQuestionId}-0" id="${currentQuestion.id}" type="radio" value="${currentQuestion.answerA}" name="question-${COURSE_ID}-${currentQuestionId}"/>
                <label class="radio__label">A. ${currentQuestion.answerA}</label>
            </div>
            <div class="testQuestion__answer__container">
                <input class="icon-medium radio__input question-${COURSE_ID}-${currentQuestionId}-1" id="${currentQuestion.id}" type="radio" value="${currentQuestion.answerB}" name="question-${COURSE_ID}-${currentQuestionId}"/>
                <label class="radio__label">B. ${currentQuestion.answerB}</label>
            </div>
            <div class="testQuestion__answer__container">
                <input class="icon-medium radio__input question-${COURSE_ID}-${currentQuestionId}-2" id="${currentQuestion.id}" type="radio" value="${currentQuestion.answerC}" name="question-${COURSE_ID}-${currentQuestionId}"/>
                <label class="radio__label">C. ${currentQuestion.answerC}</label>
            </div>
            <div class="testQuestion__answer__container">
                <input class="icon-medium radio__input question-${COURSE_ID}-${currentQuestionId}-3" id="${currentQuestion.id}" type="radio" value="${currentQuestion.answerD}" name="question-${COURSE_ID}-${currentQuestionId}"/>
                <label class="radio__label">D. ${currentQuestion.answerD}</label>
            </div>
        </div>
                `;
}

// displaying answer board
let answerContainerAnswers = document.querySelector('.answerContainer__answers');
let answersHTML = '';

currentQuestions.forEach((question, index) => {
    answersHTML += `<button id="${question.id}" class="answerContainer__answers__answer">${index + 1}</button>`;
});

answerContainerAnswers.innerHTML = answersHTML;

/**
 * events when the user click on an a button on answerboard
 * 1. update html: updateQuestion(currentQuestionId)
 * 2. update radio input DOM for selecting an answer: selectAnswer(chosenAnswers, currentQuestionId)
 * 3. showing answer's status: it is checked or not: checkSelectedAnswer(chosenAnswers)
 * @param {*} currentQuestionId 
 */
let answerContainerAnswersAnswers = document.querySelectorAll('.answerContainer__answers__answer');

choosingAnswerOnAnswerBoard(currentQuestionId);
function choosingAnswerOnAnswerBoard(currentQuestionId) {
    answerContainerAnswersAnswers.forEach((btn, index) => {
        btn.onclick = function() {
            let questionId = btn.id;
            currentQuestionId = (index + 1);

            navigateQuestion(currentQuestionId);
            updateQuestion(currentQuestionId);
            selectAnswer(chosenAnswers, currentQuestionId);
            checkSelectedAnswer(chosenAnswers);
        }
    });
}

/**
 * update radio button DOM
 */
let radioInputs = document.querySelectorAll('.radio__input');

function updateRadioInput() {
    radioInputs = document.querySelectorAll('.radio__input');
}

/**
 * select answer radio button
 * @param {*} chosenAnswers 
 * @param {*} currentQuestionId 
 */
selectAnswer(chosenAnswers, currentQuestionId);
function selectAnswer(chosenAnswers, currentQuestionId) {
    updateRadioInput();

    radioInputs.forEach((btn, index) => {
        btn.onclick = function() {
            let answerVal = btn.value;
            let answerId = btn.id;
            let chosenAnswersLen = chosenAnswers.length;
            let chosenAnswersTemp = [];
            let isAdded = true;
            let questionId = currentQuestions[currentQuestionId - 1].id;
     
            if (chosenAnswersLen == 0) {
                chosenAnswers.push({
                    answerId: answerId,
                    answerValue: answerVal,
                });
            } else {
                for (let i = 0; i < chosenAnswersLen; i++) {
                    if (questionId == chosenAnswers[i].answerId && 
                        answerVal == chosenAnswers[i].answerValue) {
                        break;
                    } else if (questionId == chosenAnswers[i].answerId &&
                        answerVal != chosenAnswers[i].answerValue) {
                        chosenAnswersTemp = chosenAnswers.filter(answer => answer.answerId != questionId);

                        chosenAnswersTemp.push({
                            answerId: answerId,
                            answerValue: answerVal,
                        });

                        chosenAnswers = chosenAnswersTemp;

                        break;
                    } else {
                        if (i == chosenAnswersLen - 1) {
                            isAdded = false;
                        }
                    }
                }

                if (!isAdded) {
                    chosenAnswers.push({
                        answerId: answerId,
                        answerValue: answerVal,
                    });
                }
            }

            checkSelectedAnswer(chosenAnswers);
            updateAnswerBoard(chosenAnswers);
        }
    });
}

/**
 * show selected answers
 */
checkSelectedAnswer(chosenAnswers);
function checkSelectedAnswer(chosenAnswers) {
    updateRadioInput();

    radioInputs.forEach(radio => {
        let radioValue = radio.value;
        let radioId = radio.id;

        chosenAnswers.forEach(answer => {
            let answerId = answer.answerId;
            let answerValue = answer.answerValue;

            if (answerValue == radioValue && answerId == radioId) {
                radio.checked = true;
            }
        });
    });
}

/**
 * change button's color on answer board
 */
updateAnswerBoard(chosenAnswers);
function updateAnswerBoard(chosenAnswers) {
    answerContainerAnswersAnswers.forEach(btn => {
        let questionId = btn.id;

        chosenAnswers.forEach(answer => {
            let answerId = answer.answerId;

            if (questionId == answerId) {
                btn.style.background = '#9DA7E6';
                btn.style.color = 'white';
            }
        });
    });
}

/**
 * click next, previous button 
 */
navigateQuestion(currentQuestionId);
function navigateQuestion(currentQuestionId) {
    let submitBarNavigationsBtns = document.querySelectorAll('.submitBar__navigations__btn');

    submitBarNavigationsBtns.forEach(btn => {
        btn.onclick = function() {
            let btnType = btn.id
            let currentQuestionsLen = currentQuestions.length;
            
            if (btnType == "previous" && currentQuestionId > 1) {
                currentQuestionId = parseInt(currentQuestionId, 10) - 1;
                updateQuestion(currentQuestionId);
            } else if (btnType == "next" && currentQuestionId < currentQuestionsLen) {
                currentQuestionId = parseInt(currentQuestionId, 10) + 1;
                updateQuestion(currentQuestionId);
            }

            updateRadioInput();
            selectAnswer(chosenAnswers, currentQuestionId);
            checkSelectedAnswer(chosenAnswers);
            updateAnswerBoard(chosenAnswers);
        }
    });
}

/**
 * open answer board
 */
let displayAnswerBtn = document.querySelector('.displayAnswerBtn');
let answerBoard = document.querySelector('.answerBoard');
let submitBar = document.querySelector('.submitBar');

if (body.clientWidth <= 768) {
    displayAnswerBtn.onclick = function() {
        answerBoard.style.display = 'flex';
    }

    document.addEventListener('click', (event) => {
        if (!displayAnswerBtn.contains(event.target) && !answerBoard.contains(event.target)) {
            answerBoard.style.display = 'none';
        }
    });
}

/**
 * time count
 */
let scoreDialog = document.querySelector('.scoreDialog');
let scoreDialogBtn = document.querySelector('.scoreDialog__btn');
let scoreDialogResult = document.querySelector('.scoreDialog__result');
let scoreDialogStatus = document.querySelector('.scoreDialog__status');

let seconds = 0;
let timeCountTime = document.querySelector('.timeCount__time');
let timeInterval = setInterval(timeUpdate, 1000);

timeCountTime.textContent = (timeAmount < 10? '0' + timeAmount: timeAmount) + ":" + 
(seconds < 10? '0' + seconds: seconds);

function timeUpdate() {
    if (timeAmount <= 0 && seconds <= 1) {
        sendingChosenAnswersToServer(COURSE_ID, chosenAnswers);   
        finishTest(score, testStatus, threshold);
    } 

    if (seconds == 0) {
        seconds = 59;
        timeAmount -= 1;
    } else {
        seconds -= 1;
    }

    timeCountTime.textContent = (timeAmount < 10? '0' + timeAmount: timeAmount) + ":" + 
    (seconds < 10? '0' + seconds: seconds);
}

/**
 * submit the test
 */
let score = 0;
let testStatus = "Failed";

/**
 * sending chosen answers to the server to compute score: sendingChosenAnswersToServer(courseId, chosenAnswers)
 * @param {*} courseId 
 */
submitTest(COURSE_ID);
function submitTest(courseId) {
    let submitBtn = document.querySelector('.submitBtn');

    submitBtn.onclick = function() {
        sendingChosenAnswersToServer(courseId, chosenAnswers);   
    }
}

/**
 * checking user's result
 * @param {*} courseId 
 * @param {*} score 
 */
function checkResult(courseId, score) {
    if (score >= threshold) {
        testStatus = "Passed";
    }

    finishTest(score, testStatus, threshold);
    sendingTestToServer(courseId, score);   
}

/**
 * 1. retrieving user's score from the server
 * 2. checking if the user's score is higher than minimum requirement. If it is then set testStatus to passed otherwise to failed
 * 3. showing user's result to the browser
 * 4. sending back user's score to the server to save into database
 * @param {*} courseId 
 * @param {*} chosenAnswers 
 */
function sendingChosenAnswersToServer(courseId, chosenAnswers) {
    fetch(`/computescore`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            chosenAnswers: chosenAnswers,
        })
    })
    .then(response => response.json())
    .then(data => checkResult(courseId, data))
    .catch(error => console.error('Error:', error));
}

/**
 * showing user's result to the browser
 * @param {*} score 
 * @param {*} testStatus 
 * @param {*} threshold 
 */
function finishTest(score, testStatus, threshold) {
    clearInterval(timeInterval);
    scoreDialog.style.display = 'flex';
    testQuestion.style.pointerEvents = 'none';
    submitBar.style.pointerEvents = 'none';

    scoreDialogResult.textContent = `${score}/${numberOfQuestions}`;
    scoreDialogStatus.textContent = `${testStatus}`;

    if (score < threshold) {
        scoreDialogStatus.style.color = 'red';
    }
}

/**
 * sending back user's score to the server to save into database
 * @param {*} courseId 
 * @param {*} userScore 
 */
function sendingTestToServer(courseId, userScore) {
    fetch(`/submittest`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            courseId: courseId.toString(),
            userScore: userScore
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
    })
    .catch(error => console.error('Error:', error));
}

/**
 * closing score dialog 
 * (the popup appear after submiting the test)
 */
scoreDialogBtn.onclick = function() {
    scoreDialog.style.display = 'none';
}

/**
 * redirect to home page when the user reload the test
 */
redirectHomeOnTestReload();
function redirectHomeOnTestReload() {
    if (window.performance.navigation.type === window.performance.navigation.TYPE_RELOAD) {
        window.addEventListener('load', () => window.location.href='/');
    }
}