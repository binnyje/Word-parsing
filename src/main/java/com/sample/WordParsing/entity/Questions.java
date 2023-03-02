package com.sample.WordParsing.entity;

import java.util.*;


public class Questions {
	private String question;
	private String questionImage;
	private List<QuestionChoice> choices;
	
	

	public Questions(String question, String questionImage, List<QuestionChoice> choices) {
		super();
		this.question = question;
		this.questionImage = questionImage;
		this.choices = choices;
	}

	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionImage() {
		return questionImage;
	}
	public void setQuestionImage(String questionImage) {
		this.questionImage = questionImage;
	}

	public List<QuestionChoice> getChoices() {
		return choices;
	}

	public void setChoices(List<QuestionChoice> choices) {
		this.choices = choices;
	}

}
