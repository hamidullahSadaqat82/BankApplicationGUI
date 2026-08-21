package GUI.Main;

import GUI.Admin.AlertsHelpAndChangeSceneMethods;
import GUI.Customer.CustomerController;
import Logic.AllControls;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

public class MainController extends AlertsHelpAndChangeSceneMethods {

    private final AllControls AC = new AllControls();

    @FXML private TextField adminUserName;
    @FXML private TextField adminPassword;
    @FXML private TextField adminQuestion;

    @FXML private TextField adminNewPassword;
    @FXML private TextField adminConformPassword;

    @FXML private TextField adminUserNameFromMain;
    @FXML private TextField adminPasswordFromMain;

    @FXML private TextField managerIdForLogin;
    @FXML private TextField managerUserName;
    @FXML private TextField managerPassword;
    @FXML private TextField managerQuestion;

    @FXML private TextField managerIdForForgetPassword;
    @FXML private TextField managerNewPassword;
    @FXML private TextField managerConformPassword;

    @FXML private TextField customerIdForLogin;
    @FXML private TextField customerUserNameForLogin;
    @FXML private TextField customerPasswordForLogin;

    @FXML private TextField customerIdForForgetPassword;
    @FXML private TextField customerNewPasswordForForgetPassword;
    @FXML private TextField customerConformPasswordForForgetPassword;
    @FXML private TextField customerQuestion;

    @FXML private BorderPane mainDashboard;


    public void ShowAndHideManiDashboard () {
        boolean isVisible = mainDashboard.isVisible();
        mainDashboard.setVisible(!isVisible);
        mainDashboard.setManaged(!isVisible);
    }

    public void goToMain(ActionEvent GoToMain) {
        changeSceneByMenuItem(GoToMain, "/GUI/Main/Main.fxml");
    }
    public void Admin(ActionEvent OpenAdmin) {
        changeScene(OpenAdmin, "AdminPasswordField.fxml");
    }
    public void forgetAdminPassword(ActionEvent ForgetPassword) {
        changeScene(ForgetPassword, "/GUI/Main/AdminPasswordForgetField.fxml");
    }

    public void managerLogin(ActionEvent OpenManagerLoginPage) {
        changeScene(OpenManagerLoginPage, "/GUI/Main/ManagerLoginPage.fxml");
    }
    public void forgetManagerPassword(ActionEvent ForgetPassword) {
        changeScene(ForgetPassword, "/GUI/Main/ManagerPasswordForgetField.fxml");
    }
    public void managerMainSceneLoader(ActionEvent ManagerMainSceneLoader) {
        changeScene(ManagerMainSceneLoader, "/GUI/Manager/ManagerMain.fxml");
    }

    public void customerLogin(ActionEvent CustomerLogin) {
        changeScene(CustomerLogin, "/GUI/Main/CustomerLoginPage.fxml");
    }
    public void customerQuestion(ActionEvent CustomerQuestion) {
        changeScene(CustomerQuestion, "/GUI/Main/CustomerPasswordForgetField.fxml");
    }

    public void AdminLogin(ActionEvent adminLogin) {
        String AdminUserName = adminUserName.getText();
        String AdminPassword = adminPassword.getText();
        boolean result = AC.AdminPasswordChakerForLogin(AdminUserName, AdminPassword);
        if (result) {
            changeScene(adminLogin, "/GUI/Admin/AdminMain.fxml");
        } else {
            errorAlert("About admin login", "login section", "Wrong user name or password");
        }
    }
    public void AdminLoginFromMain(ActionEvent adminLogin) {
        String AdminUserNameFromMain = adminUserNameFromMain.getText();
        String AdminPasswordFromMain = adminPasswordFromMain.getText();
        boolean result = AC.AdminPasswordChakerForLogin(AdminUserNameFromMain, AdminPasswordFromMain);
        if (result) {
            changeScene(adminLogin, "/GUI/Admin/AdminMain.fxml");
        } else {
            errorAlert("About admin login", "login section", "Wrong user name or password");
        }
    }
    public void questionSubmit(ActionEvent openPasswordChanger) {
        String question = adminQuestion.getText();
        if (question.equals("Black")) {
            changeScene(openPasswordChanger, "/GUI/Main/AdminPasswordChanger.fxml");
        } else {
            errorAlert("About admin", "Forget Password", "Wrong answer");
        }
    }
    public void AdminChangePassword() {
        String AdminNewPassword = adminNewPassword.getText();
        String AdminConformPassword = adminConformPassword.getText();
        if (!AdminNewPassword.isEmpty() && !AdminConformPassword.isEmpty()) {
            if (AdminNewPassword.equals(AdminConformPassword)) {
                boolean result = AC.AdminPasswordForget(AdminNewPassword);
                if (result) {
                    conformationAlert("About admin", "Password forget", "your password changed to " + AdminConformPassword + ",please remember this for login");
                    alert.show();
                } else {
                    errorAlert("About admin", "Forget Password", "Sorry your password not changed.");
                }
            } else {
                errorAlert("About admin", "Forget Password", "Your new password and conform password are not match.");
            }
        } else {
            errorAlert("About admin", "Change password", "Please enter data in the fields.");
        }
    }
    public void ManagerQuestionSubmit(ActionEvent openPasswordChanger) {
        String question = managerQuestion.getText();
        if (question.equals("Black")) {
            changeScene(openPasswordChanger, "/GUI/Main/ManagerPasswordChanger.fxml");
        } else {
            errorAlert("About Manager", "Forget Password", "Wrong answer");;
        }
    }
    public void ManagerLogin(ActionEvent managerLogin) {
        try {
            String ManagerUserName = managerUserName.getText();
            String ManagerPassword = managerPassword.getText();
            int ManagerIDNumberForLogin = Integer.parseInt(managerIdForLogin.getText());
            boolean result = AC.managerPasswordChakerForLogin(ManagerIDNumberForLogin, ManagerUserName, ManagerPassword);
            if (result) {
                managerMainSceneLoader(managerLogin);
            } else {
                errorAlert("About Manager", "Login", "Wrong password or userName.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Manager", "Login", "Please Enter only number in id field.");
        }
    }
    public void ManagerChangePassword() {
        int ManagerIDForForgetPassword = Integer.parseInt(managerIdForForgetPassword.getText());
        String ManagerNewPassword = managerNewPassword.getText();
        String ManagerConformPassword = managerConformPassword.getText();
        if (!ManagerNewPassword.isEmpty() && !ManagerConformPassword.isEmpty()) {
            if (ManagerNewPassword.equals(ManagerConformPassword)) {
                boolean result = AC.ManagerPasswordForget(ManagerIDForForgetPassword, ManagerNewPassword);
                if (result) {
                    conformationAlert("About Manager", "Forget Password", "your password changed to " + ManagerNewPassword + ",please remember this for login.");
                } else {
                    errorAlert("About Manager", "Forget Password", "Sorry your password not changed.");
                }
            } else {
                errorAlert("About Manager", "Forget Password", "Your new password and conform password are not match.");
            }
        } else {
            errorAlert("About Manager", "Change password", "Please enter data in the fields.");
        }
    }
    public void CustomerLogin(ActionEvent CustomerMain) {
        try {
            int CustomerIdForLogin = Integer.parseInt(customerIdForLogin.getText());
            String CustomerUserNameForLogin = customerUserNameForLogin.getText();
            String CustomerPasswordForLogin = customerPasswordForLogin.getText();
            CustomerController.CustomerId = CustomerIdForLogin;
            if (!CustomerPasswordForLogin.isEmpty() && !CustomerUserNameForLogin.isEmpty() && CustomerIdForLogin != 0) {
                boolean result =  AC.CustomerPasswordChakerForLogin(CustomerIdForLogin, CustomerUserNameForLogin, CustomerPasswordForLogin);
                if (result) {
                    changeScene(CustomerMain, "/GUI/Customer/CustomerMain.fxml");
                } else {
                    errorAlert("About Customer Login", "Login", "Wrong password or user name.");
                }
            } else {
                errorAlert("About Customer Login", "Login", "Please enter data in all fields.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Customer Login", "Login", "Please enter only number in id field.");
        }
    }
    public void CustomerQuestion(ActionEvent customerQuestionForMethod) {
        String CustomerQuestion = customerQuestion.getText();
        if (CustomerQuestion.equals("Black")) {
            changeScene(customerQuestionForMethod, "/GUI/Main/CustomerPasswordChanger.fxml");
        } else {
            errorAlert("About Customer", "Change Customer Password", "Your answer is wrong");
        }
    }
    public void CustomerForgetPassword() {
        try {
            int CustomerIdForForgetPassword = Integer.parseInt( customerIdForForgetPassword.getText());
            String CustomerNewPassword = customerNewPasswordForForgetPassword.getText();
            String CustomerConformPassword =  customerConformPasswordForForgetPassword.getText();
            if (!CustomerNewPassword.isEmpty() && !CustomerConformPassword.isEmpty()) {
                if (CustomerNewPassword.equals(CustomerConformPassword)) {
                    boolean result = AC.ForgetCustomerPassword(CustomerIdForForgetPassword, CustomerNewPassword);
                    if (result) {
                        informationAlert("About Customer", "Change Customer Password", "Your password change to (" + CustomerNewPassword + ") please remember this password.");
                    } else {
                        errorAlert("About Customer", "Change Customer Password", "Database error please chek the all controller class.");
                    }
                } else {
                    errorAlert("About Customer", "Change Customer Password", "Your new and conform password are not same.");
                }
            } else {
                errorAlert("About Customer", "Change Customer Password", "Please Enter data in all fields.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Customer", "Change Customer Password", "Please Enter only number in id field.");
        }
    }
    public void CloseTheApplication(ActionEvent close) {
        closeAnyScene(close, "/GUI/Main/Main.fxml");
    }

}