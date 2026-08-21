package Logic;
import Database.Queries;
import Database.SqlConnection;
import java.sql.*;

public class AllControls {
    private final Connection sql = SqlConnection.sqlConn();

    public boolean changeAdminPassword (int id, String newPassword) {
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.changeAdminPasswordInDatabase);
            pst.setString(1, newPassword);
            pst.setInt(2, id);
            result = pst.executeUpdate();
            pst.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return result > 0;
    }

    public StringBuilder readAdminInformation (int id) {
        try {
            String Aid = "";
            String tid = "";
            String name = "";
            String fName = "";
            String phone = "";
            String password = "";
            String contry = "";
            String provence = "";
            String street = "";

            PreparedStatement pst = sql.prepareStatement(Queries.readAdminInformationFromDatabase);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Aid = rs.getString("ID");
                tid = rs.getString("TID");
                name = rs.getString("NAME");
                fName = rs.getString("FNAME");
                phone = rs.getString("PHONE");
                password = rs.getString("PASSWORD");
                contry = rs.getString("CONTRY");
                provence = rs.getString("PROVENCE");
                street = rs.getString("STREET");
            }
            StringBuilder information = new StringBuilder("Manager id: " + Aid).append("\n");
            information.append("TID: ").append(tid).append("\n");
            information.append("Name: ").append(name).append("\n");
            information.append("Father's name: ").append(fName).append("\n");
            information.append("Phone: ").append(phone).append("\n");
            information.append("Password: ").append(password).append("\n");
            information.append("Contry: ").append(contry).append("\n");
            information.append("Provence: ").append(provence).append("\n");
            information.append("Street: ").append(street).append("\n");
            if (!Aid.equalsIgnoreCase("")) {
                return information;
            }
            pst.close();
            rs.close();
            sql.close();
        } catch (Exception exception) {
            System.out.printf("%s", exception.getMessage());
        }
        return new StringBuilder("Null");
    }

    public boolean AdminPasswordChakerForLogin (String userName, String password) {
        String adminUserNameFromDatabase = "";
        String adminPasswordFromDatabase = "";
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.adminUserNameAndPassword);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    adminUserNameFromDatabase = rs.getString("NAME");
                    adminPasswordFromDatabase = rs.getString("PASSWORD");
                }
            } catch (NullPointerException exception) {
                System.out.println("Password or name field is empty.");
            }
            pst.close();
            rs.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println("debugging: " + exception.getMessage());
        }
        return adminUserNameFromDatabase.equals(userName) && adminPasswordFromDatabase.equals(password);
    }

    public boolean AdminPasswordForget (String password) {
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.changeAdminPasswordInDatabase);
            pst.setString(1, password);
            pst.setInt(2, 100);
            result = pst.executeUpdate();
            pst.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return result > 0;
    }

    public boolean managerPasswordChakerForLogin (int ID, String userName, String password) {
        String userNameFromDatabase = "";
        String passwordFromDatabase = "";
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.readManagerInformationFromDatabase);
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                userNameFromDatabase = rs.getString("NAME");
                passwordFromDatabase = rs.getString("PASSWORD");
            }
            pst.close();
            rs.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return userName.equals(userNameFromDatabase) && password.equals(passwordFromDatabase);
    }

    public StringBuilder readManagerInformation (int id) {
        try {
            String Aid = "";
            String tid = "";
            String name = "";
            String fName = "";
            String phone = "";
            String password = "";
            String contry = "";
            String provence = "";
            String street = "";

            PreparedStatement pst = sql.prepareStatement(Queries.readManagerInformationFromDatabase);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Aid = rs.getString("ID");
                tid = rs.getString("TID");
                name = rs.getString("NAME");
                fName = rs.getString("FNAME");
                phone = rs.getString("PHONE");
                password = rs.getString("PASSWORD");
                contry = rs.getString("CONTRY");
                provence = rs.getString("PROVENCE");
                street = rs.getString("STREET");
            }
            StringBuilder information = new StringBuilder("Manager id: " + Aid).append("\n");
            information.append("TID: ").append(tid).append("\n");
            information.append("Name: ").append(name).append("\n");
            information.append("Father's name: ").append(fName).append("\n");
            information.append("Phone: ").append(phone).append("\n");
            information.append("Password: ").append(password).append("\n");
            information.append("Contry: ").append(contry).append("\n");
            information.append("Provence: ").append(provence).append("\n");
            information.append("Street: ").append(street).append("\n");
            if (!Aid.isEmpty()) {
                return information;
            }
            pst.close();
            rs.close();
            sql.close();
        } catch (Exception exception) {
            System.out.printf("123456789: %s", exception.getMessage());
        }
        return new StringBuilder("Null");
    }

    public boolean changeManagerPassword (int id, String newPassword) {
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.changeManagerPasswordInDatabase);
            pst.setString(1, newPassword);
            pst.setInt(2, id);
            result = pst.executeUpdate();
            pst.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return result > 0;
    }

    public boolean ManagerPasswordForget (int id, String password) {
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.changeManagerPasswordInDatabase);
            pst.setString(1, password);
            pst.setInt(2, id);
            result = pst.executeUpdate();
            pst.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return  result > 0;
    }

    public boolean CustomerPasswordChakerForLogin (int ID, String userName, String password) {
        String userNameFromDatabase = "";
        String passwordFromDatabase = "";
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.readCustomerInformationFromDatabase);
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                userNameFromDatabase = rs.getString("NAME");
                passwordFromDatabase = rs.getString("PASSWORD");
            }
            pst.close();
            rs.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return userName.equals(userNameFromDatabase) && password.equals(passwordFromDatabase);
    }

    public boolean ForgetCustomerPassword(int id, String password) {
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.changeCustomerPasswordInDatabase);
            pst.setString(1, password);
            pst.setInt(2, id);
            pst.close();
            sql.close();
            result = pst.executeUpdate();
            pst.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return result > 0;
    }


}
