import java.util.Scanner;
import java.util.StringTokenizer;

public class FractionApp {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		StringTokenizer fract = new StringTokenizer(scnr.nextLine()); // "1/2 + 3" from user
		Fraction f1, f2, f3;
		
		createFraction(fract, f1);
		arithmatic(fract.nextToken());
		System.out.print(f1.toString() + " " + f2.toString() + " = " + f3.toString());
		
	}

	public static void createFraction(StringTokenizer st, Fraction fraction) {
		String [] array = st.nextToken.split("/");
		Fraction fraction = new Fraction(array[0], array[1]);
	}
	
	public void arithmatic(String op) {
		if (op == "+") {
			createFraction(fract, f2);
			f3 = f1.plus(f2);
		}
		else if(op == "-") {
			createFraction(fract, f2);
			f3 = f1.minus(f2);
		}
		else if (op == "x") {
			createFraction(fract, f2);
			f3 = f1.times(f2);
		}
		else if (op == "/") {
			createFraction(fract, f2);
			f3 = f1.divide(f2);
		}
	}
	
}
