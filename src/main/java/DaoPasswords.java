import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoPasswords {
    public static String getPasswordsFromTheDb() {
        StringBuilder content = new StringBuilder();
        String selectSQL = "SELECT password FROM hangmandb.passwords";

        try (Connection dbConnection = DbConnection.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String pwd = rs.getString("password");
                content.append(pwd);
                content.append(System.lineSeparator());
            }
            return content.toString();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static void addingPasswordToDb(String password) {
        String selectSQL = "INSERT INTO passwords (password) VALUE (?)";

        try (Connection dbConnection = DbConnection.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, password);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}