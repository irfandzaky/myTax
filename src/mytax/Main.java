/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytax;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mytax.model.Person;
import mytax.model.SimplePerson;
import mytax.model.personList;
import mytax.view.MainMenuController;
import mytax.view.NewTaxController;
import mytax.view.ShowPersonTableController;

/**
 *
 * @author IRFAN
 */
public class Main extends Application {
    private Stage primaryStage;
    private ObservableList<Person> personData;
    
    @Override
    public void start(Stage primaryStage) {
    try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/mainMenu.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        
        Scene scene = new Scene(pane);
        
        primaryStage.setTitle("My Tax");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        MainMenuController menu = loader.getController();
        menu.setMainApp(this);
    }catch(Exception e){
        e.printStackTrace();
    }
    }
   
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    
    public void ShowPersonTable(){
    try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/showPersonTable.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        
        ShowPersonTableController controller = loader.getController();
        controller.setMainApp(this);
        
        Scene scene = new Scene(pane);
        
        primaryStage.setTitle("My Tax");
        primaryStage.setScene(scene);
        primaryStage.show();
    }catch(IOException e){
        e.printStackTrace();
    }
    }
    
    public boolean showPersonDialog(Person person){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/newTax.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Data");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            NewTaxController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Main() {
        personData = FXCollections.observableArrayList();
        personData.add(new Person(279,"3",5000000,5000,"Irfan","Belum Kawin"));
        personData.add(new Person(135,"6",4000,4000,"dd","tidak"));
    
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
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
