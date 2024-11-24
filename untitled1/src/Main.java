import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("------------------------------------------");
            System.out.println("Library Management System");
            System.out.println("1: 종류별 책 보유 현황 보기");
            System.out.println("2: 책 대출/예약");
            System.out.println("3: 책 반납");
            System.out.println("4: 사용자별 대출 현황");
            System.out.println("5: 회원 정보 수정");
            System.out.println("6: 종료");
            System.out.print("메뉴를 선택하세요: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("[[종류별 책 보유 현황 보기]]");
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.print("책 종류를 입력하세요: ");
                    String genre = scanner1.next();// 종류 입력하기
                    BookGenre.numberOfBooksByGenre(genre);// select menu를 이용한 종류별 책 보유 현황 함수 호출
                    break;
                case 2:
                    System.out.println("[[책 대출/예약하기]]");
                    Scanner scanner2 = new Scanner(System.in);
                    int user_id2;
                    String book_title2;
                    while (true) {
                        System.out.print("회원의 ID를 입력하세요: ");
                        user_id2 = scanner2.nextInt();// 대출자의 회원 id 입력
                        scanner2.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id2)) {// input 값 유효성 검사, 회원이 아니면 다시 입력
                            break;
                        } else {
                            System.out.println("존재하지 않는 회원입니다. 회원의 ID를 다시 입력하세요.");
                        }
                    }
                    while (true) {
                        System.out.print("대출할 책의 제목을 입력하세요: ");
                        book_title2 = scanner2.nextLine(); // 대출 할 책의 제목 입력
                        if (InputValidator.is_vaild_Booktitle(book_title2)) {
                            break;// input 값 유효성 검사, 도서관에서 보유한 책이 아니면 다시 입력
                        } else {
                            System.out.println("책이 존재하지 않습니다. 책의 제목을 다시 입력하세요. ");
                        }
                    }
                    BorrowManager.borrowBook(user_id2, book_title2);// insert,delete meun를 활용한 대출/예약 함수 호출
                    break;
                case 3:
                    System.out.println("[[책 반납하기]]");
                    Scanner scanner3 = new Scanner(System.in);
                    int user_id3;
                    String book_title3;
                    while (true) {
                        System.out.print("반납을 하는 회원의 ID를 입력하세요: ");// 반납자의 회원 id 입력
                        user_id3 = scanner3.nextInt();
                        scanner3.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id3)) { // input 값 유효성 검사, 회원이 아니면 다시 입력
                            break;
                        } else {
                            System.out.println("존재하지 않는 회원입니다. 회원의 ID를 다시 입력하세요.");
                        }
                    }
                    System.out.print("반납 할 책의 제목을 입력하세요: ");
                    book_title3 = scanner3.nextLine(); // 책 제목 입력
                    ReturnManager.returnBook(user_id3, book_title3);// delete menu를 활용한 반납 함수 호출
                    break;
                case 4:
                    System.out.println("[[사용자별 대출 현황]]");
                    Scanner scanner4 = new Scanner(System.in);
                    int user_id4;
                    while (true) {
                        System.out.print("회원의 ID를 입력하세요: ");
                        user_id4 = scanner4.nextInt(); // 알아볼 회원의 id 입력
                        scanner4.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id4)) {// input 값 유효성 검사, 회원이 아니면 다시 입력
                            break;
                        } else {
                            System.out.println("존재하지 않는 회원의 ID입니다. 다시 입력하세요.");
                        }
                    }
                    select1.printBorrowedBooks(user_id4);// select menu를 활용한 사용자별 대출 현환 함수 호출

                    break;
                case 5:
                    System.out.println("[[회원 정보 수정하기]]");
                    Scanner scanner5 = new Scanner(System.in);
                    int user_id5;
                    String email, phone;
                    while (true) {
                        System.out.print("수정 할 회원의 ID를 입력하세요: ");
                        user_id5 = scanner5.nextInt(); // 회원의 id 입력
                        scanner5.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id5)) {// input 값 유효성 검사, 회원이 아니면 다시 입력
                            break;
                        } else {
                            System.out.println("존재하지 않는 회원의 ID입니다. 다시 입력하세요.");
                        }
                    }
                    System.out.print("새로운 이메일 주소를 입력하세요: ");
                    email = scanner5.next();// 이메일 입력
                    System.out.print("새로운 전화번호를 입력하세요(000-1111-2222): ");
                    phone = scanner5.next(); // 전화번호 입력
                    Member_management.updateMember(user_id5, email, phone); // update menu를 이용한 회원정보 수정 함수
                    break;
                case 6:// 프로그램 종료
                    System.out.println("안녕히가십시오.");
                    break;
                default:
                    System.out.println("존재하지 않는 메뉴입니다. 다시 선택하세요.");
            }
            System.out.println("------------------------------------------");
        } while (choice != 6);
        sc.close();
    }
}
