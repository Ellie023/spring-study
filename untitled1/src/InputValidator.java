import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InputValidator {//input 값 유효성 검사, table에 없으면 false return

    public static boolean is_Valid_Memberid(int member_id){ //member_id가 members table에 있는 지 확인

        String checkSQL="SELECT member_id from Members where member_id=?";
        try (Connection con =DatabaseConnection.getConnection();
             PreparedStatement pstmt =con.prepareStatement(checkSQL)){
            pstmt.setInt(1, member_id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); //존재하면 true return

        } catch (SQLException e) {
            e.printStackTrace();
            return false; //없으면 false return
        }
    }
    public static boolean is_vaild_Booktitle(String title){//책 title이 books table에 있는 지 확인
        String checkSQL="SELECT title from Books where title=?";
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(checkSQL)){
            pstmt.setString(1,title);
            ResultSet rs=pstmt.executeQuery();
            return rs.next();//존재하면 true return
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;// 졵배하지 않으면 false return
        }
    }
}
