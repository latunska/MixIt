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
			System.out.println("Added " + data);
		}
		else {
			Node<E> temp = new Node<E>(null, data);
			tail.setNext(temp);
			tail = temp;
			counter++;
			System.out.println("Added " + data);
		}
	}
	
	public void addBeforePosition(int position, E data) {
		if (position > counter + 1 || position < 0) {
			throw new IllegalArgumentException();
		}
		if (position == 0) {
			Node<E> temp = new Node<E>(head, data);
			head = temp;
			counter++;
		}
		else if (position == counter) {
			addToEnd(data);
		}
		else {
			Node<E> temp = head;
			for (int i = 0; i <= position - 2; i++) {
				temp = temp.getNext();
			}
			temp.setNext(new Node<E>(temp.getNext(), data));
			counter++;
		}
	}
	
	public E removeAtPosition(int position) {
		E removed;
		if (position > counter || position < 0) {
			throw new IllegalArgumentException();
		}
		else if (position == 0) {
			removed = head.getData();
			head = head.getNext();
			counter--;
			return removed;
		}
		else {
			Node<E> temp = head;
			for (int i = 0; i <= position - 2; i++) {
				temp = temp.getNext();
			}
			removed = temp.getNext().getData();
			temp.setNext(temp.getNext().getNext());
			counter--;
			return removed;
		}
	}
	
	public void switchPositions(int first, int second) {
		if (first > counter || second > counter || first < 0 || second < 0) {
			throw new IllegalArgumentException();
		}
		if (second < first) {
			int temp = second;
			second = first;
			first = temp;
		}
		if (first != second) {
			addBeforePosition(second, removeAtPosition(first));
			addBeforePosition(first, removeAtPosition(second - 1));
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
			result += temp.getData() + "  ";
			temp = temp.getNext();
		}
		return result;
	}
}
