function getValues() {
    let TOTAL = {};
    let questions = document.querySelectorAll("[id='question']");
    for(let i = 0; i < questions.length; i++) {
        let questionObj = {};
        let question = questions[i];
        questionObj['type'] = question.querySelector("select").value;
        questionObj['isRequired'] = question.querySelector("[id='isRequired']").checked;
        questionObj['title'] = question.querySelector("[id='title']").value;

        let answersArray = [];
        let answers = question.querySelectorAll("[id='answer']");
        answers.forEach(element => answersArray.push(element.value));

        TOTAL[JSON.stringify(questionObj)] = answersArray;
    }
    document.getElementById("createQuizAgent").value = JSON.stringify(TOTAL);
}