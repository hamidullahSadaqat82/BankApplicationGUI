package GUI.Admin;

import Logic.RelatedManager;
import Styles.DialogLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminManagerPartController extends AlertsHelpAndChangeSceneMethods{
    private final RelatedManager RM = new RelatedManager();

    @FXML private TextField managerId;
    @FXML private TextField managerTid;
    @FXML private TextField managerName;
    @FXML private TextField managerFName;
    @FXML private TextField managerPhone;
    @FXML private TextField managerEmail;
    @FXML private TextField managerPassword;
    @FXML private TextField managerContry;
    @FXML private TextField managerProvence;
    @FXML private TextField managerStreet;

    @FXML private TextField managerIdForSearch;
    @FXML private TextField managerIdForDelete;
    @FXML private TextField managerIdNumberForChangePassword;
    @FXML private TextField managerNewPassword;
    @FXML private TextField managerConformPassword;

    @FXML private BorderPane managerDashboard;

    public void backToManager (ActionEvent BackToManager) {
        changeSceneByMenuItem (BackToManager, "/GUI/Admin/AddManagerAccount.fxml");
    }
    public void backToAdmin (ActionEvent BackToAdmin) {
        changeSceneByMenuItem (BackToAdmin, "/GUI/Admin/AdminMain.fxml");
    }
    public void deleteManagerAccount (ActionEvent DeleteManagerAccount) {
        changeScene(DeleteManagerAccount, "/GUI/Admin/DeleteManagerAccount.fxml");
    }
    public void readManagerInformation (ActionEvent ReadManagerInformation) {
        changeScene(ReadManagerInformation, "/GUI/Admin/ReadManagerInformation.fxml");
    }
    public void changeManagerPassword (ActionEvent ChangeManagerPassword) {
        changeScene(ChangeManagerPassword, "/GUI/Admin/ChangeManagerPassword.fxml");
    }

    public void CloseAdminManager (ActionEvent BackToManager) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene  (BackToManager, "/GUI/Admin/AddManagerAccount.fxml");
    }
    public void closeAdminDeleteManagerAccount (ActionEvent DeleteManagerAccount) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene (DeleteManagerAccount, "/GUI/Admin/DeleteManagerAccount.fxml");

    }
    public void closeAdminReadManagerAccount (ActionEvent ReadManagerInformation) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene (ReadManagerInformation, "/GUI/Admin/ReadManagerInformation.fxml");
    }
    public void closeAdminChangeManagerPassword (ActionEvent ChangeManagerPassword) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene (ChangeManagerPassword, "/GUI/Admin/DeleteManagerAccount.fxml");
    }

    public void HideAndShoManagerDashboard () {
        boolean isVisible = managerDashboard.isVisible();
        managerDashboard.setVisible(!isVisible);
        managerDashboard.setManaged(!isVisible);
    }
    public void setManagerInformation() {
        try {
            int ManagerID = Integer.parseInt(managerId.getText());
            String ManagerTid = managerTid.getText();
            String ManagerName = managerName.getText();
            String ManagerFName = managerFName.getText();
            String ManagerPhone = managerPhone.getText();
            String ManagerEmail = managerEmail.getText();
            String ManagerPassword = managerPassword.getText();
            String ManagerContry = managerContry.getText();
            String ManagerProvence = managerProvence.getText();
            String ManagerStreet = managerStreet.getText();
            if (ManagerID > 0 && !ManagerTid.isEmpty() && !ManagerName.isEmpty()
                    && !ManagerFName.isEmpty() && !ManagerPhone.isEmpty()
                    && !ManagerEmail.isEmpty() && !ManagerPassword.isEmpty()
                    && !ManagerContry.isEmpty() && !ManagerProvence.isEmpty()
                    && !ManagerStreet.isEmpty()) {
                int result = RM.addManagerAccount(ManagerID, ManagerTid, ManagerName, ManagerFName, ManagerPhone,
                        ManagerPassword, ManagerContry, ManagerProvence, ManagerStreet);
                if (result == 0) {
                    informationAlert("About Manager", "Account Creation", "Manager account added.");
                } else if (result == 1) {
                    errorAlert("About manager", "Account creation", "On this id we have an account.");
                } else {
                    errorAlert("About manager", "Account creation", "Please Restart your System..");
                }
            } else {
                errorAlert("About manager", "Account creation", "Please get all information in the field.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About manager", "Account creation", "Please Enter only number in id field.");
        }
    }
    public void getManagerInformation() {
        try {
            int ManagerIdForSearch = Integer.parseInt(managerIdForSearch.getText());
            if (ManagerIdForSearch > 0) {
                StringBuilder information;
                information = RM.ReadManagerInformation(ManagerIdForSearch);
                StringBuilder test = new StringBuilder("Null");
                if (!(information.compareTo(test) == 0)) {
                    informationAlert("About manager", "Manager Information", String.valueOf(information));
                } else {
                    errorAlert("About Manager", "Account Information", "Account not registered.");
                }
            } else {
                errorAlert("About Manager", "Account Information", "Please enter id number.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Manager", "Account Information", "Please enter id number.");
        }
    }
    public void DeleteManagerAccount() {
        try {
            int ManagerIdForDelete = Integer.parseInt(managerIdForDelete.getText());
            if (ManagerIdForDelete > 0) {
                int result = RM.DeleteManagerAccount(ManagerIdForDelete);
                if (result == 0) {
                    conformationAlert("About Manager", "Manager Account Deletion", "Manager Account Deleted.");
                }else{
                    errorAlert("About Manager ", "Manager Account Deletion", "Account not Deleted.");
                }
            } else {
                errorAlert("About Manager ", "Manager Account Deletion", "Please enter the id number.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Manager ", "Manager Account Deletion", "Please enter only number in the id field.");
        }
    }
    public void ChangeManagerPassword() {
        try {
            int ManagerIdNumberForChangePassword = Integer.parseInt(managerIdNumberForChangePassword.getText());
            String ManagerNewPassword = managerNewPassword.getText();
            String ManagerConformPassword = managerConformPassword.getText();
            if (ManagerIdNumberForChangePassword > 0
                    && !ManagerNewPassword.equalsIgnoreCase("")
                    && !ManagerConformPassword.equalsIgnoreCase("")) {
                if (ManagerNewPassword.equals(ManagerConformPassword)) {
                    boolean result = RM.ChangeManagerPassword(ManagerIdNumberForChangePassword, ManagerNewPassword);
                    if (result) {
                        conformationAlert("About Manager", "Change Manager Password", "Manger password change to " + ManagerNewPassword + "\n Please remember this password.");
                    } else {
                        errorAlert("About Manager", "Changer manager password", "Manager password was not change please try ag");
                    }
                } else {
                    errorAlert("About Manager", "Changer manager password", "Your new and conform passwords are not match.");
                }
            } else {
                errorAlert("About Manager", "Changer manager password", "Please Enter all information in the fields.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Manager", "Changer manager password", "Please Enter only number in the id fields.");
        }
    }

}
