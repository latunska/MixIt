package project4;

import java.util.Scanner;

public class Mix implements IMix {
	/** Linked list of characters representing a message (string) */ 
	private LinkedList<Character> message;
	
	private LinkedList<Character> clipboard;
	
	public Mix() {
		message = new LinkedList<Character>();
		clipboard = new LinkedList<Character>();
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter intial message: ");
		String input = scnr.nextLine();
		setInitialMessage(input);
		String command = "";
		while (!(command.equalsIgnoreCase("Q"))) {
			System.out.println("Command: ");
			command = scnr.nextLine();
			if (command.equalsIgnoreCase("Q"))
				break;
			else {
				System.out.println(processCommand(command));
			}
		}
		System.out.println("Final Message: " + message.getFinalMessage());
	}

	@Override
	public void setInitialMessage(String message) {
		for (int i = 0; i < message.length(); i++) {
			this.message.addToEnd(message.charAt(i));
		}
		System.out.println("\t" + this.message.getCurrentMessage());
	}

	@Override
	public String processCommand(String command) {
		String currentMessage = message.getFinalMessage();
		String[] tokens = command.split(" ");
		//Determines if command is in form of 'a x'
		if (tokens[0].equalsIgnoreCase("a")) {
			Character adding = tokens[1].charAt(0);
			message.addToEnd(adding);
		}
		else if (tokens[0].equalsIgnoreCase("b")) {
			Character c = tokens[1].charAt(0);
			int pos = Integer.parseInt(tokens[2]);
			message.addBeforePosition(pos, c);
		}
		else if (tokens[0].equalsIgnoreCase("r")) {
			int pos = Integer.parseInt(tokens[1]);
			message.removeAtPosition(pos);
		}
		else if (tokens[0].equalsIgnoreCase("w")) {
			int first = Integer.parseInt(tokens[1]);
			int second = Integer.parseInt(tokens[2]);
			message.switchPositions(first, second);
		}
		else if (tokens[0].equalsIgnoreCase("x")) {
			int start = Integer.parseInt(tokens[1]);
			int end = Integer.parseInt(tokens[2]);
			cutToClipboard(start, end);
		}
		else if (tokens[0].equalsIgnoreCase("p")) {
			int start = Integer.parseInt(tokens[1]);
			pasteFromClipboard(start);
		}
		else {
			System.out.println("Command not found");
		}
		//Displays current message with position numbers above
		String str = "Message: \n\t";
		//Cycles through numbers for each element plus one
		for (int i = 0; i < message.getCounter() + 1; i++) {
			str += i + " ";
			if (i < 10) {
				str += " ";
			}
		}
		str += "\n\t" + message.getCurrentMessage();
		return str;
	}
	
	private void cutToClipboard(int start, int end) {
		if (start > end || start < 0 || end > message.getCounter()) {
			throw new IllegalArgumentException();
		}
		for (int i = start; i <= end; i++) {
			Character b = message.removeAtPosition(start);
			System.out.println(b);
			clipboard.addToEnd(b);
			//clipboard.addToEnd(message.removeAtPosition(start));
		}
	}
	
	//Note: change to addBeforePosition(start + i - 1,
	//if needs to paste before given position
	private void pasteFromClipboard(int start) {
		if (start < 0 || start > message.getCounter()) {
			throw new IllegalArgumentException();
		}
		//Iterates through clipboard and adds to message
		else {
			for (int i = 1; i < clipboard.getCounter() + 1; i++) {
				message.addBeforePosition(start + i, clipboard.copyAtPosition(i));
			}
		}
	}
	
	public static void main(String[] args) {
		Mix temp = new Mix();
	}
}
