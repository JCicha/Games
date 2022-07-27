import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoDBImpl{
    private static UserDaoDBImpl instance;

    public static UserDaoDBImpl getInstance(){
        if (instance == null) {
            instance = new UserDaoDBImpl();
        }

        return instance;
    }

    private UserDaoDBImpl() {
    }

    @Override
    public Word getWord(String passwordToGet) {
        String selectSQL = "SELECT * FROM passwords WHERE password = ?";

        try (Connection dbConnection = DbConnection.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, passwordToGet);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong("id");
                String password = rs.getString("password");
                return new Word(id, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean createWordToGuess(String password) {
        String selectSQL = "INSERT INTO passwords (password) VALUE (?)";

        try (Connection dbConnection = DbConnection.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, password);
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

//    @Override
//    public boolean activateUser(String token) {
//
//        String selectSQL = "UPDATE users SET active = true WHERE token = ? AND active = false";
//
//        try (Connection dbConnection = DbConnection.getDBConnection();
//             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {
//
//            preparedStatement.setString(1, token);
//
//            return preparedStatement.executeUpdate() == 1;
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return false;
//    }
}