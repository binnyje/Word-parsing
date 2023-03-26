package com.sample.WordParsing.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.WordParsing.entity.QuestionGroup;
import com.sample.WordParsing.entity.Question;
//import com.sample.WordParsing.service.$missing$;
import com.sample.WordParsing.service.WordReaderService;

@RestController
@RequestMapping("/parsing")
public class WordReaderController {
	
@Autowired
WordReaderService service;


@PostMapping("/getQuestions")
public List<QuestionGroup> getDocument(@RequestParam("file") MultipartFile multipartFile) throws IOException
{
   return service.getDocument(multipartFile);
}


}


