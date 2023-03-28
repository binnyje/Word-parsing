package com.sample.WordParsing.service;



import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.util.*;


import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Node;

import com.sample.WordParsing.entity.Question;


import com.sample.WordParsing.entity.QuestionChoice;
import com.sample.WordParsing.entity.QuestionGroup;





@Service
public class WordReaderServiceImpl implements WordReaderService
{
	 @Autowired
	private Environment env;
	
     public List<QuestionGroup> getDocument(MultipartFile multipartFile)throws IOException
     { 
    	 
    	 
         List<QuestionGroup> group = new ArrayList<QuestionGroup>(); 
         List<Question> questions = new ArrayList<Question>();
         List<QuestionChoice> choices = new ArrayList<QuestionChoice>();
         String marker = new String();
  
         
         String questionGroupMarker = "D\\.[0-9]+-[0-9]+\\)";
         String questionMarker ="Q\\.[0-9]+\\)";
         String choiceMarker = "^[A-Za-z0-9]+\\)";
         
        QuestionGroup qg = new QuestionGroup();
        Question q = new Question();    
        QuestionChoice qc =  new QuestionChoice();
        
  
    
        
        String fileNameNew = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        String filepath = env.getProperty("tempLocation")+fileNameNew;
        File file = new File(filepath);   
        multipartFile.transferTo(file);

        
        XWPFDocument docm = new XWPFDocument(Files.newInputStream(Paths.get(filepath)));
        List<IBodyElement> bodyElements = docm.getBodyElements();
        List<XWPFParagraph> paragraphs = docm.getParagraphs();
        List<XWPFPictureData> piclist = docm.getAllPictures();
        
         
        
        for(XWPFParagraph para : paragraphs) {
        	String text = para.getText();
        	System.out.println(text);
        	
        	
        	 for (CTOMath ctomath : para.getCTP().getOMathList()) 
         	 {  
            	System.out.println(ctomath.toString());         	
         	 } 
        	


        	if(findPatternMatch(text, questionGroupMarker))     	
        	{
        		if(qg.getContext()!=null)	
        		{   
        			q.setChoices(choices);
        			questions.add(q);
        			qg.setQuestionGroup(questions);
        			group.add(qg);

        		}
        		qg = new QuestionGroup();
        		marker = "QuestionGroup";
         		q = new Question();
        		questions = new ArrayList<Question>();
        		
        		qg.setContext(text);
        		
        		continue;
        	}
        		 
        
        	if(findImages(para).size() > 0)	
        	{   
        	   
        		qg.setContextImages(findImages(para));
        		continue;
        	}
        	
        	
        	
        	if(findPatternMatch(text, questionMarker))
        	{
        		if(q.getQuestion() != null)
        		{
	        	    q.setChoices(choices);
	        	    questions.add(q);
	        	   
	        	    q = new Question();
	        	    
        		}
    			q.setQuestion(text);
    			marker = "Question";
    			choices = new ArrayList<QuestionChoice>();
    			continue;
    			 
        	}
        	
        	if(findPatternMatch(text, choiceMarker))
        	{ 
        		qc = new QuestionChoice();
        		marker = "Choice";
        		qc.setChoiceText(text);
        		
        		//System.out.println(para.getCTP().getOMathList().isEmpty());
        		if (!para.getCTP().getOMathList().isEmpty()) {
	                for (CTOMath ctomath : para.getCTP().getOMathList()) 
	             	 {  
	                	qc.setChoiceText(qc.getChoiceText() + ctomath.toString());         	
	             	 } 
        		}
    	
        		choices.add(qc);
        		continue;
        	}
        	
        	
        	if(marker == "QuestionGroup")
        	{

        		if(qg.getContext()!=null)
        		{
        			qg.setContext(qg.getContext()+ " " +text);
        		}
        	}
        	
        	

        	if(marker == "Question")
        	{
        		if(q.getQuestion()!=null)
        		{
        			
        			
        			q.setQuestion(q.getQuestion() + text);
        		}
        	}
        	if(marker == "Choice")
        	{
        		
        		if(qc.getChoiceText()!=null)
        		{
		        		qc.setChoiceText(qc.getChoiceText() + text);
		        	}
        	}
    
        	
        }

      docm.close(); 
			
	return group;

			
	}
 

	// Methods
     
     public boolean findPatternMatch(String text,String patternRegex)
     {
    	    Pattern pattern = Pattern.compile(patternRegex);
		    Matcher matcher = pattern.matcher(text);
		    boolean matchFound = matcher.find();
		    if(matchFound) {
		      //System.out.println("Match found");
		    } else {
		      //System.out.println("Match not found");
		    }
		    return matchFound;
     }
     
     public List<String> findImages(XWPFParagraph para)
     {
    	 List<String> encodedString = new ArrayList<>();
    	 for(XWPFRun run :para.getRuns()) {
   		  
    		 if(run.getEmbeddedPictures().size() > 0 ) {
                  for(XWPFPicture picture : run.getEmbeddedPictures()){
                      XWPFPictureData picData = picture.getPictureData();
                      byte[] bytepic = picData.getData();
                      encodedString.add(Base64.getEncoder().encodeToString(bytepic));
                     // System.out.println(encodedString);
           
                  }
    		 }
    	  } 
    	 return encodedString;
     }

	@Override
	public Question getDocument(String doc) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}



//	@Override
//	public List<QuestionGroup> getDocument(MultipartFile multipartFile) throws IOException {
		// TODO Auto-generated method stub
//		return null;
//	}
	
}
	

         




