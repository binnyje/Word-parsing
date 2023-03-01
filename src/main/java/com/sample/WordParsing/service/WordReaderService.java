package com.sample.WordParsing.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public interface WordReaderService {

	public String getDocument(String  doc) throws IOException;

	

}
