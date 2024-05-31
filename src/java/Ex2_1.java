import java.util.Scanner;

public class Ex2_1 {

    public static void main(String argsp[]){
        System.err.println("이름, 도시, 나이, 체중, 독신 여부를 빈칸으로 분리하여 입력하세요.");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.err.println("당신의 이름은 " + name + "입니다");
        int age = scanner.nextInt();
        System.err.println("당신의 나이는 " + age + "입니다");
        

    }

}