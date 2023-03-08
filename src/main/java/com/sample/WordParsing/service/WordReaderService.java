package com.sample.WordParsing.service;

import java.io.IOException;
import java.util.List;

import com.sample.WordParsing.entity.QuestionGroup;
import com.sample.WordParsing.entity.Question;

public interface WordReaderService {

	public Question getDocument(String  doc) throws IOException;

	public List<QuestionGroup> getDocument() throws IOException;

}