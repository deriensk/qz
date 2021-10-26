package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.exception.CreationException;
import fr.epita.quiz.services.data.QuestionDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) throws SQLException, CreationException {
        //UI
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, input a question title: ");
        String questionTitle = scanner.nextLine();
        System.out.println("Input the question difficulty (0 to 5");
        Integer questionDifficulty = scanner.nextInt();

        //Business Logic
        Question question = new Question(questionTitle);
        //question.setQuestion(questionTitle);
        question.setDifficulty(questionDifficulty);

        //Data access
        QuestionDAO dao = new QuestionDAO();
        dao.create(question);

        List<Question> searchList = dao.search(question);







    }
}
