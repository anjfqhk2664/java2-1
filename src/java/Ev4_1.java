
public class Ev4_1 {
    int radius;	
	String name; 

	public double getArea() { 
		return 3.14*radius*radius;
	}

	public static void main(String[] args) {
		Ev4_1
	 pizza; 
		pizza = new Ev4_1
	(); 
		pizza.radius = 10; 
		pizza.name = "자바피자"; 
		double area = pizza.getArea();
		System.out.println(pizza.name + "의 면적은 " + area);
		
		Ev4_1
	 donut = new Ev4_1
	(); 
		donut.radius = 2; 
		donut.name = "자바도넛"; 
		area = donut.getArea();
		System.out.println(donut.name + "의 면적은 " + area);
	}

    
}
