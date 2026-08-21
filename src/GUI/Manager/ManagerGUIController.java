package GUI.Manager;
import GUI.Admin.AlertsHelpAndChangeSceneMethods;
import Logic.RelatedCustomer;
import Logic.AllControls;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

public class ManagerGUIController extends AlertsHelpAndChangeSceneMethods {
    RelatedCustomer RC = new RelatedCustomer();
    AllControls AC = new AllControls();

    @FXML private TextField managerIdForreadInformation;
    @FXML private TextField managerIdForChangePassword;
    @FXML private TextField managerNewPassword;
    @FXML private TextField managerConformPassword;

    @FXML private TextField customerIdForBalance;
    @FXML private TextField customerBalance;

    @FXML private BorderPane managerDashboard;

    public void showAndHideManagerDashboard () {
        boolean isVisible = managerDashboard.isVisible();
        managerDashboard.setVisible(!isVisible);
        managerDashboard.setManaged(!isVisible);
    }


    public void backToManagerMain(ActionEvent manager) {
        changeSceneByMenuItem(manager, "/GUI/Manager/ManagerMain.fxml");
    }

    public void backToCustomer (ActionEvent customer) {
        changeSceneByMenuItem(customer, "/GUI/Manager/AddCustomerAccount.fxml");
    }

    public void readCustomerInformation (ActionEvent ReadCustomerInformation) {
        changeScene(ReadCustomerInformation, "/GUI/Manager/ReadCustomerInformation.fxml");
    }

    public void changeCustomerPassword (ActionEvent ChangeCustomerPassword) {
        changeScene(ChangeCustomerPassword, "/GUI/Manager/ChangeCustomerPassword.fxml");
    }

    public void deleteCustomerAccount (ActionEvent DeleteCustomerAccount) {
        changeScene(DeleteCustomerAccount, "/GUI/Manager/DeleteCustomerAccount.fxml");
    }

    public void addCustomerBalance(ActionEvent balance) {
        changeScene(balance, "/GUI/Manager/AddCustomerBalance.fxml");
    }

    public void readManagerInformation (ActionEvent ReadManagerInformation) {
        changeScene(ReadManagerInformation, "/GUI/Manager/ReadManagerInformation.fxml");
    }

    public void changeManagerPassword (ActionEvent ChangeManagerPassword) {
        changeScene(ChangeManagerPassword, "/GUI/Manager/ChangeManagerPassword.fxml");
    }

    public void ReadManagerInformation () {
        int ManagerId = Integer.MIN_VALUE;
        try {
            ManagerId = Integer.parseInt(managerIdForreadInformation.getText());
        } catch (NumberFormatException exception) {
            errorAlert("About Manager", "Manager information", "Please enter only number in id field.");
        }
        if (ManagerId > 0) {
            StringBuilder ck = new StringBuilder("Null");
            StringBuilder information = AC.readManagerInformation(ManagerId);
            if (!(information.compareTo(ck) == 0)) {
                informationAlert("About manager", "Manager Information", String.valueOf(information));
            } else {
                informationAlert("About manager", "Manager Information", "Account not found.");
            }
        } else {
            errorAlert("About manager", "Read information", "Please enter id number in id filed.");
        }
    }

    public void ChangeManageInformation() {
        try {
            int ManagerID = Integer.parseInt(managerIdForChangePassword.getText());
            String ManagerNewPassword = managerNewPassword.getText();
            String ManagerConformPassword = managerConformPassword.getText();
            if (ManagerID > 0 && !ManagerNewPassword.isEmpty() && !ManagerConformPassword.isEmpty()) {
                if (ManagerNewPassword.equals(ManagerConformPassword)) {
                    boolean result = AC.changeManagerPassword(ManagerID, ManagerNewPassword);
                    if (result) {
                        informationAlert("About manager", "Change password", "Your password change to " + ManagerNewPassword + ", please remember this password");
                    } else {
                        errorAlert("About manager","Change password", "Password not changed." );
                    }
                } else {
                    errorAlert("About manager","Change password", "Your new password and conform password is not match." );
                }
            } else {
                errorAlert("About manager","Change password", "Please enter your id and more information in the fields." );
            }
        } catch (NumberFormatException exception) {
            errorAlert("About manager","Change password", "Please enter only number in the id field." );
        }
    }

    public void AddCustomerBalance () {
        try {
            int CustomerIdForBalance =  Integer.parseInt(customerIdForBalance.getText());
            double CustomerBalance = Double.parseDouble(customerBalance.getText());
            if (CustomerBalance != 0 && CustomerIdForBalance != 0) {
                boolean result = RC.addCustomerBalance(CustomerIdForBalance, CustomerBalance);
                if (result) {
                    conformationAlert("About customer", "Add Balance", "You are successfully added (" + CustomerBalance + ") balance.");
                } else {
                    errorAlert("About customer", "Add Balance", "Balance not added.");
                }
            } else {
                errorAlert("About customer", "Add Balance", "Please enter data in all field.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About customer", "Add Balance", "Please enter number in id field and Enter balance another filed.");
        }
    }
}
