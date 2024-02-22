/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytax.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import mytax.Main;
import mytax.model.Person;

/**
 * FXML Controller class
 *
 * @author IRFAN
 */
public class PieChartController implements Initializable {
    
    @FXML
    private PieChart pie;
   

    @FXML
    private void handleButtonAction(ActionEvent event) {
        ObservableList<PieChart.Data>pieChartData = FXCollections.observableArrayList(
        new PieChart.Data("Pajak 5%",1),
        new PieChart.Data("Pajak 10%",3),
        new PieChart.Data("Pajak 15%",4),
        new PieChart.Data("Pajak 20%",2));
    
         pie.setTitle("Jumlah Penerima Pajak");
         pie.setData(pieChartData);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
