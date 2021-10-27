import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "");

            //addUser(new User(4, "admin", "admin"), connection);

            List<User> users = getAllUsers(connection);
            System.out.println(users);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addUser(User user, Connection connection) {
        try {
            String sql = "INSERT INTO tuser (login, pass) VALUES ('" + user.getLogin() + "', '" + user.getPass() + "');";

            System.out.println(sql);

            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<User> getAllUsers(Connection connection) {
        List<User> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tuser";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPass(rs.getString("pass"));
                result.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public static void iteratory() {
        List<String> list = new ArrayList<>();

        list.add("Asdfasdf");
        list.add("sadfgasdfg");

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
    }

    public static void addUser2(User user, Connection connection) {
        try {
            String sql = "INSERT INTO tuser (login, pass) VALUES (?,?);";

            System.out.println(sql);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            statement.executeUpdate();

            statement.clearParameters();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<User> getAllUsers2(Connection connection) {
        List<User> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tuser WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 5);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPass(rs.getString("pass"));
                result.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
