import java.util.Scanner;

public class Ex2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.err.println("정수를 입력하세요");
        int time = scanner.nextInt();
        int second = time % 60;
        int minute = (time/60) % 60;
        int hour = (time/60) /60;

        System.err.print(time + "초는 ");
        System.err.print(second + "초 ");
        System.err.print(minute + "분 ");
        System.err.print(hour + "시간 ");

    }
}
