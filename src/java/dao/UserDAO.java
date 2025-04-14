package dao;

import java.sql.*;
import model.User;

public class UserDAO {

    public boolean registerUser(User user) {
        boolean isRegistered = false;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Client", "nbuser", "nbuser");

            String sql = "INSERT INTO \"USER\" (\"name\",\"username\", \"birth\", \"email\", \"mobileNo\", \"password\") VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getBirth().toString()); // Converting LocalDate to String
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getMobileNo());
            pst.setString(6, user.getPassword());

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isRegistered = true;
            }

            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isRegistered;
    }
}
