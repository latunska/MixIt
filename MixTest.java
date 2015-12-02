package project4;
import static org.junit.Assert.*;

import org.junit.Test;

public class MixTest {
	@Test 
    	public void testProcessCommand() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = message.processCommand("b a 0");
        assertEquals("aThis is a secret message", userMessage);
	}
	@Test
	public void testRemoveBeginning() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 0");
		assertEquals("esting remove.", userMessage);
	}
	@Test
	public void testRemoveMiddle1() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 4");
		assertEquals("Testng remove.", userMessage);
	}
	@Test
	public void testRemoveSpace() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 7");
		assertEquals("Testingremove.", userMessage);
	}
	@Test
	public void testRemoveEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 14");
		assertEquals("Testing remove", userMessage);
	}
	@Test
	public void testRemoveEndSpace() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove ");
		String userMessage = message.processCommand("r 14");
		assertEquals("Testing remove", userMessage);
	}
	@Test
	public void testAppendNumber() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a 3");
		assertEquals("Testing append.3", userMessage);
	}
	@Test
	public void testAppendLetter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a H");
		assertEquals("Testing append.H", userMessage);
	}
	@Test
	public void testAppendOther() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a &");
		assertEquals("Testing append.&", userMessage);
	}
	@Test
	public void testAppendSpace() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a  ");
		assertEquals("Testing append. ", userMessage);
	}
	@Test
	public void testInsertBeginningNumber() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b 5 0");
		assertEquals("5Testing insert.", userMessage);
	}
	@Test
	public void testInsertBeginningOther() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b * 0");
		assertEquals("*Testing insert.", userMessage);
	}
	@Test
	public void testInsertMiddleNumber() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b 8 5");
		assertEquals("Testi8ng insert.", userMessage);
	}
	@Test
	public void testInsertMiddleLetter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b X 8");
		assertEquals("Testing Xinsert.", userMessage);
	}
	@Test
	public void testInsertEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b # 15");
		assertEquals("Testing insert.#", userMessage);
	}
	@Test
	public void testSwitchSelf() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("w 3 3");
		assertEquals("Testing insert.", userMessage);
	}
	@Test
	public void testSwitchMiddle() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 4 12");
		assertEquals("Testcng switih.", userMessage);
	}
	@Test
	public void testSwitchMiddleOtherOrder() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 12 4");
		assertEquals("Testcng switih.", userMessage);
	}
	@Test
	public void testSwitchOutsides() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 0 14");
		assertEquals(".esting switchT", userMessage);
	}
	@Test
	public void testSwitchMiddleWithSpace() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 7 12");
		assertEquals("Testingcswit h.", userMessage);
	}
	@Test
	public void testCutMiddleLetter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 4 4");
		assertEquals("Testng cut.", userMessage);
	}
	@Test
	public void testCutMiddleLetters() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 4 8");
		assertEquals("Testut.", userMessage);
	}
	@Test
	public void testCutBeginning() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 0 3");
		assertEquals("ing cut.", userMessage);
	}
	@Test
	public void testCutEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 4 11");
		assertEquals("Test", userMessage);
	}
	@Test
	public void testCutAll() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 0 11");
		assertEquals("", userMessage);
	}
	@Test
	public void testPasteMiddleLetter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		message.processCommand("x 5 5");
		String userMessage = message.processCommand("p 12");
		assertEquals("Testig pasten.", userMessage);
	}
	@Test
	public void testPasteMiddleLetters() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		message.processCommand("x 5 9");
		String userMessage = message.processCommand("p 2");
		assertEquals("Teng pastiste.", userMessage);
	}
	@Test
	public void testPasteFront() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		message.processCommand("x 1 7");
		String userMessage = message.processCommand("p 0");
		assertEquals("esting Tpaste.", userMessage);
	}
	@Test
	public void testPasteEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		message.processCommand("x 4 6");
		String userMessage = message.processCommand("p 11");
		assertEquals("Test paste.ing", userMessage);
	}
	@Test
	public void testCopy() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		String userMessage = message.processCommand("c 4 6");
		assertEquals("Testing copy.", userMessage);
	}
	@Test
	public void testCopyFront() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		message.processCommand("c 0 3");
		String userMessage = message.processCommand("p 12");
		assertEquals("Testing copyTest.", userMessage);
	}
	@Test
	public void testCopyEnd() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		message.processCommand("c 8 12");
		String userMessage = message.processCommand("p 0");
		assertEquals("copy.Testing copy.", userMessage);
	}
	@Test
	public void testCopyAll() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		message.processCommand("c 0 12");
		String userMessage = message.processCommand("p 13");
		assertEquals("Testing copy.Testing copy.", userMessage);
	}
	//Tests that save doesn't throw any errors
	@Test
	public void testSave() {
		Mix message = new Mix();
		message.setInitialMessage("Testing save.");
		String userMessage = message.processCommand("s file.txt");
		assertEquals("Testing save.", userMessage);
	}
	@Test
	public void testIncorrectAppend() {
		Mix message = new Mix();
		message.setInitialMessage("Testing append.");
		String userMessage = message.processCommand("a");
		assertEquals("Testing append.", userMessage);
	}
	@Test
	public void testIncorrectInsert() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b a i");
		assertEquals("Testing insert.", userMessage);
	}
	@Test
	public void testIncorrectInsertMissingPart() {
		Mix message = new Mix();
		message.setInitialMessage("Testing insert.");
		String userMessage = message.processCommand("b");
		assertEquals("Testing insert.", userMessage);
	}
	@Test
	public void testIncorrectRemove() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r g");
		assertEquals("Testing remove.", userMessage);
	}
	@Test
	public void testRemoveMissingPart() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r ");
		assertEquals("Testing remove.", userMessage);
	}
	@Test
	public void testIncorrectRemoveOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 100");
		assertEquals("Testing remove.", userMessage);
	}
	@Test
	public void testIncorrectRemoveOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r -1");
		assertEquals("Testing remove.", userMessage);
	}
	@Test
	public void testIncorrectRemoveBarelyOutOfBounds() {
		Mix message = new Mix();
		message.setInitialMessage("Testing remove.");
		String userMessage = message.processCommand("r 15");
		assertEquals("Testing remove.", userMessage);
	}
	@Test
	public void testIncorrectSwitch() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2");
		assertEquals("Testing switch.", userMessage);
	}
	@Test
	public void testIncorrectSwitchOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2 45");
		assertEquals("Testing switch.", userMessage);
	}
	@Test
	public void testIncorrectSwitchOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2 -2");
		assertEquals("Testing switch.", userMessage);
	}
	@Test
	public void testIncorrectSwitchBarelyOutOfBounds() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2 15");
		assertEquals("Testing switch.", userMessage);
	}
	@Test
	public void testIncorrectSwitchCharacter() {
		Mix message = new Mix();
		message.setInitialMessage("Testing switch.");
		String userMessage = message.processCommand("w 2 a");
		assertEquals("Testing switch.", userMessage);
	}
	@Test
	public void testIncorrectCut() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x");
		assertEquals("Testing cut.", userMessage);
	}
	@Test
	public void testIncorrectCutOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 0 30");
		assertEquals("Testing cut.", userMessage);
	}
	@Test
	public void testIncorrectCutOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x -1 2");
		assertEquals("Testing cut.", userMessage);
	}
	@Test
	public void testIncorrectCutChars() {
		Mix message = new Mix();
		message.setInitialMessage("Testing cut.");
		String userMessage = message.processCommand("x 0 e");
		assertEquals("Testing cut.", userMessage);
	}
	@Test
	public void testIncorrectPaste() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		String userMessage = message.processCommand("p ");
		assertEquals("Testing paste.", userMessage);
	}
	@Test
	public void testIncorrectPasteOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		String userMessage = message.processCommand("p 15");
		assertEquals("Testing paste.", userMessage);
	}
	@Test
	public void testIncorrectPasteOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		String userMessage = message.processCommand("p -1");
		assertEquals("Testing paste.", userMessage);
	}
	@Test
	public void testIncorrectPasteChar() {
		Mix message = new Mix();
		message.setInitialMessage("Testing paste.");
		String userMessage = message.processCommand("p a");
		assertEquals("Testing paste.", userMessage);
	}
	@Test
	public void testIncorrectCopy() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		String userMessage = message.processCommand("c 0 ");
		assertEquals("Testing copy.", userMessage);
	}
	@Test
	public void testIncorrectCopyOutOfBoundsUpper() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		String userMessage = message.processCommand("c 5 13");
		assertEquals("Testing copy.", userMessage);
	}
	@Test
	public void testIncorrectCopyOutOfBoundsLower() {
		Mix message = new Mix();
		message.setInitialMessage("Testing copy.");
		String userMessage = message.processCommand("c -1 3");
		assertEquals("Testing copy.", userMessage);
	}
	@Test
	public void testAll() {
		Mix message = new Mix();
		message.setInitialMessage("Testing all commands.");
		String userMessage = message.processCommand("H");
		assertEquals("Testing all commands.", userMessage);
		userMessage = message.processCommand("a 0");
		assertEquals("Testing all commands.0", userMessage);
		userMessage = message.processCommand("a W");
		assertEquals("Testing all commands.0W", userMessage);
		userMessage = message.processCommand("a  ");
		assertEquals("Testing all commands.0W ", userMessage);
		userMessage = message.processCommand("a");
		assertEquals("Testing all commands.0W ", userMessage);
		userMessage = message.processCommand("a");
		assertEquals("Testing all commands.0W ", userMessage);
		userMessage = message.processCommand("b t 2");
		assertEquals("Tetsting all commands.0W ", userMessage);
		userMessage = message.processCommand("b 3 3");
		assertEquals("Tet3sting all commands.0W ", userMessage);
		userMessage = message.processCommand("b   4");
		assertEquals("Tet3 sting all commands.0W ", userMessage);
		userMessage = message.processCommand("b $ $");
		assertEquals("Tet3 sting all commands.0W ", userMessage);
		userMessage = message.processCommand("b");
		assertEquals("Tet3 sting all commands.0W ", userMessage);
		userMessage = message.processCommand("h");
		assertEquals("Tet3 sting all commands.0W ", userMessage);
		userMessage = message.processCommand("r 19");
		assertEquals("Tet3 sting all commnds.0W ", userMessage);
		userMessage = message.processCommand("r 0");
		assertEquals("et3 sting all commnds.0W ", userMessage);
		userMessage = message.processCommand("r 24");
		assertEquals("et3 sting all commnds.0W", userMessage);
		userMessage = message.processCommand("r 24");
		assertEquals("et3 sting all commnds.0W", userMessage);
		userMessage = message.processCommand("r -1");
		assertEquals("et3 sting all commnds.0W", userMessage);
		userMessage = message.processCommand("r a");
		assertEquals("et3 sting all commnds.0W", userMessage);
		userMessage = message.processCommand("w 0 22");
		assertEquals("0t3 sting all commnds.eW", userMessage);
		userMessage = message.processCommand("w 10 10");
		assertEquals("0t3 sting all commnds.eW", userMessage);
		message.processCommand("w -1 22");
		message.processCommand("w 2 24");
		message.processCommand("w a b");
		userMessage = message.processCommand("klsdfjvn???#");
		assertEquals("0t3 sting all commnds.eW", userMessage);
		userMessage = message.processCommand("x 4 9");
		assertEquals("0t3 all commnds.eW", userMessage);
		userMessage = message.processCommand("p 0");
		assertEquals("sting 0t3 all commnds.eW", userMessage);
		message.processCommand("x 14 24");
		message.processCommand("x -1 2");
		userMessage = message.processCommand("x d 6");
		assertEquals("sting 0t3 all commnds.eW", userMessage);
		userMessage = message.processCommand("p 24");
		assertEquals("sting 0t3 all commnds.eWsting ", userMessage);
		userMessage = message.processCommand("c 6 8");
		assertEquals("sting 0t3 all commnds.eWsting ", userMessage);
		userMessage = message.processCommand("p 16");
		assertEquals("sting 0t3 all co0t3mmnds.eWsting ", userMessage);
		message.processCommand("p -1");
		message.processCommand("p 34");
		message.processCommand("p e");
		message.processCommand("c -1 4");
		userMessage = message.processCommand("c 23 37");
		assertEquals("sting 0t3 all co0t3mmnds.eWsting ", userMessage);
		message.processCommand("s testingAll.txt");
		userMessage = message.processCommand("x 0 32");
		assertEquals("", userMessage);
		message.processCommand("s testingAll2.txt");
	}
}
