package Database;

public class Queries {

    public static final String sendManagerInformationToDatabase = "INSERT INTO MANAGERINFORMATION (ID, TID, NAME, FNAME," +
            "PHONE, PASSWORD,  CONTRY, PROVENCE, STREET) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String readManagerInformationFromDatabase = "SELECT * FROM MANAGERINFORMATION WHERE ID = ?";
    public static final String deleteManagerInformationFromDatabase = "DELETE FROM MANAGERINFORMATION WHERE ID = ?";
    public static final String changeManagerPasswordInDatabase = "UPDATE MANAGERINFORMATION SET PASSWORD = ? WHERE ID = ?";


    public static final String sendCustomerInformationToDatabase = "INSERT INTO CUSTOMERINFORMATION (ID, TID, NAME, FNAME," +
            "PHONE, EMAIL, PASSWORD,  CONTRY, PROVENCE, STREET) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String readCustomerInformationFromDatabase = "SELECT * FROM CUSTOMERINFORMATION WHERE ID = ?";
    public static final String deleteCustomerInformationFromDatabase = "DELETE FROM CUSTOMERINFORMATION WHERE ID = ?";
    public static final String changeCustomerPasswordInDatabase = "UPDATE CUSTOMERINFORMATION SET PASSWORD = ? WHERE ID = ?";
    public static final String addCustomerBalance = "UPDATE CUSTOMERINFORMATION SET BALANCE = ? WHERE ID = ?";
    public static final String updateCustomerBalance = "UPDATE CUSTOMERINFORMATION SET BALANCE = ? WHERE ID = ?";



    public static final String readAdminInformationFromDatabase = "SELECT * FROM ADMININFORMATION WHERE ID = ?";
    public static final String changeAdminPasswordInDatabase = "UPDATE ADMININFORMATION SET PASSWORD = ? WHERE ID = ? ";
    public static final  String adminUserNameAndPassword = "SELECT NAME, PASSWORD FROM ADMININFORMATION WHERE NAME = ?";




}
