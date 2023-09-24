package com.core.office.Classes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.List;

import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import com.core.office.Abstructions.IPdfManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PdfManager implements IPdfManager {
    private ILoggerManager logger;
    public PdfManager(ILoggerManager logger){
        this.logger=logger;
    }
    private PDDocumentInformation fillProps(PDDocumentInformation props,PdfProperties pdfProperties){
        if(pdfProperties!=null){
            props.setAuthor(pdfProperties.author);
            props.setCreator(pdfProperties.creator);
            props.setKeywords(pdfProperties.keywords);
            props.setSubject(pdfProperties.subject);
            props.setTitle(pdfProperties.title);
        }
        return props;
    }
    private Document fillProps(Document doc,PdfProperties pdfProperties){
        if(pdfProperties!=null){
            doc.addAuthor(pdfProperties.author);
            doc.addCreator(pdfProperties.creator);
            doc.addKeywords(pdfProperties.keywords);
            doc.addSubject(pdfProperties.subject);
            doc.addTitle(pdfProperties.title);
        }
        return doc;
    }
    @Override
    public boolean createEmptyPdf(String path,PdfProperties pdfProperties) {  
        try
        {
            PDDocument document = new PDDocument();
            PDDocumentInformation props = document.getDocumentInformation();
            props = fillProps(props,pdfProperties);
            document.save(path);
            document.close();
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }

    @Override
    public boolean addBlankPage(String inputPath,String outputPath,int count) {  
        try
        {
            PDDocument document = PDDocument.load(new File(inputPath));
            for (int index=0;index<count;index++) {
                PDPage page = new PDPage();
                document.addPage(page);
            }
          
            document.save(outputPath);
            document.close();
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }
    @Override
    public boolean addTextToPage(String inputPath,String outputPath,String text ,int pageNumber) {  
        try
        {
            PDDocument document = PDDocument.load(new File(inputPath));
            PDPage page = document.getPage(pageNumber);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText(); 
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(25, 500);
            contentStream.showText(text);      
            contentStream.endText();
            contentStream.close();
            document.save(outputPath);
            document.close();
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }
    @Override
    public boolean merge(List<String> inputPaths,String outputPath) {  
        try
        {
            PDFMergerUtility PDFmerger = new PDFMergerUtility();
            PDFmerger.setDestinationFileName(outputPath);  
  
            for (String path  : inputPaths) {
                File file = new File(path);
                PDFmerger.addSource(file);
            }
            PDFmerger.mergeDocuments();
            
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }
    @Override
    public boolean addWatermark(String inputPath,String outputPath, List<Integer> pages,boolean isAll,PdfAnnotate watermark)
        {
            try{
                PDDocument document = PDDocument.load(new File(inputPath));
                for (Integer pageNum : pages) {
                    PDPage page = document.getPage(1);
                    PDPageContentStream cs = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
                    
                        float fontHeight = 30;

                        float width = 100;
                        
                        float height =100;
                        if(page.getMediaBox()!=null){
                            width= page.getMediaBox().getWidth();
                            height= page.getMediaBox().getHeight();
                        }
                       
                        float stringWidth = watermark.font.getStringWidth(watermark.data) / 1000 * fontHeight;
        
                        float x = (width / 2) - (stringWidth / 2);
                        float y = height - 25;
        
                        if(watermark.font!=null)
                        cs.setFont(watermark.font, fontHeight);
        
                        PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
                        gs.setNonStrokingAlphaConstant(new java.lang.Float(0.2f));
                        gs.setStrokingAlphaConstant(new java.lang.Float(0.2f));
                        gs.setBlendMode(BlendMode.MULTIPLY);
                        gs.setLineWidth((float)watermark.lineWidth);
                        cs.setGraphicsStateParameters(gs);
        
                        if(watermark.color!=null){
                            cs.setNonStrokingColor(watermark.color);
                            cs.setStrokingColor(watermark.color);
                        }
                     
        
                        cs.beginText();
                        cs.newLineAtOffset(x, y);
                        cs.showText(watermark.data);
                        cs.endText();
                        cs.close();
                       
                }
                document.save(outputPath);
                document.close();
                return true;
            }catch (Exception e)
            {
                if(logger!=null)
                    logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

            } 
            return false;
        }
    @Override
    public boolean burnAnnotats(String inputPath,String outputPath,List<PdfAnnotate> annotates) {  
        try
        {
            PDDocument document = PDDocument.load(new File(inputPath));
    
            for (PdfAnnotate pdfAnnotate : annotates) {
                PDPage page = document.getPage(pdfAnnotate.page);
                PDPageContentStream contents;
                switch(pdfAnnotate.type){
                    case IMAGE:
                        contents = new PDPageContentStream(document, page);
                        PDImageXObject pdImage = PDImageXObject.createFromFile(pdfAnnotate.data,document);
                        contents.drawImage(pdImage, pdfAnnotate.x, pdfAnnotate.y,pdfAnnotate.width,pdfAnnotate.height);
                        contents.close();
                    break;
                    case RECT:

                      //  PDRectangle rect = new PDRectangle(pdfAnnotate.x, pdfAnnotate.y, pdfAnnotate.width, pdfAnnotate.height);
                         contents = new PDPageContentStream(document, page);
                        contents.setLineWidth(pdfAnnotate.lineWidth);
                        if(pdfAnnotate.color!=null)
                        contents.setNonStrokingColor(pdfAnnotate.color);
                        contents.addRect(pdfAnnotate.x, pdfAnnotate.y, pdfAnnotate.width, pdfAnnotate.height);
                        if(pdfAnnotate.fill)
                        contents.fill();
                        contents.close();
                    break;
                    case LINE:
                        contents = new PDPageContentStream(document, page);
                        if(pdfAnnotate.color!=null)
                        contents.setNonStrokingColor(pdfAnnotate.color);
                        contents.setLineWidth(pdfAnnotate.lineWidth);
                        contents.drawLine(pdfAnnotate.x, pdfAnnotate.y, pdfAnnotate.width, pdfAnnotate.height);
                        contents.close();
                        break;
                    case TEXT:
                    contents = new PDPageContentStream(document, page);
                    if(pdfAnnotate.color!=null)
                    contents.setNonStrokingColor(pdfAnnotate.color);
                    if(pdfAnnotate.font!=null)
                    contents.setFont(pdfAnnotate.font, pdfAnnotate.fontSize);
                    contents.setLineWidth(pdfAnnotate.lineWidth);
                    contents.drawString(pdfAnnotate.data);
                    contents.close();
                break;
                }
            }
          
            document.save(outputPath);
            document.close();
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }
    @Override
    public String getAllText(String inputPath) {  
        try
        {
            PDDocument document = PDDocument.load(new File(inputPath));
            PDFTextStripper pdfStripper = new PDFTextStripper();

            String text = pdfStripper.getText(document);
            document.close();
           return text;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return null;
    }
    @Override
    public boolean removePages(String inputPath,String outputPath,List<Integer> pages) {  
        try
        {
            PDDocument document = PDDocument.load(new File(inputPath));
            for (int page : pages) {
                document.removePage(page);
            }
          
            document.save(outputPath);
            document.close();
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }
    @Override
    public boolean exportPages(String inputPath,String outputPath,List<Integer> pages) {  
        try
        {
            PDDocument document = PDDocument.load(new File(inputPath));
            PDDocument out = new PDDocument();
            for (int pageNum : pages) {
               PDPage page =document.getPage(pageNum);
                out.addPage(page);
            }
          
            out.save(outputPath);
            out.close();
            document.close();
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }

    @Override
    public boolean createPdf(String path,List<Element> elements,PdfProperties pdfProperties) {
        Document document = new Document();
        try
        {
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
           document.open();
           document = fillProps(document, pdfProperties);
                
           if(elements!=null)
            for (Element element : elements) {
                document.add(element);
            }
           document.close();
           writer.close();
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }  
    @Override
    public boolean createPdf(String path,Element element,PdfProperties pdfProperties) {
        Document document = new Document();
        try
        {
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
           document.open();
           document = fillProps(document, pdfProperties);
          
           if(element!=null)
             document.add(element);
           document.close();
           writer.close();
           return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }  
    @Override
    public boolean extractPageToImage(String inputPath,String outputPath,int pageNumber) {
        try
        {
            File file = new File(inputPath);
            PDDocument pdfDocument = PDDocument.load(file);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
            BufferedImage img = pdfRenderer.renderImage(pageNumber);
            String ext = outputPath.substring(outputPath.lastIndexOf(".")+1);
            ext=ext.toUpperCase();
            ImageIO.write( img, ext, new File(outputPath));    
            pdfDocument.close();
            return true;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return false;
    }  
    @Override
    public String getTextFromPage(String path,int page) {
        try
        {
            PdfReader reader = new PdfReader(path);
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, page);
            reader.close();
           return textFromPage;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return null;
    }  
    @Override
    public PdfInfo getInfo(String path) {
        try
        {
            PdfReader reader = new PdfReader(path);
            int numberOfPages = reader.getNumberOfPages();
            char version = reader.getPdfVersion();
            PdfInfo info = new PdfInfo(numberOfPages,version);
            reader.close();
           return info;
        }catch (Exception e)
        {
            if(logger!=null)
                logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));

        } 
        return null;
    }  
 
}
