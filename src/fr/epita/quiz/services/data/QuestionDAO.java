package fr.epita.quiz.services.data;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.exception.CreationException;
import fr.epita.quiz.services.conf.Configuration;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    private Connection getConnection() throws SQLException {

//      Configuration conf = new Configuration();
        Configuration conf = Configuration.getInstance();

        Connection connection = DriverManager.getConnection(conf.getInstance().getConfValue("db.url"),
                conf.getConfValue("db.user"),
                conf.getConfValue("db.password"));
        String schema = connection.getSchema();
        if (!"PUBLIC".equals(schema)){
            throw new RuntimeException("Connection was not successful");
        }

        String createStatement = "CREATE TABLE IF NOT EXISTS QUESTION(ID IDENTITY PRIMARY KEY, TITLE VARCHAR(255), DIFFICULTY INT);";

        PreparedStatement preparedStatement = connection.prepareStatement(createStatement);
        preparedStatement.execute();

        return connection;


    }

    public void create(Question newQuestion) throws CreationException {
        try (Connection connection = getConnection()){


            String insertStatement = "INSERT INTO QUESTION(TITLE, DIFFICULTY) VALUES(?, ?))";

            PreparedStatement insert = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
            insert.setString(2, newQuestion.getQuestion());
            insert.setInt(2, newQuestion.getDifficulty());
            insert.execute();
        } catch (SQLException sqle){
            CreationException creationException = new CreationException();
            creationException.initCause(sqle);
            throw creationException;
        }
    }

    public List<Question> search(Question questionCriteria) throws SQLException{
        String selectStatement = "SELECT ID, TITLE, DIFFICULTY FROM QUESTION";

        Connection connection = getConnection();
        PreparedStatement select = connection.prepareStatement(selectStatement);
        ResultSet resultSet = select.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            int difficulty = resultSet.getInt("DIFFICULTY");
            String title = resultSet.getString("TITLE");
//          System.out.println(id + " " + difficulty + " " + title);
            Question question = new Question(title);
            question.setId(id);
            question.setDifficulty(difficulty);
            questions.add(question);
        }
        connection.close();
        return questions;

    }

    public void retrieve(Question questions){

    }

    public void update(Question updateQuestion){

    }
    public void delete(Question deleteQuestion){
        int id = Integer.parseInt(deleteQuestion.getQuestion());

    }

}
