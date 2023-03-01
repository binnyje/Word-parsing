package com.sample.WordParsing.service;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
//import javax.swing.text.html.HTMLDocument.Iterator;

import java.util.List;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.wp.usermodel.CharacterRun;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.xb.xsdschema.ChoiceDocument;
//import org.jcp.xml.dsig.internal.dom.Utils;
//import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.springframework.stereotype.Service;


@Service
public class WordReaderServiceImpl implements WordReaderService
{
	@Override
     public String getDocument(String doc) throws IOException
     {
        String fileName = "/Users/binnyjayakumaremily/bulkquestions.docx.doc";

     
        XWPFDocument docm = new XWPFDocument(Files.newInputStream(Paths.get(fileName)));
        List<IBodyElement> bodyElements = docm.getBodyElements();
        
        List<XWPFParagraph> paragraphs = docm.getParagraphs();
        List<XWPFPictureData> piclist = docm.getAllPictures();
        for(XWPFParagraph para : paragraphs) {   
        	System.out.println(para.getText());     
        	for(XWPFRun run : para.getRuns()) {
                System.out.println(run.getEmbeddedPictures().size());
                	if(run.getEmbeddedPictures().size() == 1)
                	{
                		System.out.println(run.getEmbeddedPictures());
                	}	   
        }
        
        }

 /*       for (IBodyElement bodyElement : bodyElements) {
           
         if(bodyElement instanceof XWPFPicture) { 
                System.out.println("Picture");
                
            }
            if(bodyElement instanceof XWPFParagraph)
            {
                XWPFParagraph para = (XWPFParagraph)bodyElement;
               
                System.out.println(para.getText());
           
                }
                
         } **/
                
              
        docm.close(); 
        return doc;
            }
         
	
	
}
         




