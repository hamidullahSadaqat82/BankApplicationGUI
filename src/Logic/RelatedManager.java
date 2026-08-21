package Logic;
import Database.Queries;
import Database.SqlConnection;
import java.sql.*;

public class RelatedManager {

    private final Connection sql = SqlConnection.sqlConn();

    public int addManagerAccount (int id, String tid, String name, String fName, String phone,
                                   String password, String contry, String provence, String street) {
        String encryptPassword = "";
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            encryptPassword += (char) (ch + 894323456);
        }
        try {

            PreparedStatement pst = sql.prepareStatement(Queries.sendManagerInformationToDatabase);
            pst.setInt(1, id);
            pst.setString(2, tid);
            pst.setString(3, name);
            pst.setString(4, fName);
            pst.setString(5, phone);
            pst.setString(6, encryptPassword);
            pst.setString(7, contry);
            pst.setString(8, provence);
            pst.setString(9, street);
            int asd = pst.executeUpdate();
            if (asd != 0) {
                return 0;
            }
        } catch (SQLException exception) {
            long errorCode = exception.getErrorCode();
            if (errorCode == 1) {
                return 1;
            } else {
                return 2;
            }
        }
        return 3;
    }

    public int DeleteManagerAccount (int id) {
        int result = 0;
        try {
            PreparedStatement pst = sql.prepareStatement(Queries.deleteManagerInformationFromDatabase);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            pst.close();
            sql.close();
        } catch (SQLException exception) {
            System.out.printf("From Delete manager account: %s", exception.getMessage());
        }
        if (result > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public StringBuilder ReadManagerInformation (int id) {
        try {
            String mid = "";
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
                mid = rs.getString("ID");
                tid = rs.getString("TID");
                name = rs.getString("NAME");
                fName = rs.getString("FNAME");
                phone = rs.getString("PHONE");
                password = rs.getString("PASSWORD");
                contry = rs.getString("CONTRY");
                provence = rs.getString("PROVENCE");
                street = rs.getString("STREET");
            }
            StringBuilder information = new StringBuilder("Manager id: " + mid).append("\n");
            information.append("TID: ").append(tid).append("\n");
            information.append("Name: ").append(name).append("\n");
            information.append("Father's name: ").append(fName).append("\n");
            information.append("Phone: ").append(phone).append("\n");
            information.append("Password: ").append(password).append("\n");
            information.append("Contry: ").append(contry).append("\n");
            information.append("Provence: ").append(provence).append("\n");
            information.append("Street: ").append(street).append("\n");
            if (!mid.equalsIgnoreCase("")){
                return information;
            }
            pst.close();
            rs.close();
            sql.close();
        } catch (Exception exception) {
            System.out.printf("Read manager information method in Related manager class: %s", exception.getMessage());
        }
        return new StringBuilder("Null");
    }

    public boolean ChangeManagerPassword (int id, String newPassword) {
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

}
