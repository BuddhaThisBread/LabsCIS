/*
 	
 */
public class Fraction {
	private int Numerator;
	private int Denominator;
	
	public Fraction(int Numerator, int Denominator) {
		this.Numerator = Numerator;
		this.Denominator = Denominator;
	}
	
	public Fraction(int Numerator) {
		this.Numerator = Numerator;
		this.Denominator = 1;
	}
	
	public Fraction() {
		this.Numerator = 1;
		this.Denominator = 1;
	}
	
	public String toString() {
		StringBuilder fractize = new StringBuilder();
		String num, den;
		
		num = Integer.toString(Numerator);
		den = Integer.toString(Denominator);
		fractize.append(num + "/" + den);
		
		return fractize.toString();
	}
	 public boolean equals(Fraction f) {
		 if (this.Numerator == f.Numerator && this.Denominator == f.Denominator) {
			 return true; // true if the num/denum of two reduced fractions are equal
		 }
		 else { return false; }
	 }
	 
	 public Fraction plus(Fraction f) {
		  // adds 2 fractions / returns reduced fractions result
		 
		 return f;
	 }
	 
	 public Fraction minus(Fraction f) {
		  // subtracts 2 fractions / returns reduced fractions result
		 return f;
	 }
	 
	 public Fraction times(Fraction f) {
		  // multiplies 2 fractions / returns reduced fraction result 
		 return f;
	 }
	 
	 public Fraction divide(Fraction f) {
		  // adds 2 fractions / returns reduced fraction result
		 return f;
	 }
	 
	 private void reduce() {
		 
		 int cnt = 1;
		 while (Numerator > Denominator) { // whole numbers 
			 cnt++;
			 Numerator -= Denominator;
		 }
		 if (cnt > 1) {
			 if (Numerator == Denominator) { // e.g. 3/1 or 5/1 
				 Numerator = 1 * cnt;
				 Denominator = 1;
			 }
			 else if(Denominator % Numerator == 1) {
				 // do nothing
			 }
			 
		 }
		 else if (Denominator % Numerator == 0 && (Numerator >= 2)) { // e.g. 3/6 -> 1/2 	
			 Numerator = 1;
			 Denominator /= Numerator;
		 }
		 
		 
			 
	 }
	 
}
