package GUI.Admin;
import Logic.AllControls;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

public class AdminGUIController extends AlertsHelpAndChangeSceneMethods{
    private final AllControls AC = new AllControls();

    @FXML private TextField adminIdForChangePassword;
    @FXML private TextField adminNewPassword;
    @FXML private TextField adminConformPassword;
    @FXML private TextField adminIdForReadInformation;
    @FXML private BorderPane adminDashboard;


    public void backToMain (ActionEvent main) {
        changeSceneByMenuItem (main, "/GUI/Main/Main.fxml");
    }
    public void backToAdmin (ActionEvent BackToAdmin) {
        changeSceneByMenuItem (BackToAdmin, "/GUI/Admin/AdminMain.fxml");
    }
    public void changeAdminPassword (ActionEvent ChangeAdminPassword) {
        changeScene(ChangeAdminPassword, "/GUI/Admin/ChangeAdminPassword.fxml");
    }
    public void readAdminInformation(ActionEvent ReadAdminInformation) {
        changeScene(ReadAdminInformation, "/GUI/Admin/ReadAdminInformation.fxml");
    }
    public void addManagerAccount (ActionEvent AddManager) {
        changeScene(AddManager, "/GUI/Admin/AddManagerAccount.fxml");
    }
    public void addCustomerAccount(ActionEvent AddManagerAccount) {
        changeScene(AddManagerAccount, "/GUI/Admin/AddCustomerAccount.fxml");
    }


    public void CloseAdmin (ActionEvent BackToAdmin) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene (BackToAdmin, "/GUI/Main/Main.fxml");
    }
    public void closeChangeAdminPassword (ActionEvent ChangeAdminPassword) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene (ChangeAdminPassword, "/GUI/Admin/ChangeAdminPassword.fxml");
    }
    public void closeReadAdminInformation(ActionEvent ReadAdminInformation) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
         closeAnyScene(ReadAdminInformation, "/GUI/Admin/ReadAdminInformation.fxml");
    }
    public void CloseAdminCustomer (ActionEvent BackToCustomer) {
        if (alertShowAndWhiteResultForCloseTheScene("About Closing", "", "Are you sure to close the application"))
            closeAnyScene  (BackToCustomer, "/GUI/Admin/AddCustomerAccount.fxml");
    }

    public void HideAndShowAdminDashboard () {
        boolean isVisible = adminDashboard.isVisible();
        adminDashboard.setVisible(!isVisible);
        adminDashboard.setManaged(!isVisible);
    }

    public void ChangeAdminPassword() {
        try {
            int AdminId = Integer.parseInt(adminIdForChangePassword.getText());
            String AdminNewPassword = adminNewPassword.getText();
            String AdminConformPassword = adminConformPassword.getText();
            if (AdminId > 0 && !AdminNewPassword.isEmpty() && !AdminConformPassword.isEmpty()) {
                if (AdminNewPassword.equals(AdminConformPassword)) {
                    boolean resul = AC.changeAdminPassword(AdminId, AdminNewPassword);
                    if (resul) {
                        conformationAlert("About admin", "Change password", "Your password change to " + AdminNewPassword + ", please remember this password");
                    } else {
                        errorAlert("About admin", "Change password", "Password not changed.");
                    }
                } else {
                    errorAlert("About Admin", "Change Password", "Your new password and conform password is not match.");
                }
            } else {
                errorAlert("About Admin", "Change Password", "Please enter your id and more information in the fields.");
            }
        } catch (NumberFormatException exception) {
            errorAlert("About Admin", "Change Password", "Please enter only number in id field.");
        }
    }
    public void ReadAdminInformation() {
        int AdminId = Integer.MIN_VALUE;
        try {
            AdminId = Integer.parseInt(adminIdForReadInformation.getText());
        } catch (NumberFormatException exception) {
            errorAlert("About Admin", "Admin information", "Please enter only number in id field.");
        }
        if (AdminId > 0) {
            StringBuilder ck = new StringBuilder("Null");
            StringBuilder information = AC.readAdminInformation(AdminId);
            if (!(information.compareTo(ck) == 0)) {
                informationAlert("About admin", "Admin Information", String.valueOf(information));
            } else {
                errorAlert("About admin", "Admin Information", "Account not found.");
            }
        } else {
            errorAlert("About admin", "Admin Information", "Error in database, please restart your system.");
        }
    }


}




/*
دا کوډ باید په ادمین ډشبورډ کی واچوو خو د دی د استعمال څخه مخی باید مونږ په دیټابیس کی او کوډ کی کالمونه سم کړو

    @FXML private  Label    adminMainName;
    @FXML private  Label    adminMainFName;
    @FXML private  Label    adminMainEmail;
    @FXML private  Label    adminMainPhone;
    @FXML private  Label    adminMainAddress;


String name = "";
String FName = "";
//   String email = "";
String phone = "";
String address = "";
        try {
Connection sql = DriverManager.getConnection(SqlConnection.getUrl(), SqlConnection.getUsername(), SqlConnection.getPassword());
Statement st = sql.createStatement();
ResultSet rs = st.executeQuery("SELECT * FROM ADMININFORMATION");
            while (rs.next()) {
name = rs.getString("NAME");
FName = rs.getString("FNAME");
phone = rs.getString("PHONE");
address = rs.getString("CONTRY");
            }
                    st.close();
            adminMainName.setText(name);
            adminMainFName.setText(FName);
            adminMainPhone.setText(phone);
            adminMainAddress.setText(address);
        } catch (SQLException exception) {
        System.out.println(exception.getMessage());
        }*/
