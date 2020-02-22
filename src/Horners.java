/**
 * Horners class is used to solve polynomials
 *    @author Shelby Neal
 *    Emplid: 6030859
 *    Email: ssn287@email.vccs.edu
 *    Purpose: Programming Assignment #3
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Horners {
	static class Node {
		int data;
		Node next;
		public Node(int d) {
			this.data = d;
			this.next = null;
		}
	}
	public static Node head = null;
	public static Node tail = null;
	//reverse method reverses the linked list recursively
	public Node reverse(Node curr, Node prev) { //prev initially passed as null
		if(head == null) {
			System.out.print("\n\tList is Empty");
			return null;
		}
		//base case: last node
		if(curr.next == null) {
			head = curr; // last node moved to head
			curr.next = prev; // prev moved to next
			return head;
		}
		Node temp = curr.next; // temp var used to save next node for recursive call
		curr.next = prev; // prev moved to next
		reverse(temp, curr);
		return head;
	}
	//getSize method finds the size of a linked list recursively
	public int getSize(Node node) { //node initially passed as head
		//base case: list is empty
		if(node == null) {
			return 0;
		}
		return 1 + getSize(node.next);
	}
	//insertNode method inserts a new node at the tail of a linked list
	public void insertNode(int d) {
		//create a new Node
		Node newNode = new Node(d);
		//check if the list is empty
		if(head == null) {
			head = newNode;
			tail = newNode;
		}
		else {
			//if list is not empty, tail and head will point to newNode
			tail.next = newNode;
			tail = newNode;
		}
	}
	//display method prints the linked list coefficient inputs in polynomial form
	public void display() {
		Node current = head; int exp = 0;
		if(head == null) {
			System.out.println("\n\tPlease Input a Polynomial");
			return;
		}
		System.out.print("\n\tInputted Polynomial:");
		while(current != null) {
			System.out.print(" " + current.data + "x^" + exp);
			exp++;
			current = current.next;
		}
		System.out.println();
	}
	/**
	 * hornersRec method returns the value of a given polynomial recursively
	 * linked list must be reversed prior to calling this method
	 * @param curr: current node, initially passed as head
	 * @param x: given parameter, constant
	 * @param r: result, initially passed at zero and incremented recursively
	 * @return value of polynomial
	 */
	public long hornersRec(Node curr, int x, long r) {
		if(head == null) {
			System.out.print("\n\tPlease Input a Polynomial");
			return 0;
		}
		//base case: last node
		if(curr.next == null) {
			return r * x + curr.data;
		}
		else {
			r = r * x + curr.data;
			return hornersRec(curr.next, x, r);
		}
	}
	/**
	 * horners method returns the value of a given polynomial iteratively
	 * @param a: array converted from linked list
	 * @param x: given parameter, constant
	 * @return value of polynomial
	 */
	public static long horners(int[] a, int x) {
		long r = 0; // initialize result
		for(int i = 0; i < a.length; i++) {
			r = r * x + a[i];
		}
		return r;
	}
	public static void main(String[] args) {
		Horners slist = new Horners(); // initialize empty linked list
		int num; int x; Scanner kybd = new Scanner(System.in);
		while(true) { // program runs until user selects 0 to exit program
			int choice = Integer.parseInt(JOptionPane.showInputDialog("\n\tMake a Selection from the following: "
					+ "\n\t\t1 to Insert Coefficient"
					+ "\n\t\t2 to Print the Polynomial"
					+ "\n\t\t3 to Solve the Polynomial Recursively (Linked List)"
					+ "\n\t\t4 to Solve the Polynomial Iteratively (Array)"
					+ "\n\t\t0 to Exit Program"));
			switch(choice) {
			case 1:
				System.out.print("\n\tEnter an Integer Number (e.g. 34): ");
				num = kybd.nextInt();
				slist.insertNode(num);
				break;
			case 2:
				slist.display();
				break;
			case 3:
				System.out.println("\n\tEnter a Value for x (e.g. 3): ");
				x = kybd.nextInt();
				slist.reverse(head, null); // linked list is reversed for hornersRec method
				System.out.println("\n\tThe Value of the Polynomial is " + slist.hornersRec(head, x, 0));
				slist.reverse(head, null); // linked list is reversed back for user display
				break;
			case 4:
				System.out.println("\n\tEnter a Value for x (e.g. 3): ");
				x = kybd.nextInt();
				int[] arr = new int[slist.getSize(head)]; // getSize method is called to determine size of array
				Node current = head;
				for(int i = arr.length - 1; i >= 0; i--) { // starting at the end and moving to the front so that reversal is unnecessary
					arr[i] = current.data;
					current = current.next;
				}
				System.out.println("\n\tThe Value of the Polynomial is " + horners(arr, x));
				break;
			case 0:
				System.exit(0);
			default:
				System.out.println("\n\tInvalid Input (Re-Enter Value): ");
			}
		}
	}
}