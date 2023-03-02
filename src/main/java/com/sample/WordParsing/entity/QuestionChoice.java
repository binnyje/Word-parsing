package com.sample.WordParsing.entity;

public class QuestionChoice {
	
	private int choiceNo;
    private String choiceText;
    private String correctvalue;
    
    
	public QuestionChoice(int choiceNo, String choiceText, String correctvalue) {
		super();
		this.choiceNo = choiceNo;
		this.choiceText = choiceText;
		this.correctvalue = correctvalue;
	}
	
	public int getChoiceNo() {
		return choiceNo;
	}
	public void setChoiceNo(int choiceNo) {
		this.choiceNo = choiceNo;
	}
	public String getChoiceText() {
		return choiceText;
	}
	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}
	public String getCorrectvalue() {
		return correctvalue;
	}
	public void setCorrectvalue(String correctvalue) {
		this.correctvalue = correctvalue;
	}
    
    

}
