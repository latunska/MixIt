package project4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class UnMix implements IUnMix {
	/** Linked list of characters representing a message (string) */ 
	private LinkedList<Character> message;
	
	private LinkedList<Character> clipboard;
	
	public UnMix() {
		message = new LinkedList<Character>();
		clipboard = new LinkedList<Character>();
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter in the mixed up message: ");
		String message = scnr.nextLine();
		System.out.println("Enter in file name to unmix message: ");
		String filename = scnr.nextLine();
		try {
			System.out.println(UnMixUsingFile(filename, message));
		} catch (Exception e) {
			System.out.println("File has been corrupted.");
		}
	}

	@Override
	public String UnMixUsingFile(String filename, String mixedMessage) throws Exception {
		enterInitialMessage(mixedMessage);
		// This will reference one line at a time
	    String line = null;
	    String[]tokens;
	    
		try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(filename);
	
	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	        while((line = bufferedReader.readLine()) != null) {
	            tokens = line.split(" ");
	            try {
		            //Done, somewhat tested.
		            if (tokens[0].equalsIgnoreCase("a")) {
		    			Character adding = tokens[1].charAt(0);
		    			message.removeAtPosition(message.getCounter() - 1);
		    		}
		            //Done, not tested
		    		else if (tokens[0].equalsIgnoreCase("b")) {
		    			int pos = Integer.parseInt(tokens[2]);
		    			message.removeAtPosition(pos);
		    		}
		            //Done, somewhat tested.
		    		else if (tokens[0].equalsIgnoreCase("r")) {
		    			Character c;
		    			if (tokens[2].equals("%20")) {
		    				c = ' ';
		    			}
		    			else {
		    				c = tokens[2].charAt(0);
		    			}
		    			int pos = Integer.parseInt(tokens[1]);
		    			message.addBeforePosition(pos, c);
		    		}
		            //Nothing changed, might work?
		    		else if (tokens[0].equalsIgnoreCase("w")) {
		    			int first = Integer.parseInt(tokens[1]);
		    			int second = Integer.parseInt(tokens[2]);
		    			message.switchPositions(first, second);
		    		}
		    		else if (tokens[0].equalsIgnoreCase("x")) {
		    			int start = Integer.parseInt(tokens[1]);
		    			int index = line.indexOf("'");
		    			int end = line.length() - 1;
		    			String adding = line.substring(index + 1, end);
		    			pasteString(start, adding);
		    		}
		            //Tested once and fixed
		    		else if (tokens[0].equalsIgnoreCase("p")) {
		    			int start = Integer.parseInt(tokens[1]);
		    			int end = Integer.parseInt(tokens[2]);
		    			cutToClipboard(start + 1, start + end);
		    		}
		            //Done?
		    		else if (tokens[0].equalsIgnoreCase("c")) {
		    		}
		    		else if (tokens[0].equalsIgnoreCase("s")) {
		    			
		    		}
	            }
	            catch (Exception e){
	            	throw new Exception();
	            }
	        }
	
	        // Always close files.
	        bufferedReader.close();
	        
	        String str = message.getFinalMessage();
    		return str;
		}
	    catch(FileNotFoundException ex) {
	    	JOptionPane.showMessageDialog(null, 
	            "Unable to open file '" + 
	            filename + "");                
	    }
	    catch(IOException ex) {
	    	JOptionPane.showMessageDialog(null, 
	            "Error reading file '" 
	            + filename + "");                  
	    }
		return null;
	}
	
	private void enterInitialMessage(String message) {
		for (int i = 0; i < message.length(); i++) {
			this.message.addToEnd(message.charAt(i));
		}
	}
	
	private void cutToClipboard(int start, int end) {
		if (start > end ) {
			throw new IllegalArgumentException();
		}
		if (start < 0 ) {
			throw new IllegalArgumentException();
		}
		if (end > message.getCounter() - 1) {
			throw new IllegalArgumentException();
		}
		clipboard.deleteAll();
		for (int i = start; i <= end; i++) {
			clipboard.addToEnd(message.removeAtPosition(start));
		}
	}
	
	private void pasteFromClipboard(int start) {
		if (start < 0 || start > message.getCounter() - 1) {
			throw new IllegalArgumentException();
		}
		//Iterates through clipboard and adds to message
		else {
			for (int i = 1; i < clipboard.getCounter() + 1; i++) {
				message.addBeforePosition(start + i, clipboard.copyAtPosition(i));
			}
		}
	}
	
	public void pasteString(int start, String toAdd) {
		for (int i = 0; i < toAdd.length(); i++) {
			if (start == 0) {
				message.addToEnd(toAdd.charAt(i));
			}
			else {
				message.addBeforePosition(start + i, toAdd.charAt(i));
			}
		}
	}
	
	public static void main(String[] args) {
		UnMix temp = new UnMix();
	}
}
