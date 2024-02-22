/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytax.view;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytax.Main;
import mytax.model.Person;
import mytax.model.personList;
import static sun.security.krb5.Confounder.bytes;

/**
 * FXML Controller class
 *
 * @author IRFAN
 */
public class NewTaxController implements Initializable {
    @FXML
    private TextField nikField;
    @FXML
    private TextField namaField;
 
    @FXML
    private TextField jumlahField;
    @FXML
    private TextField gajiField;
    @FXML
    private TextField tunjanganField;
    @FXML   private RadioButton kawin;
    @FXML   private RadioButton bkawin;
    @FXML   private ChoiceBox cb;
    private Person[] p;
    private int idCount=0;
    private String status;
    private Stage dialogStage;
    private Person person;
    private Main mainApp;
    private boolean okClicked = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p = new Person[30];
        cb.setValue("0");
        cb.getItems().addAll("0","1","2","3","4","5","6");
    }    
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    public void setPerson(Person person){
        this.person = person;
        
        nikField.setText(Integer.toString(person.getNik()));
        namaField.setText(person.getNama());
        String status1= status;
        String name = (String) cb.getValue();
        gajiField.setText(Integer.toString(person.getGaji()));
        tunjanganField.setText(Integer.toString(person.getTunjangan()));
    }
    
    public boolean isOkClicked(){
        return okClicked;
    }
    
    @FXML
    private void handleOk(){
        if(isInputValid()){
            person.setNik(Integer.parseInt(nikField.getText()));
            person.setNama(namaField.getText());
            person.setStatus(status);
            person.setJumlah((String) cb.getValue());
            person.setGaji(Integer.parseInt(gajiField.getText()));
            person.setTunjangan(Integer.parseInt(tunjanganField.getText()));
            
            while(p[idCount] != null){
                idCount++;
            }
            p[idCount] = new Person(Integer.parseInt(nikField.getText()), (String) cb.getValue(), Integer.parseInt(gajiField.getText()), Integer.parseInt(tunjanganField.getText()) , namaField.getText(),status);
            
            XStream xstream = new XStream(new StaxDriver());
        String xml = xstream.toXML(p);
        System.out.println(""+xml);
    
        try{
            FileOutputStream berkas = new FileOutputStream("Person.xml");
            byte[] bytes = xml.getBytes("UTF-8");
            berkas.write(bytes);
            berkas.close();
        }catch(IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
            okClicked = true;
            dialogStage.close();
            
        }
        
    }
    
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
    
    @FXML
    private void action_1(ActionEvent event) {
        status = kawin.getText();
    }
    
    @FXML
    private void action_2(ActionEvent event) {
        status = bkawin.getText();
    }
    
    private boolean isInputValid(){
        String errorMessage = "";
        
        if (nikField.getText() == null || nikField.getText().length() == 0) {
            errorMessage += "Invalid NIK!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(nikField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid NIK (must be an integer)!\n"; 
            }
        }
        
        if (namaField.getText() == null || namaField.getText().length() == 0) {
            errorMessage += "Invalid name!\n"; 
        }
        
        if (status == null){
            errorMessage += "Invalid Stats!\n"; 
        }
        
        if (cb.getValue() == null) {
            errorMessage += "Invalid Jumlah Anak!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt((String) cb.getValue());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid Jumlah Anak (must be an integer)!\n"; 
            }
        }
        
        if (gajiField.getText() == null || gajiField.getText().length() == 0) {
            errorMessage += "Invalid Salary!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(gajiField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid Salary (must be an integer)!\n"; 
            }
        }
        
        if (tunjanganField.getText() == null || tunjanganField.getText().length() == 0) {
            errorMessage += "Invalid NIK!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(tunjanganField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid NIK (must be an integer)!\n"; 
            }
        }
        
        if (errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            return false;
        }
        
    }
    
    
}
