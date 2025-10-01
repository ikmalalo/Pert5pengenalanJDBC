package main;

import java.util.List;
import model.Siswa;

public class Kelas {
    public static void main(String[] args) {
        // Test CREATE
//        Siswa s = new Siswa();
//        s.nama = "Ikmal";
//        s.kelas = "XII-RPL";
//
//        if (s.create()) {
//            System.out.println(" Siswa berhasil disimpan!");
//        } else {
//            System.out.println(" Gagal menyimpan siswa.");
//        }
//
//        // Test UPDATE
//        
//        Siswa s2 = new Siswa();
//        s2.setId(1); 
//        s2.nama = "Ikmal Update";
//        s2.kelas = "XII-RPL-2";
//
//        if (s2.update()) {
//            System.out.println("Siswa berhasil diupdate!");
//        } else {
//            System.out.println("Gagal update siswa.");
//        }
//
//        // Test DELETE
//        Siswa s3 = new Siswa();
//        s3.setId(2); 
//
//        if (s3.delete()) {
//            System.out.println("Siswa berhasil dihapus!");
//        } else {
//            System.out.println("Gagal hapus siswa.");
//        }

        // Test READ by ID
//        Siswa s4 = Siswa.read(1); 
//        if (s4 != null) {
//            System.out.println("Data siswa ditemukan:");
//            System.out.println("ID: " + s4.getId() +
//                               " | Nama: " + s4.nama +
//                               " | Kelas: " + s4.kelas);
//        } else {
//            System.out.println("Data siswa dengan ID 1 tidak ditemukan.");
//        }

        // Test Read Last()
//        Siswa lastSiswa = Siswa.readLast();
//            if (lastSiswa != null) {
//                System.out.println("Data terakhir ditemukan:");
//                System.out.println("ID: " + lastSiswa.getId() +
//                                   " | Nama: " + lastSiswa.nama +
//                                   " | Kelas: " + lastSiswa.kelas);
//            } else {
//                System.out.println("Data siswa kosong.");
//            }

        // Test Read Previous()

        //    Siswa prevSiswa = Siswa.readPrevious();
        //if (prevSiswa != null) {
        //    System.out.println("Data sebelum terakhir ditemukan:");
        //    System.out.println("ID: " + prevSiswa.getId() +
        //                       " | Nama: " + prevSiswa.nama +
        //                       " | Kelas: " + prevSiswa.kelas);
        //} else {
        //    System.out.println("Tidak ada data sebelumnya.");
        //}
        
        //Test Read Absolute()
        Siswa sAbs = Siswa.readByAbsolute(2);
            if (sAbs != null) {
                System.out.println("Data ke-2 ditemukan:");
                System.out.println("ID: " + sAbs.getId() +
                                   " | Nama: " + sAbs.nama +
                                   " | Kelas: " + sAbs.kelas);
            } else {
                System.out.println("Data ke-2 tidak ditemukan.");
            }

    }
    
    
    
}
