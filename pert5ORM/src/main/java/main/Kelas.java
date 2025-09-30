package main;

import Model.Siswa;

public class Kelas {
    public static void main(String[] args) {
        // Test CREATE
        Siswa s = new Siswa();
        s.nama = "Ikmal";
        s.kelas = "XII-RPL";

        if (s.create()) {
            System.out.println(" Siswa berhasil disimpan!");
        } else {
            System.out.println(" Gagal menyimpan siswa.");
        }

        // Test UPDATE
        
        Siswa s2 = new Siswa();
        s2.setId(1); 
        s2.nama = "Ikmal Update";
        s2.kelas = "XII-RPL-2";

        if (s2.update()) {
            System.out.println("Siswa berhasil diupdate!");
        } else {
            System.out.println("Gagal update siswa.");
        }

        // Test DELETE
        Siswa s3 = new Siswa();
        s3.setId(2); 

        if (s3.delete()) {
            System.out.println("Siswa berhasil dihapus!");
        } else {
            System.out.println("Gagal hapus siswa.");
        }

        // Test READ by ID
        
        Siswa s4 = Siswa.read(1); 
        if (s4 != null) {
            System.out.println("Data siswa ditemukan:");
            System.out.println("ID: " + s4.getId() +
                               " | Nama: " + s4.nama +
                               " | Kelas: " + s4.kelas);
        } else {
            System.out.println("Data siswa dengan ID 1 tidak ditemukan.");
        }

    }
    
    
}


