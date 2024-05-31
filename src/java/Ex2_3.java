public class Ex2_3 {
    public static void main(String[] args) {
        for(int i=1; i<10; i++){
            for(int j=1; j<10; j++){
                System.err.println(i + "x" + j + "=" + i*j);
                System.err.println('\t');
            }
            System.err.println();
        }
    }
}
