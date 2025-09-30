// Database.java di package kelas.main
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static final String HOST = "localhost";
    private static final String DB_NAME = "db_siswa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://" + HOST + ":3306/" + DB_NAME;

    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    public Database() {
        this.connection = null;
        this.statement = null;
        this.preparedStatement = null;
        this.resultSet = null;
    }

    public void openConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Koneksi berhasil dibuka!");
            }
        } catch (SQLException e) {
            displayError(e);
        }
    }

    // 🔹 Method generateLastID
    protected int generateLastID(PreparedStatement ps) {
        int lastId = -1;
        try {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                lastId = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            displayError(e);
        }
        return lastId;
    }

    public void getData() {
        String sql = "SELECT * FROM users";
        try {
            openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("nama"));
            }
        } catch (SQLException e) {
            displayError(e);
        } finally {
            closeConnection();
        }
    }

    public void insertMember(String nama, String email) {
        String sql = "INSERT INTO member (nama, email) VALUES (?, ?)";
        try {
            openConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, email);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                int idBaru = generateLastID(preparedStatement);
                System.out.println("Member baru berhasil ditambahkan dengan ID: " + idBaru);
            }

        } catch (SQLException e) {
            displayError(e);
        } finally {
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            if (this.resultSet != null) this.resultSet.close();
            if (this.statement != null) this.statement.close();
            if (this.preparedStatement != null) this.preparedStatement.close();
            if (this.connection != null && !this.connection.isClosed()) this.connection.close();

            this.resultSet = null;
            this.statement = null;
            this.preparedStatement = null;
            this.connection = null;

            System.out.println("Semua koneksi ditutup!");
        } catch (SQLException e) {
            displayError(e);
        }
    }

    protected void displayError(SQLException e) {
        System.out.println("----- ERROR SQL -----");
        System.out.println("Message   : " + e.getMessage());
        System.out.println("SQL State : " + e.getSQLState());
        System.out.println("ErrorCode : " + e.getErrorCode());
        e.printStackTrace();
        System.out.println("---------------------");
    }
}
