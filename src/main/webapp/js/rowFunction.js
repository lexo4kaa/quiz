function addQuestionFunc (input) {
    let div = document.createElement("div");
    div.id = "question";

    let question = document.createElement("textarea");
    question.id = "title";
    question.maxLength = "200";
    question.rows = "3";
    question.style.width = "100%";
    question.style.marginTop = "10px";
    question.style.marginBottom = "10px";
    question.style.fontSize = "large";
    question.placeholder = "Enter your question here";
    question.required = true;

    let answersDiv = document.createElement("div");
    answersDiv.id = "answers";

    let textField = document.createElement("div");
    textField.innerText = "A short answer";
    textField.style.paddingTop = "5px";

    answersDiv.append(textField);

    let select = document.createElement("select");
    addOption(select, "text", "Text");
    addOption(select, "one", "Single");
    addOption(select, "multiple", "Multiple");

    select.onchange = function () {
        switchQuestionType(select);
    };

    let removeQuestion = document.createElement("input");
    removeQuestion.type = "button";
    removeQuestion.value = "remove question";
    removeQuestion.onclick = function () {
        this.parentNode.remove();
    };

    let addQuestion = document.createElement("input");
    addQuestion.type = "button";
    addQuestion.value = "add question";
    addQuestion.onclick = function () {
        addQuestionFunc(this.parentNode);
    };

    div.style.background = "rgb(200,200,250,0.97)";
    div.style.borderRadius = "5px 10px";
    div.style.padding = "10px";
    div.style.margin = "5px";

    div.append(select);
    div.append(question);
    div.append(removeQuestion);
    div.append(addQuestion);
    div.append(getBr());
    div.insertAdjacentElement('beforeend', answersDiv);

    input.insertAdjacentElement('afterend', div);
}

function getBr() {
    return document.createElement("br");
}

function addOption(select, value, text) {
    let option = document.createElement("option");
    option.value = value;
    option.text = text;
    select.add(option)
}

function switchQuestionType(select) {
    let input;
    if (select.value === "text") {
        input = document.createElement("div");
        input.innerText = "A short answer";
        input.style.paddingTop = "5px";
    } else {
        input = document.createElement("input");
        input.id = "answer";
        addMultiAnswerFunc(input);

        switch (select.value) {
            case "one":
            case "multiple":
                input.id = "button";
                input.type = "button";
                input.value= "Add answer to the start";
                input.onclick = function () {
                    addMultiAnswerFunc(this);
                };
                break;
        }
    }

    let answers = select.parentNode.querySelector("#answers");
    answers.innerHTML = "";
    answers.append(input);
}

function addMultiAnswerFunc(input) {
    let div = document.createElement("div");
    div.style.display = "flex";
    div.style.alignItems = "center";

    let answer = document.createElement("textarea");
    answer.id = "answer";
    answer.maxLength = "200";
    answer.rows = "3";
    answer.style.width = "100%";
    answer.style.marginTop = "10px";
    answer.style.marginBottom = "10px";
    answer.style.fontSize = "large";
    answer.placeholder = "Enter answer";
    answer.required = true;

    let removeAnswer = document.createElement("input");
    removeAnswer.type = "button";
    removeAnswer.value = "-";
    removeAnswer.onclick = function () {
        this.parentNode.remove();
    };
    removeAnswer.style.height = "25px";
    removeAnswer.style.widht = "25px";
    removeAnswer.style.marginLeft = "2%";

    let addAnswer = document.createElement("input");
    addAnswer.type = "button";
    addAnswer.value = "+";
    addAnswer.onclick = function () {
        addMultiAnswerFunc(addAnswer.parentNode);
    };
    addAnswer.style.height = "25px";
    addAnswer.style.widht = "25px";
    addAnswer.style.marginLeft = "2%";

    div.append(answer);
    div.append(removeAnswer);
    div.append(addAnswer);

    input.insertAdjacentElement('afterend', div);
}

window.onload = function () {
    addQuestionFunc(document.querySelector("#content"));
}