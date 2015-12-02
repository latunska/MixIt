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
	
	/*****************************************************************
	Default constructor for Mix class. Requires calling methods 
	individually in order to set message and process commands.
	******************************************************************/
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
	Constructor for Mix class when provided with initial message to
	begin with. This automatically prompts user for commands.
	
	@param input The user's initial message.
	******************************************************************/
	public Mix(String input) {
		//Instantiates the linkedlists for main message and clipboard
		message = new LinkedList<Character>();
		clipboard = new LinkedList<Character>();
		commands = new ArrayList<String>();
		//Sets all 'is__' variables to false
		isPaste = false;
		isRemoval = false;
		isCut = false;
		//Sets don't write to false
		dontWrite = false;
		//Instantiates a scanner to prompt user for commands
		Scanner scnr = new Scanner(System.in);
		//Sets the initial message
		setInitialMessage(input);
		//Displays formatted message to user
		System.out.println(displayCurrentMessage());
		String command = "";
		//Loops until user enters 'Q' or 'q'
		while (!(command.equalsIgnoreCase("Q"))) {
			//Prompts user for command and stores in variable
			System.out.println("Command: ");
			command = scnr.nextLine();
			//Breaks out of loops if command is q
			if (command.equalsIgnoreCase("Q")) {
				break;
			}
			//Else, processes command and displays formatted message
			else {
				processCommand(command);
				System.out.println(displayCurrentMessage());
			}
		}
		//Prints out final message once user enters 'q'
		System.out.println("Final Message: " + message
				.getFinalMessage());
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
		String currentMessage = message.getFinalMessage();
		//Splits command into array of Strings
		String[] tokens = command.split(" ");
		//Attempts to interpret command
		try {
			//Checks if command is too long
			if (tokens.length > 3 && !tokens[0].equalsIgnoreCase("b")) {
				throw new Exception();
			}
			//Processes append commands
			if (tokens[0].equalsIgnoreCase("a")) {
				//Checks if command is too long
				if (tokens.length > 2) {
					throw new Exception();
				}
				Character adding;
				//Determines if appending space
				if (tokens.length == 1 && command.length() >= 3) {
					adding = ' ';
				}
				//Otherwise appends character normally
				else {
					adding = tokens[1].charAt(0);
				}
				message.addToEnd(adding);
			}
			//Processes insert commands
			else if (tokens[0].equalsIgnoreCase("b")) {
				//Checks if command is too long
				if (tokens.length > 4) {
					throw new Exception();
				}
				Character c;
				int pos;
				//Sets position and character if inserting space
				if (tokens[1].length() == 0) {
					c = ' ';
					pos = Integer.parseInt(tokens[3]);
				}
				//Sets position and character otherwise
				else {
					//Checks if command has too many parts
					if (tokens.length > 3) {
						throw new Exception();
					}
					c = tokens[1].charAt(0);
					pos = Integer.parseInt(tokens[2]);
				}
				message.addBeforePosition(pos, c);
			}
			//Processes remove command
			else if (tokens[0].equalsIgnoreCase("r")) {
				//Checks if command has too many parts
				if (tokens.length > 2) {
					throw new Exception();
				}
				int pos = Integer.parseInt(tokens[1]);
				//Saves removed character for unmixing
				removed = message.removeAtPosition(pos);
				//Notifies saveCommand to add removed
				isRemoval = true;
			}
			//Processes switch command
			else if (tokens[0].equalsIgnoreCase("w")) {
				int first = Integer.parseInt(tokens[1]);
				int second = Integer.parseInt(tokens[2]);
				message.switchPositions(first, second);
			}
			//Processes cut command
			else if (tokens[0].equalsIgnoreCase("x")) {
				//Notifies saveCommand that command is a cut
				isCut = true;
				int start = Integer.parseInt(tokens[1]);
				int end = Integer.parseInt(tokens[2]);
				cutToClipboard(start, end);
			}
			//Processes paste command
			else if (tokens[0].equalsIgnoreCase("p")) {
				if (tokens.length > 2) {
					throw new Exception();
				}
				//Notifies save command that command is a paste
				isPaste = true;
				int start = Integer.parseInt(tokens[1]);
				pasteFromClipboard(start);
			}
			//Processes copy command
			else if (tokens[0].equalsIgnoreCase("c")) {
				int start = Integer.parseInt(tokens[1]);
				int end = Integer.parseInt(tokens[2]);
				copyToClipboard(start, end);
				dontWrite = true;
			}
			//Processes save command
			else if (tokens[0].equalsIgnoreCase("s")) {
				String filename = tokens[1];
				//Tries to save to file and notifies user if error
				try {
					save(filename);
				} catch (IOException e) {
					System.out.println("Could not create file.");
				}
				dontWrite = true;
			}
			//Processes help command
			else if (tokens[0].equalsIgnoreCase("h")) {
				System.out.println(getInstructions());
				//Notifies saveCommand to ignore this command
				dontWrite = true;
			}
			//Processes any other input
			else {
				System.out.println("Command not found");
				//Notifies saveCommand to ignore this command
				dontWrite = true;
			}
			saveCommand(command);
		}
		//Catches NumFormatException and informs reader of error
		catch (NumberFormatException e) {
			System.out.println("Command has a character where a number"
					+ " should be.");
		}
		//Catches IllegalArgumentExceptions (which mean that something
		//was out of range or in the wrong order)
		catch (IllegalArgumentException e) {
			System.out.println("Numbers in command out of range or "
					+ "order.");
		}
		//Catches instances where command is missing a character or two
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Command requires another part.");
		}
		//Catches instances where command is missing a part
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("Command requires another part.");
		}
		//Catches any other possible errors
		catch (Exception e) {
			System.out.println("Command formmatted incorrectly.");
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
		if (start > end || start < 0 || end > message.getCounter() - 1){
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
				message.addBeforePosition(start + i - 1, clipboard
						.copyAtPosition(i));
			}
		}
	}
	
	private void copyToClipboard(int start, int end) {
		//Throws exception if arguments are out of bounds or order
		if (start > end || start < 0 || end > message.getCounter() - 1){
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
	/*****************************************************************
	Saves list of commands to a file
	
	@param name Name to save file to
	@throws IOException Throws if error occurs while saving
	******************************************************************/
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
	
	private static String getInstructions() {
		//Returns string that constitutes help menu
		String str = "Commands (not case sensitive): " + "\n\tQ\t\t "
				+ "Quit";
		str += "\n\tH \t\t display this menu again";
		str += "\n\ta c\t\t append character c";
		str += "\n\tb c #\t\t insert character c before position #";
		str += "\n\tr #\t\t remove character at position #";
		str += "\n\tw & #\t\t switch characters at positions & and #";
		str += "\n\tx & #\t\t cut message between positions & and # "
				+ "(inclusive)";
		str += "\n\tp #\t\t paste from clipboard starting before "
				+ "position #";
		str += "\n\tc & #\t\t cut message between positions & and # "
				+ "(inclusive)";
		str += "\n\ts filename\t saves file to unmix message in text "
				+ "file of given name";
		str += "\nTo insert or append a space, type 'space' in place of"
				+ " the character";
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
	/*****************************************************************
	Main method that begins the process of prompting user for input.
	******************************************************************/
	public static void main(String[] args) {
		//Sets up scanner that allows the user to enter initial message
		Scanner scnr = new Scanner(System.in);
		System.out.println(getInstructions());
		System.out.println("Enter initial message: ");
		String input = scnr.nextLine();
		Mix temp = new Mix(input);
	}
}
