/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytax.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mytax.Main;
import mytax.model.Person;
import mytax.model.personList;

/**
 * FXML Controller class
 *
 * @author IRFAN
 */
public class MainMenuController implements Initializable {
    
    private Main mainApp;
    
    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    public void handleActionShowTable(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/showPersonTable.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Scene scene = new Scene(pane);
            
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Laporan Data Pajak");
            stage.show();
            
            ShowPersonTableController controller = loader.getController();
            controller.setMainApp(mainApp);
            controller.showPersonTable(mainApp);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleNewPerson(){
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonDialog(tempPerson);
        if (okClicked){
           mainApp.getPersonData().add(tempPerson);
        }
        
    }
    
    
    //
    @FXML
    public void handlePie(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/pieChart.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Scene scene = new Scene(pane);
            
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Pie Chart");
            stage.show();
            
            PieChartController controller = loader.getController();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
