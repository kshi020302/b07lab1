import java.io.IOException;

public class Driver {
	public static void main(String [] args) throws IOException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c11 = {6,5};
		int [] c12 = {0,3};
		Polynomial p1 = new Polynomial(c11,c12);
		double [] c21 = {-2,-9};
		int [] c22 = {1,4};
		Polynomial p2 = new Polynomial(c21,c22);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
		System.out.println("1 is a root of sss");
		else
		System.out.println("1 is not a root of sss");
		Polynomial poly = new Polynomial(new double[]{5, -3, 7}, new int[]{0, 2, 8});
		try {
		    poly.saveToFile("polynomial.txt");
		    System.out.println("Polynomial saved to polynomial.txt");
		} catch (IOException e) {
		    System.err.println("Error saving the polynomial to the file.");
		}

}
}