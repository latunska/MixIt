package project4;

import java.util.Scanner;

public class Mix implements IMix {
	/** Linked list of characters representing a message (string) */ 
	private LinkedList<Character> message;
	
	public Mix() {
		message = new LinkedList<Character>();
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
		if (tokens[0].equalsIgnoreCase("b")) {
			Character c = tokens[1].charAt(0);
			int pos = Integer.parseInt(tokens[2]);
			message.addBeforePosition(pos, c);
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
	
	public static void main(String[] args) {
		Mix temp = new Mix();
	}
}
