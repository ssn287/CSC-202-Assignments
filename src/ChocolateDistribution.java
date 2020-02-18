/**
 * ChocolateDistribution class implements an algorithm that optimally distributes chocolates among students
 *    @author Shelby Neal
 *    Emplid: 6030859
 *    Email: ssn287@email.vccs.edu
 *    Purpose: Programming Assignment #2
 */

import java.util.Arrays;
public class ChocolateDistribution {
	//set maximum amount of chocolates per packet
	private static final int PACKET_MAX = 56;
	//readArray method fills a given array with randomly sized packets recursively
	public static void readArray(int[] a, int i) {
		if(i < a.length) {
			a[i] = (int)(Math.random() * PACKET_MAX + 1);
			readArray(a, ++i);
		}
	}
	//bubbleSort method sorts a given array from smallest to largest iteratively
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
	/**
	 * chocolate method implements the following algorithm recursively:
	 *    Given an array of n integers where each value represents number of chocolates in a packet:
	 *       Each packet can have variable number of chocolates (change this parameter on line 12)
	 *       There are m students, the task is to distribute chocolate packets such that:
	 *          (1) Each students gets one packet
	 *          (2) The difference between the number of chocolates in a packet with maximum chocolates
	 *              and packet with minimum chocolates given to the students is minimum
	 * @param a = given sorted array, constant
	 * @param m = number of students, constant
	 * @param min_diff = largest array element, changes when a smaller minimum difference is found
	 * @param i = zero, index increments recursively
	 * @param beg = zero, changes to index when a smaller minimum difference is found
	 * @return String output showing smallest minimum difference and optimal packet set
	 */
	public static String chocolate(int[] a, int m, int min_diff, int i, int beg) {
		//base case: remaining elements in array < number of students
		if(i > a.length - m) {
			int[] sub = Arrays.copyOfRange(a, beg, beg + m);
			return "\n\tMinimum Difference is " + min_diff + " and the set is " + Arrays.toString(sub);
		}
		else {
			//smaller min_diff found, index in main array marked for starting sub-array index
			if(a[i + m - 1] - a[i] < min_diff) {
				return chocolate(a, m, a[i + m - 1] - a[i], i + 1, i);
			}
			//smaller min_diff NOT found, ONLY increment index
			else {
				return chocolate(a, m, min_diff, ++i, beg);
			}
		}
	}
	public static void main(String[] args) {
		//set number of packets and number of students
		final int SIZE = 17;
		final int STUD = 4;
		int[] arr = new int[SIZE];
		readArray(arr, 0);
		System.out.println(Arrays.toString(arr) + " (Unsorted Packets)");
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr) + " (Sorted Packets)");
		System.out.println(chocolate(arr, STUD, arr[SIZE - 1], 0, 0));
	}
}