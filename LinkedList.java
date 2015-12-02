package project4;

import javax.xml.transform.Templates;

/*****************************************************************
This class creates a LinkedList using the Node class. Its various
methods allow for manipulating the list in different ways, mostly
customized for use in Mix and UnMix.

@author Carolyn
@version November 2015
******************************************************************/
public class LinkedList<E> {
	public Node<E> head;
	public Node<E> tail;
	private int counter;
	
	/*****************************************************************
	Default constructor for LinkedList. Sets head and tail pointers to
	null, and instantiates a counter.
	******************************************************************/
	public LinkedList() {
		head = null;
		tail = null;
		//Keeps track of number of nodes
		counter = 0;
	}
	
	/*****************************************************************
	Keeps track of number of elements in LinkedList.
	
	@return int The number of nodes
	******************************************************************/
	public int getCounter() {
		return counter;
	}
	
	/*****************************************************************
	Adds a node containing specified data to the end of the list.
	
	@param data The data to add to the new Node.
	******************************************************************/
	public void addToEnd(E data) {
		//In the case that list is empty
		if (tail == null) {
			head = new Node<E>(data,null);
			tail = head;
			counter++;
		}
		//Otherwise set tail to new node
		else {
			Node<E> temp = new Node<E>( data,null);
			tail.setNext(temp);
			tail = temp;
			counter++;
		}
	}
	
	/*****************************************************************
	Adds a node before the specified position.
	
	@param position The position to add the node before
	@param data The data the new node should hold
	@throws IllegalArgumentException if position out of bounds
	******************************************************************/
	public void addBeforePosition(int position, E data) {
		//Throws error if position is out of range
		if (position > counter + 1 || position < 0) {
			throw new IllegalArgumentException();
		}
		//Adds node to beginning
		if (position == 0) {
			Node<E> temp = new Node<E>(data, head);
			head = temp;
			counter++;
		}
		//Adds node to end
		else if (position == counter) {
			addToEnd(data);
		}
		//Adds node to middle
		else {
			Node<E> temp = head;
			for (int i = 0; i <= position - 2; i++) {
				temp = temp.getNext();
			}
			temp.setNext(new Node<E>(data, temp.getNext()));
			counter++;
		}
	}
	
	/*****************************************************************
	Removes node from specified position and returns the data from
	the node that was removed.
	
	@return E The data held in the removed node
	@throws IllegalArgumentException if position out of bounds
	******************************************************************/
	public E removeAtPosition(int position) {
		E removed;
		//Throws error if position out of bounds
		if (position > counter - 1 || position < 0) {
			throw new IllegalArgumentException();
		}
		//Removes node if position is zero
		else if (position == 0) {
			removed = head.getData();
			head = head.getNext();
			counter--;
		}
		//Removes node if 
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
	
	/*****************************************************************
	Switches to nodes in the list given their positions
	
	@param first The number for the position of one node
	@paaram second The number for the position of the other node
	@throws IllegalArgumentException if either position out of bounds
	******************************************************************/
	public void switchPositions(int first, int second) {
		//Checks if out of bounds
		if (first > counter - 1 || second > counter - 1 || first < 0 
				|| second < 0) {
			throw new IllegalArgumentException();
		}
		//Switches order if second is greater than first
		if (second < first) {
			int temp = second;
			second = first;
			first = temp;
		}
		//So long as they are not the same node, switches them
		if (first != second) {
			addBeforePosition(second, removeAtPosition(first));
			addBeforePosition(first, removeAtPosition(second - 1));
		}
	}
	
	/*****************************************************************
	Returns the data from a node when given its position.
	
	@param position The number specifying position of node
	@return E The data from the node
	@throws IllegalArgumentException if out of bounds
	******************************************************************/
	public E copyAtPosition(int position) {
		//Checks if out of bounds
		if (position < 0 || position > counter) {
			throw new IllegalArgumentException();
		}
		//Returns data if position is 0
		else if (position == 0) {
			return head.getData();
		}
		//Otherwise iterates through list
		else {
			Node<E> temp = head;
			for (int i = 1; i <= position - 1; i++) {
				temp = temp.getNext();
			}
			return temp.getData();
		}
	}
	
	/*****************************************************************
	Returns a string containing each element.
	
	@return String contains each element
	******************************************************************/
	public String getFinalMessage() {
		//Iterates through list and adds each node's data to string
		Node<E> temp = head;
		String result = "";
		while (temp != null) {
			result += temp.getData();
			temp = temp.getNext();
		}
		return result;
	}
	
	/*****************************************************************
	Returns each element in list with spaces between elements.
	
	@return String containing each element
	******************************************************************/
	//Returns list containing each element with spaces between elements
	public String getCurrentMessage() {
		//Iterates through list and adds to string
		Node<E> temp = head;
		String result = "";
		while (temp != null) {
			result += temp.getData() + "  ";
			temp = temp.getNext();
		}
		return result;
	}
	
	/*****************************************************************
	Deletes all elements in the list.
	******************************************************************/
	public void deleteAll() {
		if (head != null) {
			counter = 0;
			head = null;
			tail = null;
		}
	}
}
