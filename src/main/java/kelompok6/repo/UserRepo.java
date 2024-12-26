package kelompok6.repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kelompok6.lib.koneksii;
import kelompok6.model.UserModel;

public class UserRepo {
    private static final String TABLE_NAME = "user";
    private static final Logger LOGGER = Logger.getLogger(UserRepo.class.getName());

    public boolean create(UserModel user) {
        String query = "INSERT INTO " + TABLE_NAME
                + " (id, nama, username, password, email, alamat) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            String uniqueId = generateUniqueId(connection);
            stmt.setString(1, uniqueId);
            stmt.setString(2, user.getNama());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getAlamat());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating user", e);
            return false;
        }
    }

    private String generateUniqueId(Connection connection) throws SQLException {
        String uniqueId;
        boolean isUnique;
        do {
            uniqueId = java.util.UUID.randomUUID().toString();
            String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, uniqueId);
                try (ResultSet rs = stmt.executeQuery()) {
                    rs.next();
                    isUnique = rs.getInt(1) == 0;
                }
            }
        } while (!isUnique);
        return uniqueId;
    }

    public List<UserModel> readAll() {
        List<UserModel> users = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = koneksii.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getString("id"));
                user.setNama(rs.getString("nama"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAlamat(rs.getString("alamat"));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading all users", e);
        }
        return users;
    }

    public UserModel login(String username, String password) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserModel(
                            rs.getString("id"),
                            rs.getString("nama"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("alamat"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error logging in user", e);
        }
        return null;
    }

    public boolean update(UserModel user) {
        String query = "UPDATE " + TABLE_NAME + " SET nama = ?, password = ?, email = ?, alamat = ? WHERE id = ?";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getNama());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getAlamat());
            stmt.setString(5, user.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user", e);
            return false;
        }
    }

    public boolean updateByUsername(UserModel user) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE " + TABLE_NAME + " SET ");
        List<Object> parameters = new ArrayList<>();

        if (user.getNama() != null) {
            queryBuilder.append("nama = ?, ");
            parameters.add(user.getNama());
        }
        if (user.getPassword() != null) {
            queryBuilder.append("password = ?, ");
            parameters.add(user.getPassword());
        }
        if (user.getAlamat() != null) {
            queryBuilder.append("alamat = ?, ");
            parameters.add(user.getAlamat());
        }
        if (user.getEmail() != null) {
            queryBuilder.append("email = ?, ");
            parameters.add(user.getEmail());
        }

        // Remove the last comma and space
        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE nama = ?");
        parameters.add(user.getNama());

        String query = queryBuilder.toString();

        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user by username", e);
            return false;
        }
    }

    public boolean updateById(String id, String username, String password, String alamat, String email) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE " + TABLE_NAME + " SET ");
        List<Object> parameters = new ArrayList<>();

        if (username != null) {
            queryBuilder.append("username = ?, ");
            parameters.add(username);
        }
        if (password != null) {
            queryBuilder.append("password = ?, ");
            parameters.add(password);
        }
        if (alamat != null) {
            queryBuilder.append("alamat = ?, ");
            parameters.add(alamat);
        }
        if (email != null) {
            queryBuilder.append("email = ?, ");
            parameters.add(email);
        }

        // Remove the last comma and space
        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE id = ?");
        parameters.add(id);

        String query = queryBuilder.toString();

        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user by id", e);
            return false;
        }
    }

    public boolean delete(String id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting user", e);
            return false;
        }
    }
}
