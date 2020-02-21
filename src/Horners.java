import java.util.Scanner;

import javax.swing.JOptionPane;

public class Horners {
	class Node {
		int data;
		Node next;
		public Node(int d) {
			this.data = d;
			this.next = null;
		}
	}
	public Node head = null;
	public Node tail = null;
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
	}
	public Node reverse(Node node) {
		Node prev = null;
		Node current = node;
		Node next = null;
		while(current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
	}
	public long hornersRec(int x) {
		Node current = head; long r = 0;
		if(head == null) {
			System.out.print("\n\tPlease Input a Polynomial");
			return 0;
		}
		reverse(current);
		while(current != null) {
			r += r * x + current.data;
			current = current.next;
		}
		return r;
	}
	public static void main(String[] args) {
		Horners slist = new Horners();
		int num; int x; Scanner kybd = new Scanner(System.in);
		while(true) {
			int choice = Integer.parseInt(JOptionPane.showInputDialog("\n\tMake a Selection from the following: "
					+ "\n\t\t1 to Insert Coefficient"
					+ "\n\t\t2 to Print the Polynomial"
					+ "\n\t\t3 to Solve the Polynomial Recurisvely (Linked List)"
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
				System.out.print("\n\n\tThe Value of the Polynomial is " + slist.hornersRec(x));
				break;
			case 0:
				System.exit(0);
			default:
				System.out.println("\n\tInvalid Input (Re-Enter Value): ");
			}
		}
	}
}