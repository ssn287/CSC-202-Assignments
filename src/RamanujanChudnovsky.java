/**
 * RamanujanChudnovsky class approximates Pi with a given degree of accuracy
 *    @author Shelby Neal
 *    Emplid: 6030859
 *    Email: ssn287@email.vccs.edu
 *    Purpose: Programming Assignment #1
 */
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class RamanujanChudnovsky {
	//set precision to avoid non-terminating decimal expansion (maximum number of significant digits)
	private static final MathContext PREC = new MathContext(1000);
	//set precision for square root algorithm
	private static final BigDecimal SQRT_PREC = new BigDecimal(0.000000000000000000000000000001);
	//initialize common constants
	private static final BigDecimal NEG1 = new BigDecimal(-1);
	private static final BigDecimal ZERO = BigDecimal.ZERO;
	private static final BigDecimal ONE = BigDecimal.ONE;
	private static final BigDecimal TWO = new BigDecimal(2);
	private static final BigDecimal THREE = new BigDecimal(3);
	private static final BigDecimal FOUR = new BigDecimal(4);
	private static final BigDecimal SIX = new BigDecimal(6);
	private static final BigDecimal EIGHT = new BigDecimal(8);
	//fact method takes a BigDecimal and returns its factorial recursively
	public static BigDecimal fact(BigDecimal n) {
		if(n.compareTo(ZERO) == 0) {
			return ONE;
		}
		else {
			return n.multiply(fact(n.subtract(ONE)));
		}
	}
	//sqrt method takes a BigDecimal and returns its square root recursively
	//   we start with x = n, y = 1, e = precision limit
	public static BigDecimal sqrt(BigDecimal n, BigDecimal x, BigDecimal y, BigDecimal e) {
		//if the difference between x and y is within the precision limit, the square root is 1 (base case)
		if(x.subtract(y).compareTo(e) <= 0) {
			return x;
		}
		//otherwise, we get the next root approximation by making the new x the average of x and y,
		//   then we set y = n / x and continue until an exact root is found or we reach the precision limit
		else {
			x = x.add(y).divide(TWO, PREC);
			y = n.divide(x, PREC);
			return sqrt(n, x, y, e);
		}
	}
	//rama method uses Ramnujan's algorithm for approximating Pi recursively
	//   1/pi = (sqrt(8)/9801)*sum(((4n)!/(n!)^4)*((26390n+1103)/396^(4n)))
	public static BigDecimal rama(int n) {
		//initialize constants used in the formula
		BigDecimal bigN = new BigDecimal(n);
		BigDecimal ram1 = sqrt(EIGHT, EIGHT, ONE, SQRT_PREC);
		BigDecimal ram2 = new BigDecimal(9801);
		BigDecimal ram3 = new BigDecimal(26390);
		BigDecimal ram4 = new BigDecimal(1103);
		BigDecimal ram5 = new BigDecimal(396);
		//if n equals zero (base case) take the inverse of the right side of the formula to find Pi
		if(bigN.compareTo(ZERO) == 0) {
			return ONE.divide(fact(FOUR.multiply(bigN)).multiply(ram3.multiply(bigN).add(ram4))
					.divide(fact(bigN).pow(4).multiply(ram5.pow(4*n)), PREC)
					.multiply(ram1).divide(ram2, PREC), PREC);
		}
		//if n does not equal zero, recursively compute the sum from the right side of the formula
		else {
			return fact(FOUR.multiply(bigN)).multiply(ram3.multiply(bigN).add(ram4))
					.divide(fact(bigN).pow(4).multiply(ram5.pow(4*n)), PREC)
					.multiply(ram1).divide(ram2, PREC).add(rama(n - 1));
		}
	}
	//chud method uses Chudnovsky's algorithm for approximating Pi recursively
	//   1/pi = 1/(53360*sqrt(640320))*sum(((-1)^n)*(6n)!/(((n!)^3)*(3n)!)*(13591409+545140134n)/(640320)^(3n))
	public static BigDecimal chud(int n) {
		//initialize constants used in the formula
		BigDecimal bigN = new BigDecimal(n);
		BigDecimal chu1 = new BigDecimal(53360);
		BigDecimal chu3 = new BigDecimal(13591409);
		BigDecimal chu4 = new BigDecimal(545140134);
		BigDecimal chu5 = new BigDecimal(640320);
		BigDecimal chu2 = sqrt(chu5, chu5, ONE, SQRT_PREC);
		//initialize constants recommended for implementations
		BigDecimal imp1 = new BigDecimal(100100025);
		BigDecimal imp2 = new BigDecimal(327843840);
		//if n equals zero (base case) take the inverse of the right side of the formula to find Pi
		if(bigN.compareTo(ZERO) == 0) {
			return ONE.divide(NEG1.pow(n).multiply(fact(SIX.multiply(bigN)).multiply(chu3.add(chu4.multiply(bigN))))
					.divide(fact(bigN).pow(3).multiply(fact(THREE.multiply(bigN)).multiply((EIGHT.multiply(imp1).multiply(imp2)).pow(n))), PREC)
					.divide(chu1.multiply(chu2), PREC), PREC);
		}
		//if n does not equal zero, recursively compute the sum from the right side of the formula
		else {
			return NEG1.pow(n).multiply(fact(SIX.multiply(bigN)).multiply(chu3.add(chu4.multiply(bigN))))
					.divide(fact(bigN).pow(3).multiply(fact(THREE.multiply(bigN)).multiply((EIGHT.multiply(imp1).multiply(imp2)).pow(n))), PREC)
					.divide(chu1.multiply(chu2), PREC).add(chud(n - 1));
		}
	}
	public static void main(String[] args) {
		Scanner kybd = new Scanner(System.in);
		while(true) {
			System.out.println("\nEnter an Integer Number (e.g. 13): ");
			int num = kybd.nextInt();
			System.out.println("\nUsing Ramanujan's Formula\n\tThe Value of Pi up to " + num + " is\n\n" + rama(num));
			System.out.println("\nUsing Chudnovsky's Formula\n\tThe Value of Pi up to " + num + " is\n\n" + chud(num));
		}
	}
}