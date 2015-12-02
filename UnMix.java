package project4;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*****************************************************************
This class allows the user to enter a previously mixed message
and the file that the list of commands was saved to, and then
unmixes the message.

@author Carolyn
@version November 2015
******************************************************************/
public class UnMix implements IUnMix {
	/** LinkedList of characters representing a message (string) */ 
	private LinkedList<Character> message;
	
	/** LinkedList of characters representing clip board */
	private LinkedList<Character> clipboard;
	
	/*****************************************************************
	Default constructor for UnMix class. Requires calling methods 
	individually in order to set initial mixed message and file.
	******************************************************************/
	public UnMix() {
		message = new LinkedList<Character>();
		clipboard = new LinkedList<Character>();
	}
	
	/*****************************************************************
	Constructor for Mix class used when provided with message and file
	ahead of time.
	
	@param mixed the mixed up message
	@param file the file name where the mix commands were stored
	******************************************************************/
	public UnMix(String mixed, String file) {
		message = new LinkedList<Character>();
		clipboard = new LinkedList<Character>();
		System.out.println(UnMixUsingFile(file, mixed));
	}
	
	/*****************************************************************
	Method used to unmix the message, given the mixed up message and
	the name of the file.
	
	@param filename the filename where the mixing commands were stored
	@param mixedMessage the mixed up message
	******************************************************************/
	@Override
	public String UnMixUsingFile(String filename, String mixedMessage) {
		String str;
		//Puts mixedMessage into LinkedList message
		enterInitialMessage(mixedMessage);
	    String line = null;
	    String[]tokens;
	    //Tries to read file, alerts user to various errors
		try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(filename);
	        BufferedReader bufferedReader = new BufferedReader
	        		(fileReader);
	        //Goes through each line of file until end is reached
	        while((line = bufferedReader.readLine()) != null) {
	        	//Splits commands at spaces
	            tokens = line.split(" ");
	            //Attempts to process command
	            try {
		            //Processes append commands
		            if (tokens[0].equalsIgnoreCase("a")) {
		    			message.removeAtPosition(message.getCounter() 
		    					- 1);
		    		}
		            //Process insert commands
		    		else if (tokens[0].equalsIgnoreCase("b")) {
		    			int pos;
		    			if (tokens.length == 4) {
		    				pos = Integer.parseInt(tokens[3]);
		    			}
		    			else {
		    				pos = Integer.parseInt(tokens[2]);
		    			}
		    			message.removeAtPosition(pos);
		    		}
		            //Processes remove commands
		    		else if (tokens[0].equalsIgnoreCase("r")) {
		    			Character c;
		    			//Checks if removed character was space
		    			if (tokens[2].equals("%20")) {
		    				c = ' ';
		    			}
		    			//Stores character otherwise
		    			else {
		    				c = tokens[2].charAt(0);
		    			}
		    			//Inserts removed character
		    			int pos = Integer.parseInt(tokens[1]);
		    			message.addBeforePosition(pos, c);
		    		}
		            //Processes switch commands
		    		else if (tokens[0].equalsIgnoreCase("w")) {
		    			int first = Integer.parseInt(tokens[1]);
		    			int second = Integer.parseInt(tokens[2]);
		    			message.switchPositions(first, second);
		    		}
		            //Processes cut commands
		    		else if (tokens[0].equalsIgnoreCase("x")) {
		    			int start = Integer.parseInt(tokens[1]);
		    			//Gets the string that was cut from message
		    			int index = line.indexOf("'");
		    			int end = line.length() - 1;
		    			String adding = line.substring(index + 1, end);
		    			//Pastes string back into message
		    			pasteString(start, adding);
		    		}
		            //Processes paste commands
		    		else if (tokens[0].equalsIgnoreCase("p")) {
		    			int start = Integer.parseInt(tokens[1]);
		    			int end = Integer.parseInt(tokens[2]);
		    			//Cuts out what was pasted
		    			cutToClipboard(start, start + end - 1);
		    		}
	            }
	            catch (Exception e){
	            	throw new Exception();
	            }
	        }
	        // Closes file.
	        bufferedReader.close();
	        
	        str = message.getFinalMessage();
    		return str;
		}
	    catch(FileNotFoundException ex) {
	    	str = "File not found.";
	    }
	    catch(IOException ex) {
	    	str = "Error reading file.";              
	    }
		catch (Exception ex) {
			str = "File corrupted.";
		}
		return str;
	}
	
	//Enters message into LinkedList message
	private void enterInitialMessage(String message) {
		for (int i = 0; i < message.length(); i++) {
			this.message.addToEnd(message.charAt(i));
		}
	}
	
	//Cuts out what was pasted
	private void cutToClipboard(int start, int end) {
		if (start > end  || start < 0 || end > message.getCounter() 
				- 1) {
			throw new IllegalArgumentException();
		}
		for (int i = start; i <= end; i++) {
			message.removeAtPosition(start);
		}
	}
	
	//Pastes given string into message at specified position
	private void pasteString(int start, String toAdd) {
		for (int i = 0; i < toAdd.length(); i++) {
			//Pastes from beginning if message is empty
			if (message.getCounter() == 0) {
				message.addToEnd(toAdd.charAt(i));
			}
			else {
				message.addBeforePosition(start + i, toAdd.charAt(i));
			}
		}
	}
	
	/*****************************************************************
	Prompts user to enter in the mixed up message and the file
	needed to unmix it.
	******************************************************************/
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter in the mixed up message: ");
		String message = scnr.nextLine();
		System.out.println("Enter in file name to unmix message: ");
		String filename = scnr.nextLine();
		UnMix temp = new UnMix(message, filename);
	}
}
