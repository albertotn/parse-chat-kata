package it.albertotn;

public final class ChatModel {

	private String date;
	private String mention;
	private String sentence;
	private String type;

	public ChatModel() {
	}

	public ChatModel(String date, String mention, String sentence, String type) {
		this.date = date;
		this.mention = mention;
		this.sentence = sentence;
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMention() {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
