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
		if (position > counter - 1 || position < 0) {
			throw new IllegalArgumentException();
		}
		else if (position == 0) {
			removed = head.getData();
			head = head.getNext();
			counter--;
		}
		else {
			Node<E> temp = head;
			for (int i = 0; i <= position - 2; i++) {
				temp = temp.getNext();
			}
			removed = temp.getNext().getData();
			if (tail == temp.getNext()) {
				tail = temp;
			}
			temp.setNext(temp.getNext().getNext());
			counter--;
		}
		return removed;
	}
	
	public void switchPositions(int first, int second) {
		if (first > counter - 1 || second > counter - 1 || first < 0 || second < 0) {
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
	
	public E copyAtPosition(int position) {
		if (position < 0 || position > counter) {
			throw new IllegalArgumentException();
		}
		//Something weird here, had to skip over position 0 or else
		//repeated 0
		else if (position == 0) {
			return head.getData();
		}
		else {
			Node<E> temp = head;
			for (int i = 1; i <= position - 1; i++) {
				temp = temp.getNext();
			}
			return temp.getData();
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
	
	public void deleteAll() {
		if (head != null) {
			counter = 0;
			head = null;
			tail = null;
		}
	}
}
