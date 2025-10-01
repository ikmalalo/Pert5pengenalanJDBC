package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Siswa extends Database {
    private int id;
    public String nama;
    public String kelas;

    // Getter & Setter ID
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Create
    public boolean create() {
        boolean isOperationSuccess = false;

        try {
            this.openConnection();

            String sql = "INSERT INTO siswa (nama, kelas) VALUES (?, ?)";
            this.preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, this.nama);
            this.preparedStatement.setString(2, this.kelas);

            int result = this.preparedStatement.executeUpdate();
            if (result > 0) {
                this.id = generateLastID(this.preparedStatement);
                System.out.println("Siswa baru berhasil ditambahkan dengan ID: " + this.id);
                isOperationSuccess = true;
            }

        } catch (SQLException ex) {
            this.displayError(ex);
        } finally {
            this.closeConnection();
        }

        return isOperationSuccess;
    }

    // Update
    
    public boolean update() {
        boolean isOperationSuccess = false;

        try {
            this.openConnection();

            String sql = "UPDATE siswa SET nama = ?, kelas = ? WHERE id = ?";
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setString(1, this.nama);
            this.preparedStatement.setString(2, this.kelas);
            this.preparedStatement.setInt(3, this.id);

            int result = this.preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Siswa dengan ID " + this.id + " berhasil diperbarui.");
                isOperationSuccess = true;
            }

        } catch (SQLException ex) {
            this.displayError(ex);
        } finally {
            this.closeConnection();
        }

        return isOperationSuccess;
    }

    // Delete
    public boolean delete() {
        boolean isOperationSuccess = false;

        try {
            this.openConnection();

            String sql = "DELETE FROM siswa WHERE id = ?";
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, this.id);

            int result = this.preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Siswa dengan ID " + this.id + " berhasil dihapus.");
                isOperationSuccess = true;
            }

        } catch (SQLException ex) {
            this.displayError(ex);
        } finally {
            this.closeConnection();
        }

        return isOperationSuccess;
    }

    // Read by ID

    public static Siswa read(int id) {
        Siswa siswa = null;

        Siswa obj = new Siswa();
        try {
            obj.openConnection();

            String sql = "SELECT * FROM siswa WHERE id = ?";
            obj.preparedStatement = obj.connection.prepareStatement(sql);
            obj.preparedStatement.setInt(1, id);  // ✅ parameter index harus 1

            ResultSet rs = obj.preparedStatement.executeQuery();
            if (rs.next()) {
                siswa = new Siswa();
                siswa.id = rs.getInt("id");
                siswa.nama = rs.getString("nama");
                siswa.kelas = rs.getString("kelas");
            }

        } catch (SQLException ex) {
            obj.displayError(ex);
        } finally {
            obj.closeConnection();
        }
        return siswa;
    }

    // Read Last
    public static Siswa readLast() {
        Siswa siswa = null;

        Siswa obj = new Siswa();
        try {
            obj.openConnection();

            String sql = "SELECT * FROM siswa";
            obj.statement = obj.connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, // bikin scrollable
                ResultSet.CONCUR_READ_ONLY
            );

            ResultSet rs = obj.statement.executeQuery(sql);

            if (rs.last()) { // langsung ke data terakhir
                siswa = new Siswa();
                siswa.id = rs.getInt("id");
                siswa.nama = rs.getString("nama");
                siswa.kelas = rs.getString("kelas");

                System.out.println(">> LAST DATA: ID=" + siswa.id +
                                   ", Nama=" + siswa.nama +
                                   ", Kelas=" + siswa.kelas);
            }

        } catch (SQLException ex) {
            obj.displayError(ex);
        } finally {
            obj.closeConnection();
        }
        return siswa;
    }

    // Test Read Previous()
    // Baca data sebelumnya (mundur dari data terakhir)
        public static Siswa readPrevious() {
            Siswa siswa = null;
            Siswa obj = new Siswa();

            try {
                obj.openConnection();
                String sql = "SELECT * FROM siswa";
                obj.statement = obj.connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                ResultSet rs = obj.statement.executeQuery(sql);

                // pindah ke last dulu
                if (rs.last()) {
                    // lalu mundur satu step (previous)
                    if (rs.previous()) {
                        siswa = new Siswa();
                        siswa.id = rs.getInt("id");
                        siswa.nama = rs.getString("nama");
                        siswa.kelas = rs.getString("kelas");
                    }
                }

            } catch (SQLException ex) {
                obj.displayError(ex);
            } finally {
                obj.closeConnection();
            }

            return siswa;
        }

        // read absolute ()
        public static Siswa readByAbsolute(int posisi) {
            Siswa siswa = null;
            Siswa obj = new Siswa();

            try {
                obj.openConnection();
                String sql = "SELECT * FROM siswa";
                obj.statement = obj.connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                ResultSet rs = obj.statement.executeQuery(sql);

                // Pindah langsung ke baris sesuai posisi
                if (rs.absolute(posisi)) {
                    siswa = new Siswa();
                    siswa.id = rs.getInt("id");
                    siswa.nama = rs.getString("nama");
                    siswa.kelas = rs.getString("kelas");
                }

            } catch (SQLException ex) {
                obj.displayError(ex);
            } finally {
                obj.closeConnection();
            }

            return siswa;
        }

        
    
    // Get All

    public static List<Siswa> getAll() {
        List<Siswa> list = new ArrayList<>();

        // Gunakan Siswa (yang extend Database) supaya bisa akses protected field
        Siswa obj = new Siswa();
        try {
            obj.openConnection();
            String sql = "SELECT * FROM siswa";
            obj.statement = obj.connection.createStatement();
            ResultSet rs = obj.statement.executeQuery(sql);

            while (rs.next()) {
                Siswa s = new Siswa();
                s.id = rs.getInt("id");
                s.nama = rs.getString("nama");
                s.kelas = rs.getString("kelas");
                list.add(s);
            }

        } catch (SQLException ex) {
            obj.displayError(ex);
        } finally {
            obj.closeConnection();
        }

        return list;
    }

}
