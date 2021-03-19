

public class Circle {
	
	private String color;
	private double radius;
	private final double PI = 3.14159;
	
	public Circle() {
		radius = 1.0;
		color = "white";
	}
	
	public Circle(double radius, String color) {
		this.radius = radius;
		this.color = color;
	}
	
	// find area 
	public double findArea(double r) {
		double area;
		area = PI * r * r;
		
		return area;
	}
	
	// find circumference
	public double findCurcumference(double r) {
		double circ;
		circ = PI * 2 * r;
		return circ;
	}
	
	// find surface area
	public double findSurfaceArea(double r) {
		double surfaceArea;
		surfaceArea = 4 * PI * r * r;
		return surfaceArea;
	}
	
	// find volume
	public double findVolume(double r) {
		double volume;
		volume = (4/3) * PI * r * r * r;
		return volume;
	}
	
	// get methods
	public String getColor() {
		return color;
	}
	
	public double getRadius() {
		return radius;
	}
	
	// set methods 
	public void setColor(String c) {
		color = c;
	}
	
	public void setRadius(double r) {
		radius = r;
	}

}
