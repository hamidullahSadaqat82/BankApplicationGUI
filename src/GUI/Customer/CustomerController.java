package GUI.Customer;

import GUI.Admin.AlertsHelpAndChangeSceneMethods;
import Logic.RelatedCustomer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class CustomerController extends AlertsHelpAndChangeSceneMethods {
    RelatedCustomer RC = new RelatedCustomer();

    public static int CustomerId;

    @FXML private TextField amountForCustomerAccount;
    @FXML private TextField amountForCustomerWithdraw;

    @FXML private TextField anotherAccountNumberForMoney;
    @FXML private TextField amountForAnotherAccount;

    public void backToMain (ActionEvent BackToMain) {
        changeSceneByMenuItem(BackToMain, "/GUI/Main/Main.fxml");
    }
    public void backToCustomer (ActionEvent BackToCustomer) {
        changeSceneByMenuItem(BackToCustomer, "/GUI/Customer/CustomerMain.fxml");
    }
    public void depositBalance (ActionEvent DepositBalance) {
       changeScene(DepositBalance, "/GUI/Customer/DepositMoney.fxml");
    }
    public void withdrawBalance (ActionEvent Withdraw) {
        changeScene(Withdraw, "/GUI/Customer/WithrawBalance.fxml");
    }
    public void transferMoney (ActionEvent TransferMany) {
        changeScene(TransferMany, "/GUI/Customer/TransferMony.fxml");
    }

    public void CheckBalance () {
        double result = RC.checkCustomerBalance(CustomerId);
        if (result != 00000.0123456123456D) {
            conformationAlert("About Customer", "Checking balance", "Your balance is: " + result);
        } else {
            errorAlert("About Customer", "Checking balance", "Error in database");
        }
    }
    public void DepositBalance () {
        try {
            double AmountToCustomerAccount = Double.parseDouble(amountForCustomerAccount.getText());
            double result = RC.depositBalance(CustomerId, AmountToCustomerAccount);
            if (result != 00000000000.123321123d){
                conformationAlert("About Customer", "Deposit balance", "Your Currently balance is: " + result);
            } else {
                errorAlert("About Customer", "Deposit balance", "Error in database");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Customer", "Deposit balance", "Please Enter only double value in amount field.");
        }

    }
    public void WithdrawBalance () {
        try {
            double AmountForCustomerWithdraw = Double.parseDouble(amountForCustomerWithdraw.getText());
            if (AmountForCustomerWithdraw != 0) {
                double result = RC.withdrawBalance(CustomerId, AmountForCustomerWithdraw);
                if (result == 0.0001) {
                    errorAlert("About Customer","Withdraw balance", "Your don't have (" + AmountForCustomerWithdraw + ") balance.");
                } else if (result == 0.0002){
                    errorAlert("About Customer", "Deposit balance", "Error in database.");
                } else {
                    conformationAlert("About Customer", "Withdraw balance", "Money successfully Withdraw, your currently balance is: (" + result + ").");
                }
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Customer", "Withdraw balance", "Please Enter only double value in amount field.");
        }
    }
    public void TransferMoney () {
        try {
            int AnotherAccountNumberForMoney = Integer.parseInt( anotherAccountNumberForMoney.getText());
            double AmountForAnotherAccount = Double.parseDouble( amountForAnotherAccount.getText());
            double result = RC.transferMoney(CustomerId, AnotherAccountNumberForMoney, AmountForAnotherAccount);
            if(result == 0.0001) {
                errorAlert("About Customer", "Transfer balance", "You don't have (" + AmountForAnotherAccount + ") money in your account.");
            } else if (result == 0.0002) {
                errorAlert("About Customer", "Transfer balance", "Error in database.");
            } else {
                conformationAlert("About Customer", "Transfer balance",  "Amount successfully transfer, your current balance is (" + result + ").");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Customer", "Transfer balance", "Please Enter only double value in amount field.");
        }
    }







}
