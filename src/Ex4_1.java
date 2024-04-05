import java.util.Scanner;

public class Ex4_1 {
    class Rectangle {
        int width;
        int height;

        public int getArea() {
            return width * height;
        }
    }

    public static void main(String[] args) {
        Ex4_1 Ex4_1 = new Ex4_1();
        Ex4_1.Rectangle rect = Ex4_1.new Rectangle();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">> ");
        rect.width = scanner.nextInt();
        rect.height = scanner.nextInt();
        System.out.println("사각형의 면적은 " + rect.getArea());

        scanner.close();
    }
}