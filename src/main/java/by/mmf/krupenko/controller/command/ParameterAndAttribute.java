package by.mmf.krupenko.controller.command;

/**
 *  Describes all parameters and attributes
 */
public final class ParameterAndAttribute {
    //  parameters
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String TEACHER_EMAIL = "teacherEmail";
    public static final String QUIZ_ID = "quizId";
    public static final String QUIZ_NAME = "quizName";
    public static final String CREATE_QUIZ_AGENT = "createQuizAgent";
    public static final String SAVE_ANSWERS_AGENT = "saveAnswersAgent";

    // attributes
    public static final String CURRENT_PAGE = "current_page";
    public static final String CURRENT_TEACHER_EMAIL = "currentTeacherEmail";
    public static final String CURRENT_QUIZ = "currentQuiz";
    public static final String CURRENT_QUIZ_INFORMATION = "currentQuizInfo";
    public static final String CURRENT_QUIZ_RESULTS = "currentQuizResults";
    public static final String SHOWN_QUIZ_ID = "shownQuizId";
    public static final String QUIZZES = "quizzes";

    // messages
    public static final String ERROR_LOGIN_PASS_MESSAGE = "errorLoginPassMessage";
    public static final String WRONG_ACTION_MESSAGE = "wrongAction";

    private ParameterAndAttribute() {}
}
