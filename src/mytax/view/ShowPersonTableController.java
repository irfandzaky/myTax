/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytax.view;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import mytax.Main;
import mytax.model.Person;
import mytax.model.SimplePerson;
import mytax.model.personList;

/**
 * FXML Controller class
 *
 * @author IRFAN
 */
public class ShowPersonTableController implements Initializable {

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Integer> nikColumn;
    @FXML
    private TableColumn<Person, String> namaColumn;
    @FXML
    private TableColumn<Person, String> statusColumn;
    @FXML
    private TableColumn<Person, String> jumlahColumn;
    
    @FXML
    private Label nikLabel;
    @FXML
    private Label namaLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label jumlahLabel;
    @FXML
    private Label gajiLabel;
    @FXML
    private Label tunjanganLabel;
    @FXML
    private Label pKotorLabel;
    @FXML private TextArea hasil;
    private Main mainApp;
    private int kotor;
    private int hs;
    private int bersih;
    private Person ps;
//    int kotor = 
    public ShowPersonTableController() {
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nikColumn.setCellValueFactory(new PropertyValueFactory("nik"));
        namaColumn.setCellValueFactory(new PropertyValueFactory("nama"));
        statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        jumlahColumn.setCellValueFactory(new PropertyValueFactory("jumlah"));
        
        showPersonDetails(null);
        
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    } 
    
    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
    }
    
    public void showPersonTable(Main mainApp){
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getPersonData());
    }
    private void datatable(){
    try{
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream berkas = new FileInputStream("Person.xml");
        String s ="";
        int c = berkas.read();
        while(c != -1){
            s += (char) c;
            c = berkas.read();
        }
        SimplePerson[] array = (SimplePerson[]) xstream.fromXML(s);
        mainApp.setFromArray(array);
        berkas.close();
        
        for(SimplePerson p : array){
            personTable.getItems().addAll();
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    }
                
    
    private void showPersonDetails(Person person){
        if(person != null){
            nikLabel.setText(Integer.toString(person.getNik()));
            namaLabel.setText(person.getNama());
            statusLabel.setText(person.getStatus());
            jumlahLabel.setText((person.getJumlah()));
            gajiLabel.setText(Integer.toString(person.getGaji()));
            tunjanganLabel.setText(Integer.toString(person.getTunjangan()));
            kotor = (person.getGaji()*12)+(person.getTunjangan()*12);
            if(kotor<59000000){
                hs = 0;
      
            }else if(kotor>=59000000 && kotor<90000000){ 
                if("Kawin".equals(person.getStatus())){
                            bersih = kotor - (54000000 + 4500000);
                            hs = bersih * 5/100;
                              if(Integer.parseInt(person.getJumlah())>0){        
                            bersih = kotor - (54000000 + 9000000);
                            hs = bersih * 5/100;
                              }
                }else if("Belum Kawin".equals(person.getStatus())){            
                        bersih = kotor - 54000000;
                        hs = bersih * 5/100;
                    }
        
            }else if(kotor>=90000000){ 
                if("Kawin".equals(person.getStatus())){
                            bersih = kotor - (54000000 + 4500000);
                            hs = bersih * 10/100;
                              if(Integer.parseInt(person.getJumlah())>0){        
                            bersih = kotor - (54000000 + 9000000);
                            hs = bersih * 10/100;
                              }
                }else if("Belum Kawin".equals(person.getStatus())){            
                        bersih = kotor - 54000000;
                        hs = bersih * 10/100;
                    }
            }
            pKotorLabel.setText(Integer.toString(kotor));
            hasil.setText(Integer.toString(hs));
            
        }else{
            nikLabel.setText("");
            namaLabel.setText("");
            statusLabel.setText("");
            jumlahLabel.setText("");
            gajiLabel.setText("");
            tunjanganLabel.setText("");
            pKotorLabel.setText("");
        }
//        personTable.setItems();
//      buat method baru untuk load table line 1 load dulu, line 2 save tablenya
    }

    
    @FXML
    private void handleDeletePerson(){
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0){    
        personTable.getItems().remove(selectedIndex);
    }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the Table");
        
        alert.showAndWait();
    }
    } 
    
    @FXML
    private void handleEditPerson(){
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if(selectedPerson != null){
            boolean okClicked = mainApp.showPersonDialog(selectedPerson);
            if(okClicked){
                showPersonDetails(selectedPerson);
            }
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No person Selected");
            alert.setContentText("Please select a person in the Table");
            
            alert.showAndWait();
        }
    }
    
}
