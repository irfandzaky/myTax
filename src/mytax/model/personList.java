/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytax.model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author IRFAN
 */
public class personList {
    private ObservableList<Person> personData;

    public personList() {
        personData = FXCollections.observableArrayList();
    }
    
    
    public ObservableList<Person> getPersonData(){
        return personData;
    }
    
    
    public void setFromArray(SimplePerson[] array){
        personData = FXCollections.observableArrayList();
        for (SimplePerson SP : array) {
            personData.add(new Person(SP.getNik(),SP.getJumlah(),SP.getGaji(),SP.getTunjangan(),SP.getNama(),SP.getStatus()));
        }
    }
    public void loadXMLFile(){
    try{
        XStream xs = new XStream(new StaxDriver());
        FileInputStream in = new FileInputStream("Person.xml");
        String s = "";
        int c = in.read();
        while(c != -1){
            s += (char) c;
            c = in.read();
        }
        
        SimplePerson[] array = (SimplePerson[]) xs.fromXML(s);
        this.setFromArray(array);
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
//    public getPieData(){
//        
//    }
        public SimplePerson[] getArray(){
        SimplePerson[] array = new SimplePerson[personData.size()];
        for (int i = 0; i < personData.size(); i++) {
            int NIK = personData.get(i).getNik();
            String jumlah = personData.get(i).getJumlah();
            int gaji= personData.get(i).getGaji();
            int tunjangan = personData.get(i).getTunjangan();
            String nama = personData.get(i).getNama();
            String status = personData.get(i).getStatus();
            array[i] = new SimplePerson(NIK,jumlah,gaji,tunjangan,nama,status);
            
        }
        return array;
}
        
    public void saveXMLFile(){
        SimplePerson[] array = this.getArray();
        XStream xs = new XStream(new StaxDriver());
        String xml = xs.toXML(array);
        
        try{
            FileOutputStream out = new FileOutputStream("Data.xml");
            byte[] bytes =xml.getBytes("UTF-8");
            out.write(bytes);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }    

    
    
}
