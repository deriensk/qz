import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.exception.CreationException;
import fr.epita.quiz.services.data.QuestionDAO;

import java.sql.SQLException;
import java.util.List;

public class QuestionDAOTests {

    public static void main(String[] args) throws CreationException, SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        try {
            questionDAO.create(new Question("test question"));
        }catch (CreationException ce){
            System.out.println("Something went wrong during question creation");
        }
        List<Question> search = questionDAO.search(null);

        Question returnedQuestion = search.get(0);
        if (!"test question".equals(returnedQuestion.getQuestion())){
            System.out.println("Error! title mismatch");
            return;

        }
        System.out.println("Successfully returned");
    }
}
