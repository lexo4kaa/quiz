package by.mmf.krupenko.controller.command;

import by.mmf.krupenko.controller.command.impl.*;

/**
 * The enum for getting Command.
 */
public enum CommandType {

    CREATE_QUIZ {
        {
            this.command = new CreateQuizCommand();
        }
    },
    REMOVE_QUIZ {
        {
            this.command = new RemoveQuizCommand();
        }
    },
    SAVE_ANSWERS {
        {
            this.command = new SaveAnswersCommand();
        }
    },
    FIND_QUIZZES_BY_TEACHER_EMAIL {
        {
            this.command = new FindQuizzesByTeacherEmailCommand();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    TO_LOGIN_PAGE {
        {
            this.command = new ToLoginPageCommand();
        }
    },
    TO_CREATE_QUIZ_PAGE {
        {
            this.command = new ToCreateQuizPageCommand();
        }
    },
    TO_SHOW_RESULTS_PAGE {
        {
            this.command = new ToShowResultsPage();
        }
    },
    TO_STUDENT_PAGE {
        {
            this.command = new ToStudentPageCommand();
        }
    },
    TO_QUIZ_PAGE {
        {
            this.command = new ToQuizPageCommand();
        }
    },
    TO_QUIZZES_PAGE {
        {
            this.command = new ToTeacherQuizzesPageCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
