package project4;
import static org.junit.Assert.*;

import org.junit.Test;
public class UnMixTest {
	@Test 
    public void testUndoProcessCommand() {
        	// Creates the file for testing, 
    		// this code could be removed if the file already exist
    		Mix message = new Mix();
        	message.setInitialMessage ("This is a secret message");
        	String userMessage = message.processCommand("b a 0");
        	message.processCommand("s testIt");
      
        	UnMix unMessage = new UnMix();
        	String original = unMessage.UnMixUsingFile ("testIt", 
        			userMessage);
        	assertEquals(original, "This is a secret message");
	}
	@Test 
    public void testUndoRemoveBeginning() {
        	// Creates the file for testing, 
    		// this code could be removed if the file already exist
    		Mix message = new Mix();
        	message.setInitialMessage ("This is a secret message");
        	String userMessage = message.processCommand("r 0");
        	message.processCommand("s testing.txt");
      
        	UnMix unMessage = new UnMix();
        	String original = unMessage.UnMixUsingFile ("testing.txt", 
        			userMessage);
        	assertEquals(original, "This is a secret message");
	}
	@Test 
    public void testUndoRemoveSpace() {
        	// Creates the file for testing, 
    		// this code could be removed if the file already exist
    		Mix message = new Mix();
        	message.setInitialMessage ("This is a secret message");
        	String userMessage = message.processCommand("r 4");
        	message.processCommand("s testing.txt");
      
        	UnMix unMessage = new UnMix();
        	String original = unMessage.UnMixUsingFile ("testing.txt", 
        			userMessage);
        	assertEquals(original, "This is a secret message");
	}
	@Test 
    public void testUndoTestAllWithoutEndErase() {
        	UnMix unMessage = new UnMix();
        	String original = unMessage.UnMixUsingFile("testingAll.txt", 
        			"sting 0t3 all co0t3mmnds.eWsting ");
        	assertEquals(original, "Testing all commands.");
	}
	@Test 
    public void testUndoTestAll2() {
        	UnMix unMessage = new UnMix();
        	String original = unMessage.UnMixUsingFile("testingAll2."
        			+ "txt", "");
        	assertEquals(original, "Testing all commands.");
	}
	@Test
	public void testConstructUnMix() {
		UnMix message = new UnMix("","testingAll2.txt");
	}
}
