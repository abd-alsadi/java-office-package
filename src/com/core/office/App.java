package com.core.office;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import com.core.office.Abstructions.IExcelManager;
import com.core.office.Abstructions.IFileConverterManager;
import com.core.office.Abstructions.IPdfManager;
import com.core.office.Abstructions.IWordManager;
import com.core.office.Classes.ExcelManager;
import com.core.office.Classes.FileConverterManger;
import com.core.office.Classes.ImageAnnotate;
import com.core.office.Classes.ImageManager;
import com.core.office.Classes.PdfAnnotate;
import com.core.office.Classes.PdfInfo;
import com.core.office.Classes.PdfManager;
import com.core.office.Classes.PdfProperties;
import com.core.office.Classes.WordBookmark;
import com.core.office.Classes.WordManager;
import com.core.office.Enums.ImageAnnotateTypeEnum;
import com.core.office.Enums.PdfAnnotateTypeEnum;
import com.core.office.Enums.WordBookmarkTypeEnum;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("-------------------------------");
 
        // IPdfManager pdfManager = new PdfManager(null);
        // List<Element> elements = new ArrayList<>();
        // Paragraph paragraph = new Paragraph(" Hello World ",
        //  FontFactory.getFont(FontFactory.TIMES_ROMAN,18, Font.BOLD, BaseColor.BLACK));
        
        // elements.add(paragraph);
        // pdfManager.createPdf("test.pdf", elements,null,true);

        // IExcelManager excel = new ExcelManager(null);
        // List<Object> data = new ArrayList<>();
        // PdfProperties pp = (new PdfProperties.Builder()).setTitle("title").setAuthor("author").build();
        // data.add(pp);
        // excel.createFileFromObject("excel.xls", "s1",data);

       // IWordManager word = new WordManager(null);
       // word.createEmptyFile("test.docx");
        //word.convertToPdf("test.docx","convert.pdf");
       // HashMap<String,String> bookmarks = new  HashMap<String,String>();
      //  bookmarks.put("BookmarkX","abd abd abd");
      //  word.fillBookmarks("test.docx","fill.docx",bookmarks);
       // IFileConverterManager fileConverterManager = new FileConverterManger(null);

       // fileConverterManager.convertWordToPdf("fill.docx","fill.pdf");
      // fileConverterManager.convertImageToPdf("images.jpeg","fill.pdf",true);
     //  fileConverterManager.convertHtmlToPdf("<p>asdasd</>","fillx.pdf");
    //  List<ImageAnnotate> list = new ArrayList<>();
    //  list.add(new ImageAnnotate.Builder().setType(ImageAnnotateTypeEnum.TEXT).setLineWidth(10).setBackgroundColor(java.awt.Color.RED).setColor(java.awt.Color.BLUE).setData("NOOR").build());
    // ImageManager x = new ImageManager(null);
   // x.BurnAnnotats("images.jpg","x.jpeg",list);
   //x.scale("images.jpg","x.jpeg",90,90);  

//    String input="testPdf2.pdf";
//      IPdfManager pdfManager = new PdfManager(null);
    //  pdfManager.extractPageToImage(input, "extractPageToImage.PNG", 0);
    //  pdfManager.addBlankPage(input, "addBlankPage.pdf",2);
    //  pdfManager.addTextToPage(input, "addTextToPage.pdf", "test Text", 1);
    //  String xxx = pdfManager.getAllText(input);
    //  List<Integer> pages = new ArrayList();
    //  pages.add(0);

    //  pdfManager.removePages(input, "removePages.pdf", pages);
    //pdfManager.exportPages(input, "ex.pdf", pages);
    //  List<PdfAnnotate> anns = new ArrayList();
    //  anns.add(new PdfAnnotate.Builder().setType(PdfAnnotateTypeEnum.RECT).setX(20).setY(20).setWidth(250).setHeight(250).setFill(true).setPage(1).setData("image.jpg").build());
    //  List<String> files = new ArrayList<>();
    //  files.add("testPdf.pdf");
    //  files.add("aaaa.pdf");
    // // pdfManager.merge(files,"burnAnnotats.pdf");

    //  PdfAnnotate a= new PdfAnnotate.Builder().setData("xxxxxxxx").setLineWidth(20).build();
    // pdfManager.addWatermark(input, "xx.pdf", pages, true,a);


   System.out.println("PDF Created"); 
   // add annotate to pdf  writer.addAnnotation(null);

    IWordManager word = new WordManager(null);
    List<WordBookmark> ws=new ArrayList<>();
    //ws.add(new WordBookmark("BookmarkX", "xxxxxxxxxxxxxx", WordBookmarkTypeEnum.TEXT));
    ws.add(new WordBookmark("BookmarkX", "image.jpg", WordBookmarkTypeEnum.IMAGE));
    word.fillBookmarks("asd.docx", "tttt.docx", ws);


    }
  
}

