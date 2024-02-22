/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytax.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author IRFAN
 */
public class Person {
    private SimpleIntegerProperty nik;
    private SimpleStringProperty jumlah;
    private SimpleIntegerProperty gaji;
    private SimpleIntegerProperty tunjangan;
    private SimpleIntegerProperty pKotor;
    private SimpleIntegerProperty pajak;
    private SimpleStringProperty nama;
    private SimpleStringProperty status;
//jumlah dan status beda button

    public Person() {
        this(0,null,0,0,null,null);
    }
       
    public Person(int nik, String jumlah, int gaji, int tunjangan, String nama, String status) {
        this.nik = new SimpleIntegerProperty(nik);
        this.jumlah = new SimpleStringProperty(jumlah);
        this.gaji = new SimpleIntegerProperty(gaji);
        this.tunjangan = new SimpleIntegerProperty(tunjangan);
        this.nama = new SimpleStringProperty(nama);
        this.status = new SimpleStringProperty(status);
    }

    

    public int getNik() {
        return nik.get();
    }

    public void setNik(int nik) {
        this.nik.set(nik);
    }
    
    public IntegerProperty nikProperty() {
        return nik;
    }

    public String getJumlah() {
        return jumlah.get();
    }

    public void setJumlah(String jumlah) {
        this.jumlah.set(jumlah);
    }
    
    public StringProperty jumlahProperty() {
        return jumlah;
    }

    public int getGaji() {
        return gaji.get();
    }

    public void setGaji(int gaji) {
        this.gaji.set(gaji);
    }
    
    public IntegerProperty gajiProperty() {
        return gaji;
    }

    public int getTunjangan() {
        return tunjangan.get();
    }

    public void setTunjangan(int tunjangan) {
        this.tunjangan.set(tunjangan);
    }
    
    public IntegerProperty tunjanganProperty() {
        return tunjangan;
    }

    public SimpleIntegerProperty getPKotor() {
        return this.pKotor;
    }

    public void setPKotor(int pKotor) {
        this.pKotor.set(pKotor);
    }
    
    public IntegerProperty pKotorProperty() {
        return pKotor;
    }

    public int getPajak() {
        return pajak.get();
    }

    public void setPajak(int pajak) {
        this.pajak.set(pajak);
    }
    
    public IntegerProperty pajakProperty() {
        return pajak;
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }
    
    public StringProperty namaProperty() {
        return nama;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
    
    public StringProperty statusProperty() {
        return status;
    }

    
    
    
    
}
