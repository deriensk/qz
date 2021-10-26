import java.sql.*;

public class H2ConnectionTests {

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "root", "root");
        String schema = connection.getSchema();
        if (!"PUBLIC".equals(schema)){
            throw new Exception("Connection was not successful");
        }

        System.out.println(schema);

        // Use of the prepared statement: It allows the developer to write the SQL command
        // and the user provided data separately. Or prevent SQL injection 1.16.00
        String createStatement = "CREATE TABLE IF NOT EXISTS QUESTION(ID IDENTITY PRIMARY KEY, TITLE VARCHAR(255), DIFFICULTY INT);";

        PreparedStatement preparedStatement = connection.prepareStatement(createStatement);
        preparedStatement.execute();

        // START OF INSERT STATEMENT
        String insertStatement = "INSERT INTO QUESTION(TITLE, DIFFICULTY) VALUES(?, ?)";

        PreparedStatement insert = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        insert.setString(1, "What is a java doc comment?");
        insert.setInt(2,2);

        insert.execute();
        ResultSet generatedKeys = insert.getGeneratedKeys();
        generatedKeys.next();
        int getGeneratedId = generatedKeys.getInt(1);
        // END OF INSERT STATEMENT

        //test
        String selectStatement = "SELECT ID, TITLE, DIFFICULTY FROM QUESTION";

        PreparedStatement select = connection.prepareStatement(selectStatement);
        ResultSet resultSet = select.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            int difficulty = resultSet.getInt("DIFFICULTY");
            String title = resultSet.getString("TITLE");
            System.out.println(id + " " + difficulty + " " + title);
        }

    }
}
