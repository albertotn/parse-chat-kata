package it.albertotn;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public final class ParseChatTest {

	@Test(expected = IllegalArgumentException.class)
	public void nullTest() throws IOException {
		ParseChat parseChat = new ParseChat();
		parseChat.parse(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyTest() throws IOException {
		ParseChat parseChat = new ParseChat();
		parseChat.parse("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void noSeparatorTest() throws IOException {
		ParseChat parseChat = new ParseChat();
		parseChat.parse("Test1");
	}

	@Test
	public void step1Test() throws IOException {
		ParseChat parseChat = new ParseChat();
		String input = parseChat.readFromFile("step1.txt");
		assertTrue(!input.isEmpty());
		String expectedOutput = parseChat.readFromFile("step1-output.txt");
		assertTrue(!input.isEmpty());

		String output = parseChat.parse(input);
		assertTrue(output.equals(expectedOutput));
	}

	@Test
	public void step2Test() throws IOException {
		ParseChat parseChat = new ParseChat();
		String input = parseChat.readFromFile("step2.txt");
		assertTrue(!input.isEmpty());
		String expectedOutput = parseChat.readFromFile("step2-output.txt");
		assertTrue(!input.isEmpty());

		String output = parseChat.parse(input);
		assertTrue(output.equals(expectedOutput));
	}

	@Test
	public void step3Test() throws IOException {
		ParseChat parseChat = new ParseChat();
		String input = parseChat.readFromFile("step3.txt");
		assertTrue(!input.isEmpty());
		String expectedOutput = parseChat.readFromFile("step3-output.txt");
		assertTrue(!input.isEmpty());

		String output = parseChat.parse(input);
		assertTrue(output.equals(expectedOutput));
	}

	@Test
	public void step4Test() throws IOException {
		ParseChat parseChat = new ParseChat();
		String input = parseChat.readFromFile("step4.txt");
		assertTrue(!input.isEmpty());
		String expectedOutput = parseChat.readFromFile("step4-output.txt");
		assertTrue(!input.isEmpty());

		String output = parseChat.parse(input);
		assertTrue(output.equals(expectedOutput));
	}

	@Test
	public void step5Test() throws IOException {
		ParseChat parseChat = new ParseChat();
		String input = parseChat.readFromFile("step5.txt");
		assertTrue(!input.isEmpty());
		String expectedOutput = parseChat.readFromFile("step5-output.txt");
		assertTrue(!input.isEmpty());

		String output = parseChat.parse(input);
		assertTrue(output.equals(expectedOutput));
	}

	@Test
	public void step6Test() throws IOException {
		ParseChat parseChat = new ParseChat();
		String input = parseChat.readFromFile("step6.txt");
		assertTrue(!input.isEmpty());
		String expectedOutput = parseChat.readFromFile("step6-output.txt");
		assertTrue(!input.isEmpty());

		String output = parseChat.parse(input);
		assertTrue(output.equals(expectedOutput));
	}

	@Test
	public void step7Test() throws IOException {
		ParseChat parseChat = new ParseChat();
		String input = parseChat.readFromFile("step7.txt");
		assertTrue(!input.isEmpty());
		String expectedOutput = parseChat.readFromFile("step7-output.txt");
		assertTrue(!input.isEmpty());

		String output = parseChat.parse(input);
		assertTrue(output.equals(expectedOutput));
	}
}
