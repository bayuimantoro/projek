package kelompok6.repo;

import kelompok6.model.PaketModel;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import kelompok6.lib.koneksii;

public class PaketRepo {
    private static final String TABLE_NAME = "paketdata";

    public boolean create(PaketModel paket) {
        String query = "INSERT INTO " + TABLE_NAME + " (id, paket, username) VALUES (?, ?, ?)";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, paket.getId());
            stmt.setString(2, paket.getPaket());
            stmt.setString(3, paket.getUsername());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PaketModel> readAll() {
        List<PaketModel> pakets = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = koneksii.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                PaketModel paket = new PaketModel(
                        rs.getString("id"),
                        rs.getString("paket"),
                        rs.getString("username"));
                pakets.add(paket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pakets;
    }

    public boolean update(PaketModel paket) {
        String query = "UPDATE " + TABLE_NAME + " SET paket = ?, username = ? WHERE id = ?";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, paket.getPaket());
            stmt.setString(2, paket.getUsername());
            stmt.setString(3, paket.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
    }

    public List<PaketModel> findByUsername(String username) {
        List<PaketModel> pakets = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
        try (Connection connection = koneksii.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PaketModel paket = new PaketModel(
                            rs.getString("id"),
                            rs.getString("paket"),
                            rs.getString("username"));
                    pakets.add(paket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pakets;
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
}
