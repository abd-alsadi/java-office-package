package com.core.office.Abstructions;

import com.itextpdf.text.Paragraph;

public interface IFileConverterManager {
    public boolean convertWordToPdf(String inputPath,String outputPath);
    public boolean convertImageToPdf(String inputPath, String outputPath,boolean isFull);
    public boolean convertTextToPdf(Paragraph text, String outputPath);
    public boolean convertHtmlToPdf(String html, String outputPath);
}
