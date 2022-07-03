package by.mmf.krupenko.model.service.impl;

import by.mmf.krupenko.entity.Answer;
import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.entity.Quiz;
import by.mmf.krupenko.model.dao.AnswerDao;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.QuestionDao;
import by.mmf.krupenko.model.dao.QuizDao;
import by.mmf.krupenko.model.dao.impl.AnswerDaoImpl;
import by.mmf.krupenko.model.dao.impl.QuestionDaoImpl;
import by.mmf.krupenko.model.dao.impl.QuizDaoImpl;
import by.mmf.krupenko.model.service.QuizService;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.view.QuizView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizServiceImpl implements QuizService {
    private static Logger logger = LogManager.getLogger();
    private final QuizDao quizDao;
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    public QuizServiceImpl() {
        quizDao = QuizDaoImpl.getInstance();
        questionDao = QuestionDaoImpl.getInstance();
        answerDao = AnswerDaoImpl.getInstance();
    }

    @Override
    public Quiz findQuizById(String quizId) throws ServiceException {
        Quiz quiz;
        try {
            quiz = quizDao.findQuizByQuizId(quizId);
        } catch (DaoException e) {
            logger.error("quizDao.findQuizById(" + quizId + ") is failed in QuizServiceImpl", e);
            throw new ServiceException(e);
        }
        return quiz;
    }

    @Override
    public List<QuizView> findQuizzesByTeacherEmail(String teacherEmail) throws ServiceException {
        List<QuizView> quizViews = new ArrayList<>();
        try {
            List<Quiz> quizzes = quizDao.findQuizzesByTeacherEmail(teacherEmail);
            for (Quiz quiz : quizzes) {
                QuizView quizView = new QuizView();
                quizView.setQuiz(quiz);
                quizView.setCountOfQuestions(questionDao.findQuestionsByQuizId(quiz.getId()).size());
                quizViews.add(quizView);
            }
        } catch (DaoException e) {
            logger.error("quizDao.findQuizzesByTeacherEmail(" + teacherEmail + ") is failed in QuizServiceImpl", e);
            throw new ServiceException(e);
        }
        return quizViews;
    }

    @Override
    public String createQuiz(String quizName, String teacherEmail, Map<Question, List<String>> values) throws ServiceException {
        try {
            quizDao.createQuiz(quizName, teacherEmail);
            String quizId = quizDao.findQuizByQuizNameAndTeacherEmail(quizName, teacherEmail).getId();
            for (Map.Entry<Question, List<String>> entry : values.entrySet()) {
                Question question = entry.getKey();
                questionDao.createQuestion(question, quizId);
                int questionId = questionDao.findQuestionId(question.getTitle(), quizId);
                for (String answer : entry.getValue()) {
                    answerDao.createAnswer(answer, questionId);
                }
            }
            return quizId;
        } catch (DaoException e) {
            logger.error("createQuiz is failed in QuizServiceImpl", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeQuiz(String quizId) throws ServiceException {
        try {
            quizDao.removeQuiz(quizId);
        } catch (DaoException e) {
            logger.error("remove quiz is failed in QuizServiceImpl", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Question, List<Answer>> findQuizInformation(String quizId) throws ServiceException {
        Map<Question, List<Answer>> map = new HashMap<>();
        try {
            List<Question> questions = questionDao.findQuestionsByQuizId(quizId);
            for (Question question : questions) {
                map.put(question, answerDao.findAnswersByQuestionId(question.getId()));
            }
        } catch (DaoException e) {
            logger.error("findQuizInformation(" + quizId + ") is failed in QuizServiceImpl", e);
            throw new ServiceException(e);
        }
        return map;
    }
}
