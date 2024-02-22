/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytax.model;

/**
 *
 * @author IRFAN
 */
public class SimplePerson {
    private int nik;
    private String jumlah;
    private int gaji;
    private int tunjangan;
    private int pKotor;
    private int pajak;
    private String nama;
    private String status;

    public SimplePerson(int nik, String jumlah, int gaji, int tunjangan,String nama, String status) {
        this.nik = nik;
        this.jumlah = jumlah;
        this.gaji = gaji;
        this.tunjangan = tunjangan;
        this.pKotor = pKotor;
        this.pajak = pajak;
        this.nama = nama;
        this.status = status;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public int getTunjangan() {
        return tunjangan;
    }

    public void setTunjangan(int tunjangan) {
        this.tunjangan = tunjangan;
    }

    public int getpKotor() {
        return pKotor;
    }

    public void setpKotor(int pKotor) {
        this.pKotor = this.gaji + this.tunjangan / 10/100;
    }

    public int getPajak() {
        return pajak;
    }

    public void setPajak(int pajak) {
        this.pajak = pajak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
