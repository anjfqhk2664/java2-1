public class Ex4_2 {
    String title;
    String author;

    public Ex4_2(String t) {
        title = t; author = "작자미상";
    }

    public Ex4_2(String t, String a) {
        title = t; author = a;
    }

    public static void main(String[] args) {
        Ex4_2 littlePrince = new Ex4_2("어린왕자", "생덱쥐페리");
        Ex4_2 loveStory = new Ex4_2("춘향전");
        System.err.println(littlePrince + " " + loveStory);
        System.err.println(loveStory + " " + loveStory.author);
    }
}
