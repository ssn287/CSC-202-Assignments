import java.util.Arrays;
public class ChocolateDistribution {
	public static void readArray(int[] a, int i) {
		if(i < a.length) {
			a[i] = (int)(Math.random() * 100);
			readArray(a, ++i);
		}
	}
	public static void bubbleSort(int[] a) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 1; j < a.length - i; j++) {
				if(a[j - 1] > a[j]) {
					int temp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = temp;
				}
			}
		}
	}
	public static int findMax(int[] a, int n, int i) {
		if(i >= a.length) {
			return n;
		}
		else {
			if(a[i] > n) {
				return findMax(a, a[i], ++i);
			}
			else {
				return findMax(a, n, ++i);
			}
		}
	}
	public static int[] chocolate(int[] a, int m, int min_diff, int i, int beg) {
		if(i >= a.length) {
			return Arrays.copyOfRange(a, beg, beg + m - 1);
		}
		else {
			if(a[i + m - 1] - a[i] < min_diff) {
				return chocolate(a, m, a[i + m - 1] - a[i], i + 1, i);
			}
			else {
				return chocolate(a, m, min_diff, i + 1, i + 1);
			}
		}
	}
	public static void main(String[] args) {
		
	}
}