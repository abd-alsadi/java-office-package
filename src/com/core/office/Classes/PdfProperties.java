package com.core.office.Classes;

public class PdfProperties {
    public String author;
    public String creator;
    public String title;
    public String subject;
    public String keywords;
    public String description;
    public PdfProperties(){
        this.author="";
        this.creator="";
        this.title="";
        this.subject="";
        this.keywords="";
        this.description="";
    }
    public PdfProperties clone(){
        PdfProperties pdfProperties=new PdfProperties();
        pdfProperties.author=author;
        pdfProperties.creator=creator;
        pdfProperties.title=title;
        pdfProperties.subject=subject;
        pdfProperties.keywords=keywords;
        pdfProperties.description=description; 
        return pdfProperties;
    }
    public static class Builder{
        private PdfProperties pdfProperties;

        public Builder(){
            pdfProperties=new PdfProperties();
        }
        public Builder setAuthor(String author){
            this.pdfProperties.author=author;
            return this;
        }
        public Builder setCreator(String creator){
            this.pdfProperties.creator=creator;
            return this;
        }
        public Builder setTitle(String title){
            this.pdfProperties.title=title;
            return this;
        }
        public Builder setSubject(String subject){
            this.pdfProperties.subject=subject;
            return this;
        }
        public Builder setKeywords(String keywords){
            this.pdfProperties.keywords=keywords;
            return this;
        }
        public Builder setDescription(String description){
            this.pdfProperties.description=description;
            return this;
        }
        public PdfProperties build(){
            return pdfProperties.clone();
        }
    }
}
