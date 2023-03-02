package com.sample.WordParsing.entity;

import java.util.List;

public class QuestionGroup {
	
	private List<Questions> ques;
	private String content;
	private String contextImages;
	
	
	public QuestionGroup(List<Questions> ques, String content, String contextImages) {
		super();
		this.ques = ques;
		this.content = content;
		this.contextImages = contextImages;
	}
	
	public List<Questions> getQues() {
		return ques;
	}
	public void setQues(List<Questions> ques) {
		this.ques = ques;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContextImages() {
		return contextImages;
	}
	public void setContextImages(String contextImages) {
		this.contextImages = contextImages;
	}
	
	
	

}
