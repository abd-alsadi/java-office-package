package com.core.office.Classes;

public class PdfInfo {
    private int numberOfPages;
    private char version;
    public PdfInfo (int numberOfPages,char version){
        this.numberOfPages=numberOfPages;
        this.version=version;
    }
    public int getNumberOfPages(){
        return numberOfPages;
    }
    public char getVersion(){
        return version;
    }
}
