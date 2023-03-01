package com.sample.WordParsing.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.WordParsing.service.WordReaderService;
import com.sample.WordParsing.service.WordReaderServiceImpl;

@RestController
@RequestMapping("/parsing")
public class WordReaderController {
	
@Autowired
WordReaderService service;

@GetMapping("/getQuestions")
public String getDocument(String doc) throws IOException
{
   return service.getDocument(doc);
}
}
