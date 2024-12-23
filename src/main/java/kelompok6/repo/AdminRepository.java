package kelompok6.repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kelompok6.lib.koneksii;
import kelompok6.model.AdminModel;

public class AdminRepository {
    private static final String TABLE_NAME = "Admin";

    public boolean create(AdminModel admin) {
        String query = "INSERT INTO " + TABLE_NAME + " (username, password) VALUES (?, ?)";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AdminModel> readAll() {
        List<AdminModel> admins = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = koneksii.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                AdminModel admin = new AdminModel();
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public AdminModel login(String username, String password) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AdminModel(
                        rs.getString("username"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
