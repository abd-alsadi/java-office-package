package com.core.office.Abstructions;

import java.util.List;

import com.core.office.Classes.PdfAnnotate;
import com.core.office.Classes.PdfInfo;
import com.core.office.Classes.PdfProperties;
import com.itextpdf.text.Element;

public interface IPdfManager {
    public boolean createEmptyPdf(String path,PdfProperties pdfProperties);
    public boolean createPdf(String path,List<Element> elements,PdfProperties pdfProperties);
    public boolean createPdf(String path,Element element,PdfProperties pdfProperties);
    public String getTextFromPage(String path,int page);
    public PdfInfo getInfo(String path);
    public boolean extractPageToImage(String inputPath,String outputPath,int pageNumber);
    public boolean addBlankPage(String inputPath,String outputPath,int count);
    public boolean addTextToPage(String inputPath,String outputPath,String text ,int pageNumber);
    public boolean burnAnnotats(String inputPath,String outputPath,List<PdfAnnotate> annotates);
    public String getAllText(String inputPath);
    public boolean removePages(String inputPath,String outputPath,List<Integer> pages);
    public boolean exportPages(String inputPath,String outputPath,List<Integer> pages);
    public boolean merge(List<String> inputPaths,String outputPath);
    public boolean addWatermark(String inputPath,String outputPath, List<Integer> pages,boolean isAll,PdfAnnotate watermark);
}
