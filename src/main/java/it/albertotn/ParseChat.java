package it.albertotn;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public final class ParseChat {

	private final static String separator = " : ";
	private List<String> agentDb = new ArrayList<>();

	public ParseChat() {
		this.agentDb.add("Emanuele Querzola");
	}

	public String parse(String input) {
		if (StringUtils.isEmpty(input)) {
			throw new IllegalArgumentException("input must be not null and not empty");
		}
		List<ChatModel> res = new ArrayList<ChatModel>();
		String[] lines = StringUtils.split(input, System.lineSeparator());
		List<String> toProcess = separateLines(lines);
		boolean splittedLines = toProcess.size() != lines.length;
		lines = toProcess.toArray(new String[toProcess.size()]);
		for (int j = 0; j < lines.length; j++) {
			String line = lines[j];
			boolean replacedSeparator = false;
			int separatorIndex = StringUtils.indexOf(line, separator);
			if (separatorIndex < 0) {
				if (!StringUtils.containsAnyIgnoreCase(line, "customer", "agent")) {
					throw new IllegalArgumentException("input is not in the required format, missing ':'");
				}
				line = StringUtils.replaceIgnoreCase(line, "customer ", "Customer : ");
				line = StringUtils.replaceIgnoreCase(line, "agent ", "Agent : ");
				replacedSeparator = true;
			}
			ChatModel chatModel = new ChatModel();
			String[] splitted = StringUtils.splitByWholeSeparator(line, separator);
			for (int i = 0; i < splitted.length; i++) {
				String element = splitted[i];
				if (i == 0) {
					if (element.length() == 0) {
						break;
					}
					chatModel.setDate(element.substring(0, 8));
					chatModel.setMention(element + separator);
					if (replacedSeparator) {
						chatModel.setMention(StringUtils.removeEnd(chatModel.getMention(), ": "));
					}
					if (StringUtils.containsIgnoreCase(element, "customer")) {
						chatModel.setType("customer");
					} else if (StringUtils.containsIgnoreCase(element, "agent")) {
						chatModel.setType("agent");
					} else {
						String name = StringUtils.strip(element.substring(8));
						if (agentDb.contains(name)) {
							chatModel.setType("agent");
						} else {
							chatModel.setType("customer");
						}
					}
				}
				if (i == 1) {
					if (element.length() == 0) {
						break;
					}
					chatModel.setSentence(element);
					if (j + 1 < lines.length) {
						String terminator = "\\n";
						if (splittedLines) {
							terminator = "";
						}
						chatModel.setSentence(chatModel.getSentence() + terminator);
					}
				}
			}
			res.add(chatModel);
		}
		return getAsString(res);
	}

	private List<String> separateLines(String[] lines) {
		if (lines == null || lines.length == 0) {
			return new ArrayList<>();
		}
		List<String> toProcess = Arrays.asList(lines);
		List<String> res = new ArrayList<>();
		for (String line : toProcess) {
			if (countMatch(line, "\\.\\d\\d:\\d\\d:\\d\\d") > 1) {
				String[] elems = line.split("\\.\\d\\d:\\d\\d:\\d\\d");
				for (int i = 0; i < elems.length; i++) {
					if (i == 0) {
						res.add(elems[0] + ".");
					}
					if (i == 1) {
						res.add(StringUtils.substring(line, elems[0].length() + 1));
					}
				}

			} else {
				res.add(line);
			}
		}
		return res;
	}

	private int countMatch(String str, String pattern) {
		if (str == null || pattern == null) {
			return 0;
		}
		String[] res = str.split(pattern);
		return res.length;
	}

	private String getAsString(List<ChatModel> models) {
		StringBuilder builder = new StringBuilder();
		if (models == null || models.isEmpty()) {
			return "";
		}
		builder.append("[");
		Iterator<ChatModel> iter = models.iterator();
		while (iter.hasNext()) {
			ChatModel chatModel = iter.next();
			builder.append("{").append(System.lineSeparator());
			builder.append("  date: '" + chatModel.getDate() + "',").append(System.lineSeparator());
			builder.append("  mention: '" + chatModel.getMention() + "',").append(System.lineSeparator());
			builder.append("  sentence: '" + chatModel.getSentence() + "',").append(System.lineSeparator());
			builder.append("  type: '" + chatModel.getType() + "'").append(System.lineSeparator());
			builder.append("}");
			if (iter.hasNext()) {
				builder.append(", ");
			}
		}
		builder.append("]").append(System.lineSeparator());
		return builder.toString();
	}

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.err.println("Warning: ParseChat need a filename as input");
			return;
		} else if (args.length > 1) {
			System.err.println("Warning: ParseChat need a single a filename as input");
			return;
		}
		ParseChat parser = new ParseChat();
		try {
			String res = parser.parse(parser.readFromFile(args[0]));
			System.out.println(res);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public String readFromFile(String fileName) throws IOException {
		return FileUtils.readFileToString(readFile(fileName), Charset.forName("UTF-8"));
	}

	private File readFile(String name) {
		File file = new File(name);
		if (file == null || !file.exists()) {
			throw new IllegalArgumentException("File not found or does not exists with name : " + name);
		}
		return file;
	}

}
