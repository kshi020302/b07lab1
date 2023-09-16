public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		this.coefficients = new double[]{0};
	}

	public Polynomial(double[] arg_coefficients) {
		this.coefficients = arg_coefficients;
	}

	public Polynomial add(Polynomial new_poly) {
		int maxLength=Math.max(this.coefficients.length, new_poly.coefficients.length);
		double[] result = new double[maxLength];

		for(int i = 0; i< this.coefficients.length;i++) {
			result[i] += this.coefficients[i];
		}

		for(int i =0; i< new_poly.coefficients.length;i++) {
			result[i] += new_poly.coefficients[i];
		}

		return new Polynomial(result);
	}

	public double evaluate(double val) {
		double result = 0;

		for(int i = 0;i<this.coefficients.length;i++) {
			result += this.coefficients[i] * Math.pow(val, i);
		}

		return result;
	}

	public boolean hasRoot(double val) {
		return evaluate(val) == 0;
	}
}
