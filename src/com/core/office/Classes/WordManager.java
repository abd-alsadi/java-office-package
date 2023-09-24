package com.core.office.Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Bookmark;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import com.core.office.Abstructions.IWordManager;
import com.core.office.Enums.WordBookmarkTypeEnum;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;
//https://drive.google.com/uc?id=1pvkrvs-LGNhxgz3ZyX3hffRy34wcnfIy&export=download
public class WordManager  implements IWordManager{
    private ILoggerManager logger;
    public WordManager(ILoggerManager logger){
        this.logger=logger;
    }
    @Override
    public boolean createEmptyFile(String path){
		try{
            XWPFDocument document = new XWPFDocument(); 
            //lets write the excel data to file now
            FileOutputStream fos = new FileOutputStream(path);
            document.write(fos);
            fos.close();
            return true;
        }catch(Exception e){
            if(logger!=null)
                logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return false;
	}
 
   
    @Override
    public boolean fillBookmarks(String inputFile,String outputFile,List<WordBookmark> bookmarksList) 
    {
        try{
            InputStream fis = new FileInputStream(inputFile);
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs)
            {
                //Here you have your paragraph; 
                CTP ctp = paragraph.getCTP(); 
                // Get all bookmarks and loop through them 
                List<CTBookmark> bookmarks = ctp.getBookmarkStartList(); 
                for(CTBookmark bookmark : bookmarks) 
                { 
                    String bookmarkName = bookmark.getName();
                    String bookmarkValue = paragraph.getText();
                    WordBookmark obj = bookmarksList.stream().filter(o -> o.name.toLowerCase().equals(bookmarkName.toLowerCase())).findFirst().get();

                     if(obj!=null)
                     {
                        initParagraph(paragraph);  
                        if(obj.type == WordBookmarkTypeEnum.TEXT){
                            fillTextBookmark(paragraph,obj);
                        }else if(obj.type == WordBookmarkTypeEnum.IMAGE){
                            fillImageBookmark(paragraph,obj); //now JPEG
                        }else{ //default TEXT
                            fillTextBookmark(paragraph,obj);
                        }
                        
                     }
                }   
            }
            OutputStream out = new FileOutputStream(outputFile);
            document.write(out);
            document.close();
            out.close();  
            fis.close();
            return true;
        } catch (Exception e) {
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
		}
        return false;
    }
    private boolean initParagraph(XWPFParagraph paragraph){
        try{
            int size = paragraph.getRuns().size();
            for (int i = 0; i < size; i++) {
                 paragraph.removeRun(0);
            }  
            return true;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
     return false;
    }
    private boolean fillTextBookmark(XWPFParagraph paragraph,WordBookmark obj){
        try{
            String text = obj.value.toString();

            String[] replacementTextSplitOnCarriageReturn = text.split("\n");
           
            for (int j = 0; j < replacementTextSplitOnCarriageReturn.length; j++) {
                String line = replacementTextSplitOnCarriageReturn[j];
    
                XWPFRun newRun = paragraph.insertNewRun(j);
                newRun.setText(line);
    
                if (j+1 < replacementTextSplitOnCarriageReturn.length) {
                    newRun.addCarriageReturn();
                }
            }  
            return true;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
     return false;
    }
    private boolean fillImageBookmark(XWPFParagraph paragraph,WordBookmark obj){
        try{
            String imgFile = obj.value.toString();
            XWPFRun newRun = paragraph.createRun();
                            FileInputStream is = new FileInputStream(imgFile);
                            newRun.addBreak();
                            newRun.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(obj.imageWidth), Units.toEMU(obj.imageHeight)); // 200x200 pixels
                            
            return true;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
     return false;
    }
}
