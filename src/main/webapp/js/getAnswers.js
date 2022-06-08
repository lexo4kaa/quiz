function getAnswers() {
    let TOTAL = {};
    let questions = document.querySelectorAll("[id^='question_']");
    for(let i = 0; i < questions.length; i++) {
        let question = questions[i];
        let questionIdDB = question.id.split("_")[1];

        let answers;
        if (question.querySelectorAll('input[type="checkbox"]').length !== 0) {
            answers = question.querySelectorAll('input[type="checkbox"]:checked');
        } else if (question.querySelectorAll('input[type="radio"]').length !== 0) {
            answers = question.querySelectorAll('input[type="radio"]:checked');
        } else if (question.querySelectorAll('textarea').length !== 0) {
            answers = question.querySelectorAll('textarea');
        }

        let answersArray = [];
        answers.forEach(element => answersArray.push(element.value));

        TOTAL[questionIdDB] = answersArray;
    }
    document.getElementById("saveAnswersAgent").value = JSON.stringify(TOTAL);
}