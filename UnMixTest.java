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
	public void testUndoRemoveMiddle1() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 4");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing remove.", original);
	}
	//Fix assert equals
	@Test
	public void testUndoRemoveEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 14");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing remove.", original);
	}
	@Test
	public void testUndoRemoveEndSpace() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove ");
		String userMessage = message.processCommand("r 14");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing remove ", original);
	}
	@Test
	public void testUndoAppendNumber() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a 3");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing append.", original);
	}
	@Test
	public void testUndoAppendLetter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a H");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing append.", original);
	}
	@Test
	public void testUndoAppendOther() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a &");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing append.", original);
	}
	@Test
	public void testUndoAppendSpace() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a  ");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing append.", original);
	}
	@Test
	public void testUndoInsertBeginningNumber() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b 5 0");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing insert.", original);
	}
	@Test
	public void testUndoInsertBeginningOther() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b * 0");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing insert.", original);
	}
	@Test
	public void testUndoInsertMiddleNumber() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b 8 5");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing insert.", original);
	}
	@Test
	public void testUndoInsertMiddleLetter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b X 8");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing insert.", original);
	}
	@Test
	public void testUndoInsertEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b # 15");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing insert.", original);
	}
	@Test
	public void testUndoSwitchSelf() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("w 3 3");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing insert.", original);
	}
	@Test
	public void testUndoSwitchMiddle() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 4 12");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoSwitchMiddleOtherOrder() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 12 4");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoSwitchOutsides() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 0 14");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoSwitchMiddleWithSpace() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 7 12");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoCutMiddleLetter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 4 4");
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoCutMiddleLetters() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 4 8");
		assertEquals("Testut.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoCutBeginning() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 0 3");
		assertEquals("ing cut.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoCutEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 4 11");
		assertEquals("Test", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoCutAll() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 0 11");
		assertEquals("", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoPasteMiddleLetter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		message.processCommand("x 5 5");
		String userMessage = message.processCommand("p 12");
		assertEquals("Testig pasten.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing paste.", original);
	}
	@Test
	public void testUndoPasteMiddleLetters() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		message.processCommand("x 5 9");
		String userMessage = message.processCommand("p 2");
		assertEquals("Teng pastiste.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing paste.", original);
	}
	@Test
	public void testUndoPasteFront() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		message.processCommand("x 1 7");
		String userMessage = message.processCommand("p 0");
		assertEquals("esting Tpaste.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing paste.", original);
	}
	@Test
	public void testUndoPasteEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		message.processCommand("x 4 6");
		String userMessage = message.processCommand("p 11");
		assertEquals("Test paste.ing", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing paste.", original);
	}
	@Test
	public void testUndoCopy() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		String userMessage = message.processCommand("c 4 6");
		assertEquals("Testing copy.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing copy.", original);
	}
	@Test
	public void testUndoCopyFront() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		message.processCommand("c 0 3");
		String userMessage = message.processCommand("p 12");
		assertEquals("Testing copyTest.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing copy.", original);
	}
	@Test
	public void testUndoCopyEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		message.processCommand("c 8 12");
		String userMessage = message.processCommand("p 0");
		assertEquals("copy.Testing copy.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing copy.", original);
	}
	@Test
	public void testUndoCopyAll() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		message.processCommand("c 0 12");
		String userMessage = message.processCommand("p 13");
		assertEquals("Testing copy.Testing copy.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing copy.", original);
	}
	//Tests that save doesn't throw any errors
	@Test
	public void testUndoSave() {
		Mix message = new Mix();
		message.setInitialMessage("Testing save.");
		String userMessage = message.processCommand("s file.txt");
		assertEquals("Testing save.", userMessage);
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("file.txt", 
				userMessage);
		assertEquals("Testing save.", original);
	}
	@Test
	public void testUndoIncorrectAppend() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a");
		assertEquals("Testing append.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing append.", original);
	}
	@Test
	public void testUndoIncorrectInsert() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b a i");
		assertEquals("Testing insert.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing insert.", original);
	}
	@Test
	public void testUndoIncorrectInsertMissingPart() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b");
		assertEquals("Testing insert.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing insert.", original);
	}
	@Test
	public void testUndoIncorrectRemove() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r g");
		assertEquals("Testing remove.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing remove.", original);
	}
	@Test
	public void testUndoRemoveMissingPart() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r ");
		assertEquals("Testing remove.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing remove.", original);
	}
	@Test
	public void testUndoIncorrectRemoveOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 100");
		assertEquals("Testing remove.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing remove.", original);
	}
	@Test
	public void testUndoIncorrectRemoveOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r -1");
		assertEquals("Testing remove.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing remove.", original);
	}
	@Test
	public void testUndoIncorrectRemoveBarelyOutOfBounds() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 15");
		assertEquals("Testing remove.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing remove.", original);
	}
	@Test
	public void testUndoIncorrectSwitch() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2");
		assertEquals("Testing switch.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoIncorrectSwitchOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2 45");
		assertEquals("Testing switch.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoIncorrectSwitchOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2 -2");
		assertEquals("Testing switch.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoIncorrectSwitchBarelyOutOfBounds() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2 15");
		assertEquals("Testing switch.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoIncorrectSwitchCharacter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2 a");
		assertEquals("Testing switch.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing switch.", original);
	}
	@Test
	public void testUndoIncorrectCut() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x");
		assertEquals("Testing cut.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoIncorrectCutOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 0 30");
		assertEquals("Testing cut.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoIncorrectCutOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x -1 2");
		assertEquals("Testing cut.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoIncorrectCutChars() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 0 e");
		assertEquals("Testing cut.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing cut.", original);
	}
	@Test
	public void testUndoIncorrectPaste() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		String userMessage = message.processCommand("p ");
		assertEquals("Testing paste.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing paste.", original);
	}
	@Test
	public void testUndoIncorrectPasteOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		String userMessage = message.processCommand("p 15");
		assertEquals("Testing paste.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing paste.", original);
	}
	@Test
	public void testUndoIncorrectPasteOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		String userMessage = message.processCommand("p -1");
		assertEquals("Testing paste.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing paste.", original);
	}
	@Test
	public void testUndoIncorrectPasteChar() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		String userMessage = message.processCommand("p a");
		assertEquals("Testing paste.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing paste.", original);
	}
	@Test
	public void testUndoIncorrectCopy() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		String userMessage = message.processCommand("c 0 ");
		assertEquals("Testing copy.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing copy.", original);
	}
	@Test
	public void testUndoIncorrectCopyOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		String userMessage = message.processCommand("c 5 13");
		assertEquals("Testing copy.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing copy.", original);
	}
	@Test
	public void testUndoIncorrectCopyOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		String userMessage = message.processCommand("c -1 3");
		assertEquals("Testing copy.", userMessage);
		message.processCommand("s unmix.txt");
		
		UnMix unMessage = new UnMix();
		String original = unMessage.UnMixUsingFile("unmix.txt", 
				userMessage);
		assertEquals("Testing copy.", original);
	}
	@Test 
    	public void testUndoUndoTestAllWithoutEndErase() {
        	UnMix unMessage = new UnMix();
        	String original = unMessage.UnMixUsingFile("testingAll.txt", 
        			"sting 0t3 all co0t3mmnds.eWsting ");
        	assertEquals(original, "Testing all commands.");
	}
	@Test 
        public void testUndoUndoTestAll2() {
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
