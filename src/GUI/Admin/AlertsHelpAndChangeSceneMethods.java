package GUI.Admin;

import Styles.DialogLoader;
import Styles.StyleLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.util.Objects;

public class AlertsHelpAndChangeSceneMethods {
    protected Parent root;
    protected Scene scene;
    protected Stage stage;

    protected Alert alert;

    public void changeScene (ActionEvent event, String fileName) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fileName)));
            scene = new Scene(root);
            StyleLoader.FileLoader(scene);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void changeSceneByMenuItem (ActionEvent event, String fileName) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fileName)));
            scene = new Scene(root);
            StyleLoader.FileLoader(scene);
            MenuItem menuItem = (MenuItem) event.getSource();
            stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void closeAnyScene (ActionEvent event, String fileName) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fileName)));
            scene = new Scene(root);
            StyleLoader.FileLoader(scene);
            MenuItem menuItem = (MenuItem) event.getSource();
            stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
            stage.setScene(scene);
            stage.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public boolean alertShowAndWhiteResultForCloseTheScene (String title, String header, String content) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        DialogLoader.dialogLoader(alert);
        ButtonType stayButton = new ButtonType("Stay");
        ButtonType exitButton = new ButtonType("Exit");
        alert.getButtonTypes().setAll(stayButton, exitButton);
        return alert.showAndWait().get() == exitButton;
    }

    public void informationAlert (String title, String header, String content) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        DialogLoader.dialogLoader(alert);
        alert.show();
    }
    public void errorAlert (String title, String header, String content) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        DialogLoader.dialogLoader(alert);
        alert.show();
    }
    public void conformationAlert (String title, String header, String content) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        DialogLoader.dialogLoader(alert);
        alert.show();
    }

    public void helpForChangeAdminPassword () {
        informationAlert("About Admin", "Change Admin Password", "First of all you should enter you id in admin id field, then you should enter your new password in password field, the enter this password to password conform field the go to the file menu item and select the change password filed");
    }
    public void helpForReadAdminInformation () {
        informationAlert("About Admin", "Read Admin information", "First of all you should Enter your id number in id field, then go to the file item, and select from there read admin information");
    }
    public void helpForDeleteManagerAccount () {
        informationAlert("About Manager", "Delete Manager Account", "When you want to delete the manager account your should enter the manager id in id field, then you should go to the file mene item and select from there delete manager account, click on it and delete the account, follow the instr.");
    }
    public void helpForReadManagerInformation () {
        informationAlert("About Manager", "Delete Manager Account", "When you want to read the manager account information your should enter the manager id in id field, then you should go to the file mene item and select from there Read information, click on it and read the information, follow the instr.");
    }
    public void helpForChangeManagerPassword () {
        informationAlert("About Manager", "Delete Manager Account", "When you want to change the manager account password, your should enter the manager id, new password for manager account, conform password for manager account, then you should go to the file mene item and select from there Change the password, click on it and Change the password, follow the instr.");
    }
    public void helpForAddCustomerAccount () {
        informationAlert("About Manager", "Delete Manager Account", "When you want to add the customer account, your should enter the all data in his field, then you should go to the file mene item and select from there Save information, click on.");
    }
    public void helpForDeleteCustomerAccount () {
        informationAlert("About Manager", "Delete Manager Account", "When you want to delete the customer account your should enter the customer id in id field, then you should go to the file mene item and select from there delete customer account, click on it and delete the account, follow the instr.");
    }
    public void helpForReadCustomerInformation () {
        informationAlert("About Manager", "Delete Manager Account", "When you want to read the customer account information your should enter the customer id in id field, then you should go to the file mene item and select from there Read information, click on it and read the information, follow the instr.");
    }
    public void helpForChangeCustomerPassword () {
        informationAlert("About Manager", "Delete Manager Account", "When you want to change the customer account password, your should enter the customer id, new password for manager account, conform password for manager account, then you should go to the file mene item and select from there Change the password, click on it and Change the password, follow the instr.");
    }

    public void howToAddTheBalance () {

    }
    public void howToWithdrawBalance () {

    }
    public void howToTransferTheBalance () {

    }


}
