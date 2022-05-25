function addQuestionFunc (input) {
    let div = document.createElement("div");
    div.id = "question";

    let question = document.createElement("input");
    question.type = "text";
    question.id = "title";

    let answersDiv = document.createElement("div");
    answersDiv.id = "answers";

    let textField = document.createElement("div");
    textField.innerText = "A short answer";
    textField.style.paddingTop = "6px";

    answersDiv.append(textField);

    let select = document.createElement("select");
    addOption(select, "text", "Text");
    addOption(select, "one", "Single");
    addOption(select, "multiple", "Multiple");
    addOption(select, "scale", "Scale");

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
        input.style.paddingTop = "6px";
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
            case "scale":
                alert("+");
                break;
        }
    }

    let answers = select.parentNode.querySelector("#answers");
    answers.innerHTML = "";
    answers.append(input);
}

function addMultiAnswerFunc(input) {
    let div = document.createElement("div");

    let answer = document.createElement("input");
    answer.type = "text";
    answer.id = "answer";

    let removeAnswer = document.createElement("input");
    removeAnswer.type = "button";
    removeAnswer.value = "-";
    removeAnswer.onclick = function () {
        this.parentNode.remove();
    };

    let addAnswer = document.createElement("input");
    addAnswer.type = "button";
    addAnswer.value = "+";
    addAnswer.onclick = function () {
        addMultiAnswerFunc(addAnswer.parentNode);
    };

    div.append(answer);
    div.append(removeAnswer);
    div.append(addAnswer);

    input.insertAdjacentElement('afterend', div);
}

window.onload = function () {
    addQuestionFunc(document.querySelector("#content"));
}