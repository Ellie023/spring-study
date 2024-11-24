import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Member_management { //회원 정보 관리
    public static void updateMember(int user_id, String email, String phone) {//회원 id로 이메일, 전화번호를 수정
        Connection connection = DatabaseConnection.getConnection();//데이터 베이스를 연결합니다.
        String sql = "UPDATE members SET email=?, phone_number=? where member_id=?";//sql updae query를 문자열로 정의
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(sql);//PreparedStatement 객체를 생성
            //query 각 매개변수에 값을 설정
            pstm.setString(1, email);
            pstm.setString(2, phone);
            pstm.setInt(3,user_id);
            int count = pstm.executeUpdate();//sql update query 실행
            if (count >0) { //업데이트 된 행의 수가 0보다 크면 실행 성공
                System.out.println("이메일과 전화번호가 변경되었습니다.");

            } else { //회원정보 수정 실패 
                System.out.println("회원 정보 수정에 실패했습니다.");
            }

            System.out.println("회원정보 수정 서비스를 종료합니다.");
            System.out.println("------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
