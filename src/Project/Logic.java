package Project;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.lang.Math;
import java.text.DecimalFormat;

public class Logic {

	private ArrayList<Person> _people;
	private Pane _mainPane;
	private int _counter;
	private int _numberOfLabels;
	private DecimalFormat _format;

	public Logic(Pane _pane) {
		_format = new DecimalFormat("##.00");
		_numberOfLabels = 1;
		_mainPane = _pane;
		_people = new ArrayList<Person>();
		this.addFirstPerson();
		_counter = 0;
		this.setUpLabel();
	}

	public void setUpLabel() {
		Button nextPersonButton = new Button("Next Person");
		_mainPane.getChildren().add(nextPersonButton);
		nextPersonButton.setLayoutX(150);
		nextPersonButton.setLayoutY(600);
		nextPersonButton.setOnAction(new NextPersonHandler());
		Button computeTransaction = new Button("Compute Transaction");
		_mainPane.getChildren().add(computeTransaction);
		computeTransaction.setLayoutX(250);
		computeTransaction.setLayoutY(600);
		computeTransaction.setOnAction(new TransactionHandler());
	}

	private void addFirstPerson() {
		_people.add(new Person(_mainPane));
	}

	private void addPerson() {
		_people.add(new Person(_mainPane));

	}

	private double getAverage() {
		double sum = 0;
		for (int i = 0; i < _people.size(); i++) {
			sum = (sum + _people.get(i).getAmount());

		}
		return (sum / (_people.size()));
	}

	private void calcAmountOwed() {
		for (int i = 0; i < _people.size(); i++) {
			_people.get(i).setAmtOwed(_people.get(i).getAmount() - this.getAverage());

			if (_people.get(i).getAmtOwed() < 0) {
				Label label = new Label(
						_people.get(i).getName() + " owes $" + _format.format((-1) * _people.get(i).getAmtOwed()));
				label.setLayoutX(100);
				label.setLayoutY(100 + (_numberOfLabels * 20));
				_mainPane.getChildren().add(label);
				_numberOfLabels++;

			}

			else if (_people.get(i).getAmtOwed() > 0) {
				Label label = new Label(
						_people.get(i).getName() + " will get $" + _format.format(_people.get(i).getAmtOwed()));
				label.setLayoutX(100);
				label.setLayoutY(100 + (_numberOfLabels * 20));
				_mainPane.getChildren().add(label);
				_numberOfLabels++;
			}

			else {
				Label label = new Label(_people.get(i).getName() + " will not give or receive any money! ");
				label.setLayoutX(100);
				label.setLayoutY(100 + (_numberOfLabels * 20));
				_mainPane.getChildren().add(label);
				_numberOfLabels++;
			}

		}
	}

	public void transactions() {

		_numberOfLabels = _numberOfLabels + 5;
		Label transactions = new Label("TRANSACTIONS");
		transactions.setTextFill(Color.RED);
		transactions.setLayoutX(100);
		transactions.setLayoutY(100 + (_numberOfLabels * 20));
		_mainPane.getChildren().add(transactions);
		_numberOfLabels++;

		for (int i = 0; i < _people.size(); i++) {
			if (_people.get(i).getAmtOwed() < 0) {

				for (int j = 0; j < _people.size(); j++) {

					if (_people.get(j).getAmtOwed() > 0) {

						double dif = _people.get(i).getAmtOwed() + _people.get(j).getAmtOwed();

						if (dif > 0) {
							Label label = new Label(_people.get(i).getName() + " pays " + _people.get(j).getName()
									+ " $ " + _format.format((-1) * _people.get(i).getAmtOwed()));
							label.setLayoutX(100);
							label.setLayoutY(100 + (_numberOfLabels * 20));
							_mainPane.getChildren().add(label);
							_numberOfLabels++;
							_people.get(i).setAmtOwed(0);
							_people.get(j).setAmtOwed(dif);

						} else if (dif < 0) {
							Label label = new Label(_people.get(i).getName() + " pays " + _people.get(j).getName()
									+ " $ " + _format.format(_people.get(j).getAmtOwed()));
							label.setLayoutX(100);
							label.setLayoutY(100 + (_numberOfLabels * 20));
							_mainPane.getChildren().add(label);
							_numberOfLabels++;
							_people.get(j).setAmtOwed(0);
							_people.get(i).setAmtOwed(dif);
						} else {
							Label label = new Label(_people.get(i).getName() + " pays " + _people.get(j).getName()
									+ " $ " + _format.format(_people.get(j).getAmtOwed()));
							label.setLayoutX(100);
							label.setLayoutY(100 + (_numberOfLabels * 20));
							_mainPane.getChildren().add(label);
							_numberOfLabels++;
							_people.get(i).setAmtOwed(0);
							_people.get(j).setAmtOwed(0);
						}

						if (_people.get(i).getAmtOwed() == 0) {
							break;
						}
					}
				}
			}
		}
	}

	private class NextPersonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e3) {
			_people.get(_counter).getChildPane().getChildren().clear();
			Logic.this.addPerson();
			_counter++;

		}
	}

	private class TransactionHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e2) {
			_mainPane.getChildren().clear();
			Label average = new Label();
			average.setText(
					"Everyone should end up paying " + "$" + String.valueOf(_format.format(Logic.this.getAverage())));
			average.setLayoutX(100);
			average.setLayoutY(100);
			_mainPane.getChildren().add(average);
			Logic.this.calcAmountOwed();
			Logic.this.transactions();
		}
	}

}
