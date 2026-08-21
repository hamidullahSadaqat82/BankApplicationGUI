package Logic;
import Database.Queries;
import Database.SqlConnection;
import java.sql.*;

public class RelatedCustomer {

    private final Connection sql = SqlConnection.sqlConn();

    public int addCustomerAccount (int id, String tid, String name, String fName, String phone,
                                   String email, String password, String contry, String provence, String street) {
        int EntryResult;
        String encryptPassword = "";
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            encryptPassword += (char) (ch + 894323456);
        }
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.sendCustomerInformationToDatabase);
            pst.setInt(1, id);
            pst.setString(2, tid);
            pst.setString(3, name);
            pst.setString(4, fName);
            pst.setString(5, phone);
            pst.setString(6, email);
            pst.setString(7, encryptPassword);
            pst.setString(8, contry);
            pst.setString(9, provence);
            pst.setString(10, street);
            EntryResult = pst.executeUpdate();
        }  catch (SQLException exception) {
            System.out.println(exception.getMessage());
            long errorCode = exception.getErrorCode();
            if (errorCode == 1) {
                return 1;
            } else {
                return 2;
            }
        }

        if (EntryResult != 0) {
            return 10;
        }
        else {
            return 3;
        }
    }

    public boolean DeleteCustomerAccount (int id) {
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.deleteCustomerInformationFromDatabase);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            pst.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.printf("From Delete manager account: %s", exception.getMessage());
        }
        return result > 0;
    }

    public StringBuilder ReadCustomerInformation (int id) {
        try {
            String cid = "";
            String tid = "";
            String name = "";
            String fName = "";
            String phone = "";
            String email = "";
            String password = "";
            String contry = "";
            String provence = "";
            String street = "";
            String balance = "";

            PreparedStatement pst = sql.prepareStatement(Queries.readCustomerInformationFromDatabase);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                cid = rs.getString("ID");
                tid = rs.getString("TID");
                name = rs.getString("NAME");
                fName = rs.getString("FNAME");
                phone = rs.getString("PHONE");
                email = rs.getString("EMAIL");
                password = rs.getString("PASSWORD");
                contry = rs.getString("CONTRY");
                provence = rs.getString("PROVENCE");
                street = rs.getString("STREET");
                balance = String.valueOf(rs.getDouble("BALANCE"));
            }

            StringBuilder information = new StringBuilder("Manager id: " + cid).append("\n");
            information.append("TID: ").append(tid).append("\n");
            information.append("Name: ").append(name).append("\n");
            information.append("Father's name: ").append(fName).append("\n");
            information.append("Phone: ").append(phone).append("\n");
            information.append("Email: ").append(email).append("\n");
            information.append("Password: ").append(password).append("\n");
            information.append("Contry: ").append(contry).append("\n");
            information.append("Provence: ").append(provence).append("\n");
            information.append("Street: ").append(street).append("\n");
            information.append("Balance: ").append(balance).append("\n");

            if (!cid.equalsIgnoreCase("")) {
                return information;
            }
            pst.close();
            rs.close();
            sql.close();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return new StringBuilder("Null");
    }

    public boolean ChangeCustomerPassword (int id, String newPassword) {
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.changeCustomerPasswordInDatabase);
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

    public boolean addCustomerBalance (int id, double balance) {
        int result = 0;
        double oldBalance = 0;
        double newBalance = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.readCustomerInformationFromDatabase);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                oldBalance = rs.getDouble("BALANCE");
            }
                newBalance += oldBalance + balance;
            pst = sql.prepareStatement(Queries.addCustomerBalance);
            pst.setDouble(1, newBalance);
            pst.setInt(2, id);
            result = pst.executeUpdate();
            pst.close();
            rs.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return result > 0;
    }

    public double checkCustomerBalance (int id) {
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.readCustomerInformationFromDatabase);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                return rs.getDouble("BALANCE");
            } else {
                return 00000.0123456123456D;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return 0.0;
    }

    public  double depositBalance (int id, double balance) {
        double oldBalance;
        double newBalance = 0.0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.readCustomerInformationFromDatabase);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                oldBalance = rs.getDouble("BALANCE");
                newBalance += balance;
                newBalance += oldBalance;
                pst = sql.prepareStatement(Queries.updateCustomerBalance);
                pst.setDouble(1, newBalance);
                pst.setInt(2, id);
                int result = pst.executeUpdate();
                if (result > 0) {
                    return newBalance;
                } else {
                    return 00000000000.123321123d;
                }
            }
            pst.close();
            rs.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return 0.0d;
    }

    public double withdrawBalance (int id, double balance) {
        double oldBalance;
        double newBalance = 0;
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.readCustomerInformationFromDatabase);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                oldBalance = rs.getDouble("BALANCE");
                if (oldBalance < balance) {
                    return 0.0001;
                } else {
                   newBalance = oldBalance;
                   newBalance -= balance;
                   pst = sql.prepareStatement(Queries.updateCustomerBalance);
                   pst.setDouble(1, newBalance);
                   pst.setInt(2, id);
                   result  = pst.executeUpdate();
                }
            }
            pst.close();
            rs.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        if (result > 0) {
            return newBalance;
        } else {
            return 0.0002;
        }
    }

    public double transferMoney (int senderId, int resaverId, double balance){
        double senderOldBalance = 0.0;
        double resaverOldBalance = 0.0d;
        int ResaverResult;
        int senderResult = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.readManagerInformationFromDatabase);
            pst.setInt(1, senderId);
            ResultSet SenderResultset = pst.executeQuery();
            if (SenderResultset.next()) {
                senderOldBalance = SenderResultset.getDouble("BALANCE");
                if (senderOldBalance < balance) {
                    return 0.0001;
                } else {
                    pst = sql.prepareStatement(Queries.readCustomerInformationFromDatabase);
                    pst.setInt(1, resaverId);
                    ResultSet ResaverData = pst.executeQuery();
                    if (ResaverData.next()) {
                        resaverOldBalance = ResaverData.getDouble("BALANCE");
                        resaverOldBalance += balance;
                        pst = sql.prepareStatement(Queries.addCustomerBalance);
                        pst.setDouble(1, resaverOldBalance);
                        pst.setInt(2, resaverId);
                        ResaverResult = pst.executeUpdate();
                        if (ResaverResult > 0) {
                            senderOldBalance -= balance;
                            pst = sql.prepareStatement(Queries.addCustomerBalance);
                            pst.setDouble(1, senderOldBalance);
                            pst.setInt(2, senderId);
                            senderResult = pst.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        if (senderResult != 0) {
            return senderOldBalance;
        } else {
            return 0.0002;
        }
    }
}
