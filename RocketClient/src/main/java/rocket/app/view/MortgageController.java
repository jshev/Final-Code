package rocket.app.view;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	// Created private instance variables

	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;

	@FXML
	private Label lblIncome;
	@FXML
	private Label lblExpenses;
	@FXML
	private Label lblCreditScore;
	@FXML
	private Label lblHouseCost;
	@FXML
	private Label lblDownPayment;
	@FXML
	private Label lblTerm;
	@FXML
	private Label lblMortgagePayment;
	@FXML
	private Label lblErrors;

	@FXML
	private Button btnCalculate;

	ObservableList<String> terms = FXCollections.observableArrayList(
			"15 Years",
			"30 Years");
	@FXML
	private ComboBox<String> cmbTerm;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		// Called this when btnPayment is pressed, calculate the payment

		Object message = null;
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();

		// Set the loan request details and executed the setters in lq
		try {
			lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
			if (cmbTerm.getValue() == "15 Years") {
				lq.setiTerm(15);
			}
			else {
				lq.setiTerm(30);
			}
			lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
			lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
			lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
			lq.setiExpenses(Double.parseDouble(txtExpenses.getText()));
			lq.setiIncome(Double.parseDouble(txtIncome.getText()));
		}
		catch (RateException e) {
			lblErrors.setText("No rate found.");
		}

		a.setLoanRequest(lq);
		mainApp.messageSend(lq);
		// Sent lq as a message to RocketHub
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		double Payment1 = lRequest.getiIncome() * 0.28;
		double Payment2 = lRequest.getiIncome() * 0.36 - lRequest.getiExpenses();
		double truePayment;

		if (Payment1 < Payment2) {
			truePayment = Payment1;
		}
		else {
			truePayment = Payment2;
		}

		if (lRequest.getdPayment() > truePayment) {
			lblErrors.setText("House Cost is too high.");
		}
		else {
			lblMortgagePayment.setText((String.format("%.2f", lRequest.getdPayment())));
		}
		// Displayed dPayment rounded to two decimal places
	}
}