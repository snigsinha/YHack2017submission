package Project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class PaneOrganiser {

	private BorderPane _root;
	private Pane _pane;
	private Logic _logic;

	public PaneOrganiser() {
		_root = new BorderPane();

		_pane = new Pane();
		_root.setCenter(_pane);

		_logic = new Logic(_pane);
		this.quitButton();
		this.showInstructions();
	}
	
	public void showInstructions() {
		Label instructions = new Label("Instructions!");
		instructions.setLayoutX(50);
		instructions.setLayoutY(100);
		Label instruction1 = new Label("1) Input name and amount owed for a person in respective fields, press the 'Submit' button");
		instruction1.setLayoutX(50);
		instruction1.setLayoutY(130);
		Label instruction2 = new Label("2) Press the 'Next Person' button when ready to add details for next person");
		instruction2.setLayoutX(50);
		instruction2.setLayoutY(160);
		Label instruction3 = new Label("3) After inputting details in respective fields for last person, press the 'submit' button");
		instruction3.setLayoutX(50);
		instruction3.setLayoutY(190);
		Label instruction4 = new Label("then press the 'Compute Transaction' button to see a preview of the transactions");
		instruction4.setLayoutX(50);
		instruction4.setLayoutY(210);
		_pane.getChildren().addAll(instructions,instruction1,instruction2,instruction3,instruction4);
	}
	
	public void quitButton() {
		Button quitbtn = new Button();
		quitbtn.setText("Quit!");
		quitbtn.setLayoutX(400);
		quitbtn.setLayoutY(600);		
		quitbtn.setOnAction(new QuitButtonHandler());
		_pane.getChildren().add(quitbtn);
	}
	
	

	public Node getRoot() {
		return _root;

	}
	
	private class QuitButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e) {
			Platform.exit();
		}
	}
}