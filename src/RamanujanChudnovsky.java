import java.math.BigDecimal;
import java.math.MathContext;

public class RamanujanChudnovsky {
	private static final MathContext PREC = new MathContext(10000);
	private static final BigDecimal SQRT_PREC = new BigDecimal(0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001);
	private static final BigDecimal ZERO = BigDecimal.ZERO;
	private static final BigDecimal ONE = BigDecimal.ONE;
	private static final BigDecimal TWO = new BigDecimal(2);
	private static final BigDecimal FOUR = new BigDecimal(4);
	private static final BigDecimal EIGHT = new BigDecimal(8);
	public static BigDecimal fact(BigDecimal n) {
		if(n.compareTo(ZERO) == 0) {
			return ONE;
		}
		else {
			return n.multiply(fact(n.subtract(ONE)));
		}
	}
	public static BigDecimal sqrt(BigDecimal n, BigDecimal x, BigDecimal y, BigDecimal e) {
		if(x.subtract(y).compareTo(e) <= 0) {
			return x;
		}
		else {
			x = x.add(y).divide(TWO, PREC);
			y = n.divide(x, PREC);
			return sqrt(n, x, y, e);
		}
	}
	public static BigDecimal rama(int n) {
		BigDecimal bigN = new BigDecimal(n);
		BigDecimal ram1 = sqrt(EIGHT, EIGHT, ONE, SQRT_PREC);
		BigDecimal ram2 = new BigDecimal(9801);
		BigDecimal ram3 = new BigDecimal(26390);
		BigDecimal ram4 = new BigDecimal(1103);
		BigDecimal ram5 = new BigDecimal(396);
		if(bigN.compareTo(ZERO) == 0) {
			return ONE.divide((fact(FOUR.multiply(bigN)).multiply(ram3.multiply(bigN).add(ram4)))
					.divide(fact(bigN).pow(4).multiply(ram5.pow(4*n)), PREC).multiply(ram1).divide(ram2, PREC), PREC);
		}
		else {
			return ONE.divide((fact(FOUR.multiply(bigN)).multiply(ram3.multiply(bigN).add(ram4)))
					.divide(fact(bigN).pow(4).multiply(ram5.pow(4*n)), PREC).multiply(ram1).divide(ram2, PREC)
					.add(rama(n - 1)), PREC);
		}
	}
	public static void main(String[] args) {
		int N = 2;
		System.out.print(rama(N));
	}
}