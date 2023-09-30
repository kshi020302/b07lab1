import java.io.*;

public class Polynomial {
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		this.coefficients = new double[]{0};
		this.exponents = new int[] {0};
	}

	public Polynomial(double[] arg_coefficients, int[] arg_expos) {
		this.coefficients = arg_coefficients;
		this.exponents = arg_expos;
	}
	
    public Polynomial(File file) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            
            if (line != null) {
                parsePolyFromString(line);
            } else {
                throw new IOException("Empty file.");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    
    private void parsePolyFromString(String input) {
        String[] terms = input.split("(?=[+-])"); 
        int termCount = terms.length;

        coefficients = new double[termCount];
        exponents = new int[termCount];

        for (int i = 0; i < termCount; i++) {
            String term = terms[i].trim();
            
            if (term.isEmpty()) {
                continue;
            }

            String[] parts = term.split("x");
            double coefficient = 0;
            int exponent = 0;

            if (parts.length > 0) {
                coefficient = Double.parseDouble(parts[0]);
            }

            if (parts.length > 1) {
                String exponentStr = parts[1].replace("^", "").trim();
                if (!exponentStr.isEmpty()) {
                    exponent = Integer.parseInt(exponentStr);
                }
            }

            coefficients[i] = coefficient;
            exponents[i] = exponent;
        }
    }
    
    public void saveToFile(String fileName) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(toString()); 
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

	public Polynomial add(Polynomial new_poly) {
		int maxLength=Math.max(this.exponents[this.exponents.length - 1], new_poly.exponents[new_poly.exponents.length - 1]) + 1;
		double[] result = new double[maxLength];

		for(int i = 0; i< this.coefficients.length;i++) {
			result[this.exponents[i]] += this.coefficients[i];
		}

		for(int i =0; i< new_poly.coefficients.length;i++) {
			result[new_poly.exponents[i]] += new_poly.coefficients[i];
		}
		
		int[] resultExponents = new int[maxLength];
	    for (int i = 0; i < maxLength; i++) {
	        resultExponents[i] = i;
	    }

		return new Polynomial(result, resultExponents);
	}

	public double evaluate(double val) {
		double result = 0;

		for(int i = 0;i<this.coefficients.length;i++) {
			result += this.coefficients[i] * Math.pow(val, exponents[i]);
		}

		return result;
	}

	public boolean hasRoot(double val) {
		return evaluate(val) == 0;
	}
	
	public Polynomial multiply(Polynomial new_poly) {
	    int maxExponent = this.exponents[this.exponents.length - 1] + new_poly.exponents[new_poly.exponents.length - 1];

	    double[] resultCoefficients = new double[maxExponent + 1];

	    for (int i = 0; i < this.coefficients.length; i++) {
	        for (int j = 0; j < new_poly.coefficients.length; j++) {
	            int resultExponent = this.exponents[i] + new_poly.exponents[j];
	            resultCoefficients[resultExponent] += this.coefficients[i] * new_poly.coefficients[j];
	        }
	    }

	    int[] resultExponents = new int[maxExponent + 1];
	    for (int i = 0; i <= maxExponent; i++) {
	        resultExponents[i] = i;
	    }

	    return new Polynomial(resultCoefficients, resultExponents);
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < coefficients.length; i++) {
            double coefficient = coefficients[i];
            int exponent = exponents[i];

            if (coefficient != 0) {
                if (sb.length() > 0) {
                    sb.append(coefficient >= 0 ? " + " : " - ");
                } else if (coefficient < 0) {
                    sb.append("-");
                }

                coefficient = Math.abs(coefficient);
                sb.append(coefficient);

                if (exponent > 0) {
                    sb.append("x");

                    if (exponent > 1) {
                        sb.append("^").append(exponent);
                    }
                }
            }
        }

        return sb.toString();
    }
}
