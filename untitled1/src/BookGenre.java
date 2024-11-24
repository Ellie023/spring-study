import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookGenre {
    public static void numberOfBooksByGenre(String genre) {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        Connection connection = DatabaseConnection.getConnection(); // 데이터베이스 연결
        try {
            // SQL 쿼리문 : 책 테이블에서 종류 속성으로 그룹짓고, 사용자가 입력한 종류를 받아 해당 종류의 책들의 genre 값과 총 책의 개수 반환
            String sql = "SELECT genre, count(genre) AS total_number FROM books GROUP BY genre having genre=?";

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, genre); // 사용자 입력을 미리 작성해둔 쿼리문 ? 표시된 곳에 넣어 저장

            // 쿼리 실행하고 ResultSet 객체를 사용해 결과 처리
            resultSet = pstm.executeQuery();

            // 결과가 존재하면 장르와 해당 장르의 책 수를 출력하고 , 존재하지 않으면 존재하지 않음 메시지 출력
            if (resultSet.next()) {
                String bookGenre = resultSet.getString("genre");
                int totalNumber = resultSet.getInt("total_number");
                System.out.println(bookGenre + " 종류의 책 보유 권수 : " + totalNumber + "권");
            }
            else {
                System.out.println("존재하지 않는 종류입니다.");
            }

            // 사용한 객체를 닫아 자원을 해제함
            resultSet.close();
            pstm.close();
            connection.close();
        }
        // 예외 처리
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
