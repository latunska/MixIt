package project4;
//TO FIX? - Does filenotfound catch block work?
//TO FIX? - check public versus private for helper methods
//Add more comments inside try block of processCommand
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*****************************************************************
This class allows the user to enter in a message and use various
commands to mix that message up. Additionally, it optionally stores
the commands to a file which can later be used to unmix the
resulting message.

@author Carolyn
@version November 2015
******************************************************************/

import javax.swing.JOptionPane;

public class Mix implements IMix {
	/** Linked list of characters representing a message (string) */ 
	private LinkedList<Character> message;
	
	/** Linked list of characters representing clipboard */
	private LinkedList<Character> clipboard;
	
	/** ArrayList that holds the commands to be saved to text file */
	private ArrayList<String> commands;
	
	/** Number of elements in the clipboard */
	private int clipboardLength;
	
	/** Boolean values specifying if command is any of the following,
	where dontWrite means not to write command to file */
	private boolean isPaste, isRemoval, isCut, dontWrite;
	
	/** Holds a character that has been removed */
	private Character removed;
	
	public Mix() {
		message = new LinkedList<Character>();
		clipboard = new LinkedList<Character>();
		commands = new ArrayList<String>();
		//Sets all 'is__' variables to false
		isPaste = false;
		isRemoval = false;
		isCut = false;
		//Sets don't write to false
		dontWrite = false;
	}
	
	/*****************************************************************
	Constructor for Mix class.
	******************************************************************/
	public Mix(String input) {
		message = new LinkedList<Character>();
		clipboard = new LinkedList<Character>();
		commands = new ArrayList<String>();
		//Sets all 'is__' variables to false
		isPaste = false;
		isRemoval = false;
		isCut = false;
		//Sets don't write to false
		dontWrite = false;
		//Instantiates a scanner and prompts user for inputs
		Scanner scnr = new Scanner(System.in);
		setInitialMessage(input);
		System.out.println(displayCurrentMessage());
		String command = "";
		//Loops until user enters 'Q' or 'q'
		while (!(command.equalsIgnoreCase("Q"))) {
			//Prompts user for command and stores in variable
			System.out.println("Command: ");
			command = scnr.nextLine();
			if (command.equalsIgnoreCase("Q")) {
				break;
			}
			else {
				processCommand(command);
				System.out.println(displayCurrentMessage());
			}
		}
		//Prints out final message once user enters 'q'
		System.out.println("Final Message: " + message.getFinalMessage());
	}
	
	/*****************************************************************
	Takes the message the user entered and inputs it into the
	LinkedList 'message.'
	
	@param message The user's original message
	******************************************************************/
	@Override
	public void setInitialMessage(String message) {
		//Iterates through message and adds each character to list
		for (int i = 0; i < message.length(); i++) {
			this.message.addToEnd(message.charAt(i));
		}
	}
	
	/*****************************************************************
	Accepts the commands as a parameter and processes them, either
	changing the message, notifying the user of errors, saving to
	file, or displaying a help menu.
	
	@param command The user's command
	@return String the message's current state
	******************************************************************/
	@Override
	public String processCommand(String command) {
		isPaste = false;
		isRemoval = false;
		isCut = false;
		dontWrite = false;
		String currentMessage = message.getFinalMessage();
		//Splits command into array of Strings
		String[] tokens = command.split(" ");
		//Attempts to interpret command
		try {
			if (tokens[0].equalsIgnoreCase("a")) {
				Character adding;
				if (tokens.length == 1 && command.length() >= 3) {
					adding = ' ';
				}
				else {
					adding = tokens[1].charAt(0);
				}
				message.addToEnd(adding);
			}
			else if (tokens[0].equalsIgnoreCase("b")) {
				Character c;
				int pos;
				if (tokens[1].length() == 0) {
					c = ' ';
					pos = Integer.parseInt(tokens[3]);
				}
				else {
					c = tokens[1].charAt(0);
					pos = Integer.parseInt(tokens[2]);
				}
				message.addBeforePosition(pos, c);
			}
			else if (tokens[0].equalsIgnoreCase("r")) {
				int pos = Integer.parseInt(tokens[1]);
				removed = message.removeAtPosition(pos);
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
			else if (tokens[0].equalsIgnoreCase("h")) {
				System.out.println(getInstructions());
				dontWrite = true;
			}
			else {
				System.out.println("Command not found");
				dontWrite = true;
			}
			saveCommand(command);
		}
		//Catches NumFormatException and informs reader of error
		catch (NumberFormatException e) {
			System.out.println("Command has a character where a number should be.");
		}
		//Catches IllegalArgumentExceptions (which mean that something
		//was out of range or in the wrong order)
		catch (IllegalArgumentException e) {
			System.out.println("Numbers in command out of range or order.");
		}
		//Catches instances where command is missing a character or two
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Command requires another part.");
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("Command requires another part.");
		}
		catch (Exception e) {
			System.out.println("Please check command and try again.");
		}
		//Returns current message
		String str = message.getFinalMessage();
		return str;
	}
	
	private String displayCurrentMessage() {
		//Displays current message with position numbers above
		String str = "Message: \n\t";
		//Cycles through numbers for each element plus one
		for (int i = 0; i < message.getCounter() + 1; i++) {
			str += i + " ";
			//Creates even spacing
			if (i < 10) {
				str += " ";
			}
		}
		str += "\n\t" + message.getCurrentMessage();
		return str;
	}
	
	private void cutToClipboard(int start, int end) {
		//Throws exception if arguments out of order or out of bounds
		if (start > end || start < 0 || end > message.getCounter() - 1) {
			throw new IllegalArgumentException();
		}
		//Erases previous content of clipboard
		clipboard.deleteAll();
		//Moves specified letters from message to clipboard
		for (int i = start; i <= end; i++) {
			clipboard.addToEnd(message.removeAtPosition(start));
		}
	}

	private void pasteFromClipboard(int start) {
		//Throws exception if argument is out of bounds
		if (start < 0 || start > message.getCounter()) {
			throw new IllegalArgumentException();
		}
		//Iterates through clipboard and adds to message
		else {
			clipboardLength = clipboard.getCounter();
			for (int i = 1; i < clipboard.getCounter() + 1; i++) {
				message.addBeforePosition(start + i - 1, clipboard.copyAtPosition(i));
			}
		}
	}
	
	private void copyToClipboard(int start, int end) {
		//Throws exception if arguments are out of bounds or order
		if (start > end || start < 0 || end > message.getCounter() - 1) {
			throw new IllegalArgumentException();
		}
		else {
			//Erases previous content of clipboard
			clipboard.deleteAll();
			//Iterates through message and copies to clipboard
			for (int i = start + 1; i <= end + 1; i++) {
				clipboard.addToEnd(message.copyAtPosition(i));
			}
		}
	}
	
	public void save(String name) throws IOException {
		//Creates a new file
		File file = new File(name);
		file.createNewFile();
		//Tries to write to file
		try {
			// FileWriter writes text files in the default encoding.
			FileWriter writer = new FileWriter(file);

			// BufferWriter holds the text
			BufferedWriter buffer = new BufferedWriter(writer);
			
			//Iterates through commands and writes to file
			for(String i : commands) {
				buffer.write(i,0,i.length());
				buffer.newLine();
			}
			//Closes buffer
			buffer.close();         
		}
		//Catches exception if file not found
		catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,
					"Unable to open file '" + 
							file + "");                
		}
		//Catches IOException
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					"Error reading file '" 
							+ file + "");                  
		}
	}
	
	public static String getInstructions() {
		//Returns string that constitutes help menu
		String str = "Commands (not case sensitive): " + "\n\tQ\t\t Quit";
		str += "\n\tH \t\t display this menu again";
		str += "\n\ta c\t\t append character";
		str += "\n\tb c #\t\t insert character c before position #";
		str += "\n\tr #\t\t remove character at position #";
		str += "\n\tw & #\t\t switch characters at positions & and #";
		str += "\n\tx & #\t\t cut message between positions & and # (inclusive)";
		str += "\n\tp #\t\t paste from clipboard starting before position #";
		str += "\n\tc & #\t\t cut message between positions & and # (inclusive)";
		str += "\n\ts filename\t saves file to unmix message in text file of given name";
		str += "\nTo insert or append a space, type 'space' in place of the character";
		return str;
	}
	
	private void saveCommand(String command) {
		if (isPaste) {
			command += " " + clipboardLength;
		}
		//If cut, adds contents of clipboard for unmixing
		if (isCut) {
			String str = clipboard.getFinalMessage();
			command += " '" + str + "'";
		}
		//If removal, adds letter removed for unmixing
		if (isRemoval) {
			//Changes spaces to %20 to be split in unmixing
			if (removed == ' ') {
				command += " %20";
			}
			//Otherwise just adds removed character
			else {
				command += " " + removed;
			}
		}
		//Adds to list of commands iff dontWrite is false
		if (!dontWrite) {
			commands.add(0, command);
		}
		//Sets all checking variables back to false
		isPaste = false;
		isRemoval = false;
		isCut = false;
		dontWrite = false;
	}
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println(getInstructions());
		System.out.println("Enter initial message: ");
		String input = scnr.nextLine();
		Mix temp = new Mix(input);
	}
}
