package GUI.Admin;

import Logic.RelatedCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminCustomerPartController extends AdminGUIController{
    private final RelatedCustomer RC = new RelatedCustomer();

    @FXML private TextField customerId;
    @FXML private TextField customerTid;
    @FXML private TextField customerName;
    @FXML private TextField customerFName;
    @FXML private TextField customerPhone;
    @FXML private TextField customerEmail;
    @FXML private TextField customerPassword;
    @FXML private TextField customerContry;
    @FXML private TextField customerProvence;
    @FXML private TextField customerStreet;

    @FXML private TextField customerIdForSearch;
    @FXML private TextField customerIdForDelete;
    @FXML private TextField customerIdNumberForChangePassword;
    @FXML private TextField customerNewPassword;
    @FXML private TextField customerConformPassword;

    @FXML private BorderPane customerDashboard;

    public void backToCustomer (ActionEvent BackToCustomer) {
        changeSceneByMenuItem (BackToCustomer, "/GUI/Admin/AddCustomerAccount.fxml");
    }
    public void deleteCustomerAccount(ActionEvent DeleteCustomerAccount) {
        changeScene(DeleteCustomerAccount, "/GUI/Admin/DeleteCustomerAccount.fxml");
    }
    public void readCustomerInformation(ActionEvent ReadCustomerInformation) {
        changeScene(ReadCustomerInformation, "/GUI/Admin/ReadCustomerInformation.fxml");
    }
    public void changeCustomerPassword(ActionEvent ChangeCustomerInformation) {
        changeScene(ChangeCustomerInformation, "/GUI/Admin/ChangeCustomerPassword.fxml");
    }

    public void DeleteCustomerAccountScene(ActionEvent DeleteCustomerAccount) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene (DeleteCustomerAccount, "/GUI/Admin/DeleteCustomerAccount.fxml");
    }
    public void ReadCustomerInformation(ActionEvent ReadCustomerInformation) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene (ReadCustomerInformation, "/GUI/Admin/ReadCustomerInformation.fxml");
    }
    public void ChangeCustomerPasswordScene(ActionEvent ChangeCustomerInformation) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene (ChangeCustomerInformation, "/GUI/Admin/ChangeCustomerPassword.fxml");
    }

    public void HideAndShowCustomerDashboard () {
        boolean isVisible = customerDashboard.isVisible();
        customerDashboard.setVisible(!isVisible);
        customerDashboard.setManaged(!isVisible);
    }

    public void setCustomerInformation() {
        try {
            int CustomerID = Integer.parseInt(customerId.getText());
            String CustomerTid = customerTid.getText();
            String CustomerName = customerName.getText();
            String CustomerFName = customerFName.getText();
            String CustomerPhone = customerPhone.getText();
            String CustomerEmail = customerEmail.getText();
            String CustomerPassword = customerPassword.getText();
            String CustomerContry = customerContry.getText();
            String CustomerProvence = customerProvence.getText();
            String CustomerStreet = customerStreet.getText();
            if (!CustomerTid.isEmpty()
                    && !CustomerName.isEmpty()
                    && !CustomerFName.isEmpty()
                    && !CustomerPhone.isEmpty()
                    && !CustomerEmail.isEmpty()
                    && !CustomerPassword.isEmpty()
                    && !CustomerContry.isEmpty()
                    && !CustomerProvence.isEmpty()
                    && !CustomerStreet.isEmpty()) {
                int result = RC.addCustomerAccount(CustomerID, CustomerTid, CustomerName, CustomerFName, CustomerPhone, CustomerEmail, CustomerPassword,
                        CustomerContry, CustomerProvence, CustomerStreet);
                if (result == 10) {
                    conformationAlert("About Customer", "Account Creation", "Customer account added.");
                } else if (result == 1) {
                    errorAlert("About Customer", "Account Creation", "On this id we have an account.");
                } else {
                    errorAlert("About Customer", "Account Creation", "Please Restart your System.");
                }
            } else {
                errorAlert("About Customer", "Account Creation", "Please enter all information in the fields.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Customer", "Account Creation", "Please enter only number in id field.");
        }
    }
    public void getCustomerInformation() {
        try {
            int CustomerId = Integer.parseInt(customerIdForSearch.getText());
            StringBuilder information;
            StringBuilder ck = new StringBuilder("Null");
            information = RC.ReadCustomerInformation(CustomerId);
            if (!(information.compareTo(ck) == 0)) {
                informationAlert("About customer", "Customer Information", String.valueOf(information));
            } else {
                errorAlert("About customer", "Customer information", "Account not Registred.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About customer", "Customer information", "Please Enter only number int id field.");
        }
    }
    public void ChangeCustomerPassword() {
        try {
            int CustomerId = Integer.parseInt(customerIdNumberForChangePassword.getText());
            String CustomerNewPassword = customerNewPassword.getText();
            String CustomerConformPassword = customerConformPassword.getText();
            if (CustomerId > 0
                    && !CustomerNewPassword.isEmpty()
                    && !CustomerConformPassword.isEmpty()) {
                if (CustomerNewPassword.equals(CustomerConformPassword)) {
                    boolean result = RC.ChangeCustomerPassword(CustomerId, CustomerNewPassword);
                    if (result) {
                        conformationAlert("About customer", "Customer account password", "Password change to " + CustomerNewPassword + ", Please remember this password.");
                    } else {
                        errorAlert("About customer", "Customer account password", "Password was not changed please try again.");
                    }
                } else {
                    errorAlert("About customer", "Customer account password", "Your new password and conform password is not match.");
                }
            } else {
                errorAlert("About customer", "Customer account password", "Please enter all information in the fields.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About customer", "Customer account password", "Please enter only number in id field.");
        }
    }
    public void DeleteCustomerAccount() {
        try {
            int CustomerId = Integer.parseInt(customerIdForDelete.getText());
            if (CustomerId > 0) {
                boolean result = RC.DeleteCustomerAccount(CustomerId);
                if (result) {
                    conformationAlert("About customer", "Delete Customer Account", "Account Deleted.");
                } else {
                    errorAlert("About customer", "Delete Customer Account", "Account not found.");
                }
            } else {
                errorAlert("About customer", "Delete Customer Account", "Please Enter Customer ID number.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About customer", "Delete Customer Account", "Please enter only number in id field.");
        }
    }
}
