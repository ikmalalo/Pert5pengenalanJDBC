/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Database;
import model.Siswa;

/**
 *
 * @author ikmal
 */
public class Kelas {

    public static void main(String[] args) {
        Database db = new Database();
        Connection conn = db.openConnection();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM siswa");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String kelas = rs.getString("kelas");

                Siswa s = new Siswa(id, nama, kelas);
                System.out.println(s.getId() + " - " + s.getNama() + " - " + s.getKelas());
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            db.closeConnection();
        }
    }
}
