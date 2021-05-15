package it.albertotn;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public final class ParseChat {

	private final static String separator = " : ";

	public ParseChat() {
	}

	public String parse(String input) {
		if (StringUtils.isEmpty(input)) {
			throw new IllegalArgumentException("input must be not null and not empty");
		}
		int separatorIndex = StringUtils.indexOf(input, separator);
		if (separatorIndex < 0) {
			throw new IllegalArgumentException("input is not in the required format, missing ':'");
		}
		List<ChatModel> res = new ArrayList<ChatModel>();
		ChatModel chatModel = new ChatModel();
		String[] splitted = StringUtils.splitByWholeSeparator(input, separator);
		for (int i = 0; i < splitted.length; i++) {
			String element = splitted[i];
			if (i == 0) {
				if (element.length() == 0 || element.length() < 17) {
					break;
				}
				chatModel.setDate(element.substring(0, 8));
				chatModel.setMention(element.substring(0, 17) + separator);
				if (StringUtils.containsIgnoreCase(element, "customer")) {
					chatModel.setType("customer");
				}
			}
			if (i == 1) {
				if (element.length() == 0) {
					break;
				}
				chatModel.setSentence(element);
			}
		}
		res.add(chatModel);
		return getAsString(res);
	}

	private String getAsString(List<ChatModel> models) {
		StringBuilder builder = new StringBuilder();
		if (models == null || models.isEmpty()) {
			return "";
		}
		builder.append("[{").append(System.lineSeparator());
		for (ChatModel chatModel : models) {
			builder.append("  date: '" + chatModel.getDate() + "',").append(System.lineSeparator());
			builder.append("  mention: '" + chatModel.getMention() + "',").append(System.lineSeparator());
			builder.append("  sentence: '" + chatModel.getSentence() + "',").append(System.lineSeparator());
			builder.append("  type: '" + chatModel.getType() + "'").append(System.lineSeparator());
		}
		builder.append("}]").append(System.lineSeparator());
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
