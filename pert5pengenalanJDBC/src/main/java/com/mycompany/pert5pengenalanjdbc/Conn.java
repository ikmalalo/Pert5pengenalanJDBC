/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pert5pengenalanjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ikmal
 */
public class Conn {

    // Konfigurasi database
    private static final String URL = "jdbc:mysql://localhost:3306/dbku"; // ganti "dbku" dengan nama database kamu
    private static final String USER = "root"; // username MySQL
    private static final String PASSWORD = ""; // password MySQL (kosong kalau default XAMPP)

    public static void main(String[] args) {
        Connection conn = null;

        try {
            // Buka koneksi
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Koneksi ke database berhasil!");

        } catch (SQLException e) {
            System.out.println(" Gagal konek ke database!");
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println(" Koneksi ditutup.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
