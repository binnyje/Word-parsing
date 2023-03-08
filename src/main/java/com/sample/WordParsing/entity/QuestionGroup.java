package com.sample.WordParsing.entity;

import java.util.*;


public class QuestionGroup {
	
	private List<Question> questionGroup;
	
	private String context;
	
	List<String> contextImages=new ArrayList<String>(); 
	
	
	public QuestionGroup(List<Question> questionGroup, String context) {
		super();
		this.setQuestionGroup(questionGroup);
		this.context = context;
		//this.contextImages = contextImages;
	}
	
	public QuestionGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	public List<String> getContextImages() {
		return contextImages;
	}

	public void setContextImages(List<String> contextImages) {
		this.contextImages = contextImages;
	}

	public List<Question> getQuestionGroup() {
		return questionGroup;
	}

	public void setQuestionGroup(List<Question> questionGroup) {
		this.questionGroup = questionGroup;
	}

	
	
	

}
