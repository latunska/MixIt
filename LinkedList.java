package project4;

import javax.xml.transform.Templates;

public class LinkedList<E> {
	public Node<E> head;
	public Node<E> tail;
	private int counter;
	
	public LinkedList() {
		head = null;
		tail = null;
		//Keeps track of number of nodes
		counter = 0;
	}
	
	public int getCounter() {
		return counter;
	}
	
	//Adds a node to the end of the list
	public void addToEnd(E data) {
		if (tail == null) {
			head = new Node<E>(null, data);
			tail = head;
			counter++;
		}
		else {
			Node<E> temp = new Node<E>(null, data);
			tail.setNext(temp);
			tail = temp;
			counter++;
		}
	}
	
	//Returns a string containing each element
	public String getFinalMessage() {
		Node<E> temp = head;
		String result = "";
		while (temp != null) {
			result += temp.getData();
			temp = temp.getNext();
		}
		return result;
	}
	
	//Returns list containing each element, with spaces between elements
	public String getCurrentMessage() {
		Node<E> temp = head;
		String result = "";
		while (temp != null) {
			result += temp.getData() + " ";
			temp = temp.getNext();
		}
		return result;
	}
}
