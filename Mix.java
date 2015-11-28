package project4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Mix implements IMix {
	/** Linked list of characters representing a message (string) */ 
	private LinkedList<Character> message;
	
	private LinkedList<Character> clipboard;
	
	private ArrayList<String> commands;
	
	private int clipboardLength;
	
	private boolean isPaste, isRemoval, isCut;
	
	private Character removed;
	
	public Mix() {
		message = new LinkedList<Character>();
		clipboard = new LinkedList<Character>();
		commands = new ArrayList<String>();
		isPaste = false;
		isRemoval = false;
		isCut = false;
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
				if (isPaste) {
					command += " " + clipboardLength;
				}
				if (isCut) {
					String str = clipboard.getFinalMessage();
					command += " '" + str + "'";
				}
				if (isRemoval) {
					System.out.println("Here");
					if (removed == ' ') {
						command += " %20";
					}
					else {
						command += " " + removed;
					}
				}
				commands.add(0, command);
				isPaste = false;
				isRemoval = false;
				isCut = false;
			}
		}
		System.out.println("Final Message: " + message.getFinalMessage());
	}

	@Override
	public void setInitialMessage(String message) {
		for (int i = 0; i < message.length(); i++) {
			this.message.addToEnd(message.charAt(i));
		}
		System.out.println(displayCurrentMessage());
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
			removed = message.removeAtPosition(pos);
			System.out.println(removed);
			isRemoval = true;
		}
		else if (tokens[0].equalsIgnoreCase("w")) {
			int first = Integer.parseInt(tokens[1]);
			int second = Integer.parseInt(tokens[2]);
			message.switchPositions(first, second);
		}
		else if (tokens[0].equalsIgnoreCase("x")) {
			isCut = true;
			int start = Integer.parseInt(tokens[1]);
			int end = Integer.parseInt(tokens[2]);
			cutToClipboard(start, end);
		}
		else if (tokens[0].equalsIgnoreCase("p")) {
			isPaste = true;
			int start = Integer.parseInt(tokens[1]);
			pasteFromClipboard(start);
		}
		else if (tokens[0].equalsIgnoreCase("c")) {
			int start = Integer.parseInt(tokens[1]);
			int end = Integer.parseInt(tokens[2]);
			copyToClipboard(start, end);
		}
		else if (tokens[0].equalsIgnoreCase("s")) {
			String filename = tokens[1];
			try {
				save(filename);
			} catch (IOException e) {
				System.out.println("Could not create file.");
			}
		}
		else {
			System.out.println("Command not found");
		}
		
		String str = displayCurrentMessage();
		return str;
	}
	
	private String displayCurrentMessage() {
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
		clipboard.deleteAll();
		for (int i = start; i <= end; i++) {
			clipboard.addToEnd(message.removeAtPosition(start));
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
			clipboardLength = clipboard.getCounter();
			for (int i = 1; i < clipboard.getCounter() + 1; i++) {
				message.addBeforePosition(start + i, clipboard.copyAtPosition(i));
			}
		}
	}
	
	private void copyToClipboard(int start, int end) {
		if (start > end || start < 0 || end > message.getCounter()) {
			throw new IllegalArgumentException();
		}
		else {
			clipboard.deleteAll();
			System.out.println(clipboard.getFinalMessage());
			for (int i = start + 1; i <= end + 1; i++) {
				clipboard.addToEnd(message.copyAtPosition(i));
			}
		}
	}
	
	public void save(String name) throws IOException {
		File file = new File(name);
		file.createNewFile();
		try {
			// FileWriter writes text files in the default encoding.
			FileWriter writer = new FileWriter(file);

			// BufferWriter holds the text
			BufferedWriter buffer = new BufferedWriter(writer);

			for(String i : commands) {
				buffer.write(i,0,i.length());
				buffer.newLine();
			}
			buffer.close();         
		}
		catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,
					"Unable to open file '" + 
							file + "");                
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					"Error reading file '" 
							+ file + "");                  
		}
	}
	
	public static void main(String[] args) {
		Mix temp = new Mix();
	}
}
