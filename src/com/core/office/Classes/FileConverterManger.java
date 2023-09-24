package com.core.office.Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.core.office.Abstructions.IFileConverterManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class FileConverterManger implements IFileConverterManager {
    private ILoggerManager logger;
    public FileConverterManger(ILoggerManager logger){
        this.logger=logger;
    }
  
  
    @Override
    public boolean convertWordToPdf(String inputPath, String outputPath) {
        try{
            InputStream is = new FileInputStream(new File(inputPath));
			OutputStream out = new FileOutputStream(new File(outputPath));

			XWPFDocument document = new XWPFDocument(is);
			PdfOptions options = PdfOptions.create();
			PdfConverter.getInstance().convert(document, out, options);
            document.close();
            out.close();
            is.close();
            return true;

		} catch (Exception e) {
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
		}
        return false;
    }
    @Override
    public boolean convertImageToPdf(String inputPath, String outputPath,boolean isFull) {
        try{
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            document.newPage();
            Image img = Image.getInstance(inputPath); 
     
            if(isFull){
                int indentation = 0; 
                float scaler = 0; // ((document.getPageSize().getWidth() - document.leftMargin()- document.rightMargin() - indentation) / img.getWidth()) * 100; 
                img.scalePercent(scaler);
            }
          
            img.setAlignment(1);
            document.add(img);
            document.close();
            writer.close();
            return true;
		} catch (Exception e) {
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
		}
        return false;
    }
    @Override
    public boolean convertTextToPdf(Paragraph text, String outputPath) {
        try{
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            document.newPage();
            document.add(text);
            document.close();
            writer.close();
            return true;
		} catch (Exception e) {
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
		}
        return false;
    }
    @Override
    public boolean convertHtmlToPdf(String html, String outputPath) {
        try{
            OutputStream out = new FileOutputStream(new File(outputPath));
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(html,out, converterProperties);
            out.close();
            return true;
        } catch (Exception e) {
            if(logger!=null)
            logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
		}
        return false;
       
    }
}
