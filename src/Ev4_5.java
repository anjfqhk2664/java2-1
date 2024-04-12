
public class Ev4_5 {
    String title;
	String author;

	void show() { System.out.println(title + " " + author); }
	
	public Ev4_5() {
		this("배가", "고프네요");
		System.out.println("생성자 호출됨");
	}

	public Ev4_5(String title) {
		this(title, "작자미상");
	}
	
	public Ev4_5(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public static void main(String [] args) {
		Ev4_5 littlePrince = new Ev4_5("어린왕자", "생텍쥐페리");
		Ev4_5 loveStory = new Ev4_5("춘향전");
		Ev4_5 emptyEv4_5 = new Ev4_5();		
		loveStory.show();
        littlePrince.show();
        emptyEv4_5.show();
	}

    
}
