
public class Ev4_4 {
    String title;
	String author;
	
	public Ev4_4(String t) {
		title = t;
		author = "작자미상";
	}
	
	public Ev4_4(String t, String a) { 
		title = t;
		author = a;
	}
	
	public static void main(String [] args) {
		Ev4_4 littlePrince = new Ev4_4("어린왕자", "생텍쥐페리");
		Ev4_4 loveStory = new Ev4_4("춘향전"); 
		System.out.println(littlePrince.title + " " + littlePrince.author);
        System.out.println(loveStory.title + " " + loveStory.author);
	}
    
}
