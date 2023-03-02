package com.sample.WordParsing.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import com.sample.WordParsing.entity.Questions;
import com.sample.WordParsing.entity.QuestionChoice;
import com.sample.WordParsing.entity.QuestionGroup;

@Service
public class WordReaderServiceImpl implements WordReaderService
{
	
     public List<QuestionGroup> getDocument()throws IOException
     {
       
		String fileName = "/Users/binnyjayakumaremily/bulkquestions.docx.doc";

     
        XWPFDocument docm = new XWPFDocument(Files.newInputStream(Paths.get(fileName)));
        List<IBodyElement> bodyElements = docm.getBodyElements();
        
        List<XWPFParagraph> paragraphs = docm.getParagraphs();
        List<XWPFPictureData> piclist = docm.getAllPictures();
        
        for(XWPFParagraph para : paragraphs) {  
        	// Pattern p = Pattern.compile("D"+"\\."+"[51-100]"+"\\-"+"[51-100]"+"\\)");
       		// Matcher matcher = p.matcher(para);
       		// while(matcher.find())
       		// {
       			// System.out.println(matcher.group());
       		// }
             
        	System.out.println(para.getText());     
        	for(XWPFRun run : para.getRuns()) {
                System.out.println(run.getEmbeddedPictures().size());
                	if(run.getEmbeddedPictures().size() == 1)
                	{
                		System.out.println(run.getEmbeddedPictures());
                	}	   
        }
        
        }
		
        
 
              
        docm.close(); 
			
	return getQuestiongroup();
			
	}
	
	
	public List<QuestionGroup> getQuestiongroup()
	{
		List<QuestionGroup> group = new ArrayList<QuestionGroup>();
		QuestionGroup qg1 = new QuestionGroup(getQuestions(), "graph", "graph1");
		QuestionGroup qg2 = new QuestionGroup(getQuestions(), "graph", "graph1");
		QuestionGroup qg3 = new QuestionGroup(getQuestions(), "graph", "graph1");
		group.add(qg1);
		group.add(qg2);
		group.add(qg3);
		return group;
	}
	
	public List<Questions> getQuestions()
	{
		 List<Questions> testquestion = new ArrayList<Questions>();
		 Questions q1 = new Questions("Question1", "QuesImage", getChoice());
		 Questions q2 = new Questions("Question1", "QuesImage", getChoice());
		 Questions q3 = new Questions("Question1", "QuesImage", getChoice());
		 testquestion.add(q1);
		 testquestion.add(q2);
		 testquestion.add(q3);
	     //testquestion.setQuestion("Question");
	     //testquestion.setQuestionImage("questionimage");
	     //testquestion.setChoices(getChoice());
	     return testquestion;
	}
	
	
	public List<QuestionChoice> getChoice()
	{
		List<QuestionChoice> choice = new ArrayList<QuestionChoice>();
		QuestionChoice qc1 = new QuestionChoice(1, "choice1", "Choiceval1");
		QuestionChoice qc2 = new QuestionChoice(1, "choice1", "Choiceval1");
		QuestionChoice qc3 = new QuestionChoice(1, "choice1", "Choiceval1");
		choice.add(qc1);
		choice.add(qc2);
		choice.add(qc3);
		
		return choice;
         
     }


	@Override
	public Questions getDocument(String doc) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	

         




