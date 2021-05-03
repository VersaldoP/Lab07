/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	 private Model model;
	 private List<Nerc> NercList;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbNerc"
    private ComboBox<Nerc> cmbNerc; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

   
    
    @FXML
    void doRun(ActionEvent event) {
    	txtResult.clear();
    	try {
    		
    		Nerc selectedNerc = cmbNerc.getSelectionModel().getSelectedItem();
			if (selectedNerc == null) {
				txtResult.setText("Select a NERC (area identifier)");
				return;
			}

			int maxY = Integer.parseInt(txtYears.getText());
			if (maxY <= 0) {
				txtResult.setText("Select a number of years greater than 0");
				return;
			}

			int maxH = Integer.parseInt(txtHours.getText());
			if (maxH <= 0) {
				txtResult.setText("Select a number of hours greater than 0");
				return;
			}

			txtResult.setText(
					String.format("Computing the worst case analysis... for %d hours and %d years", maxH, maxY));
			List<PowerOutage> worstCase = model.getListEvent(selectedNerc,maxH, maxY );

			txtResult.clear();
			txtResult.appendText("Tot people affected: " + model.maxPersone + "\n");
			txtResult.appendText("Tot hours of outage: " + model.oreDisservizio + "\n");

			for (PowerOutage ee : worstCase) {
				txtResult.appendText(String.format("%s %s %d %d",  ee.getData_inizio(),
						ee.getData_fine(), ee.getDifferenza(), ee.getCustomers_affected()));
				txtResult.appendText("\n");
			}

		} catch (NumberFormatException e) {
			txtResult.setText("Insert a valid number of years and of hours");
		}
    }
    private void setComboNerc() {
		// TODO Auto-generated method stub
		NercList =model.getNercList();
//		Collections.sort(NercList); Non riesco a capire perchè non mi lascia fare la sort
		cmbNerc.getItems().addAll(NercList);
		
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbNerc != null : "fx:id=\"cmbNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
        // Utilizzare questo font per incolonnare correttamente i dati;
        txtResult.setStyle("-fx-font-family: monospace");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	setComboNerc();
    }

	
}
