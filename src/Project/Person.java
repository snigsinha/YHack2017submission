package Project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Person {
	private double _amountPaid;
	private double _amtOwed;
	private String _name;
	private Pane _childPane;
	private TextField _firstValue;
	private TextField _secondValue;

	public Person(Pane pane) {
		_childPane = new Pane();
		pane.getChildren().add(_childPane);
		this.getInfo();
		_amtOwed = 0;

	}

	public Pane getChildPane() {
		return _childPane;
	}

	public void setAmtOwed(double x) {
		_amtOwed = x;
	}

	public Double getAmtOwed() {
		return _amtOwed;
	}

	public void getInfo() {
		Label label1 = new Label("Enter Name");
		_firstValue = new TextField();

		Label label2 = new Label("Enter Amount Paid");

		_secondValue = new TextField();

		Button button = new Button("Submit");

		label1.setLayoutX(100);
		label1.setLayoutY(400);
		label2.setLayoutX(100);
		label2.setLayoutY(450);
		_firstValue.setLayoutX(250);
		_firstValue.setLayoutY(400);
		_secondValue.setLayoutX(250);
		_secondValue.setLayoutY(450);

		button.setLayoutX(450);
		button.setLayoutY(450);

		_childPane.getChildren().addAll(label1, _firstValue, label2, _secondValue, button);

		button.setOnAction(new PersonHandler());
	}

	public String getName() {
		return _name;
	}

	public Double getAmount() {
		return _amountPaid;
	}

	private class PersonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e1) {
			Label label = new Label();
			try {
				_name = _firstValue.getText();
				_amountPaid = Double.valueOf(_secondValue.getText());
				Label label1 = new Label();
				label1.setText("Information has been entered");
				label1.setLayoutX(160);
				label1.setLayoutY(550);
				_childPane.getChildren().add(label1);
			} catch (NumberFormatException Ignore) {
				Label label2 = new Label();
				label2.setText("Invalid Input, Please try again");
				label2.setLayoutX(300);
				label2.setLayoutY(550);
				_childPane.getChildren().add(label2);

			}
		}
	}

}