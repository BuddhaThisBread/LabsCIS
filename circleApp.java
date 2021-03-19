import java.util.Scanner;

public class circleApp {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		Circle circle1 = new Circle();
		
		System.out.print("Please enter a radius and color: ");
		double radius;
		String color;
		radius = scnr.nextInt();
		color = scnr.nextLine();
		
		Circle circle2 = new Circle(radius, color);

	}

}
