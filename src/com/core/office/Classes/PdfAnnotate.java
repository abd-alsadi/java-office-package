package com.core.office.Classes;

import java.awt.Font;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

import com.core.office.Enums.ImageAnnotateTypeEnum;
import com.core.office.Enums.PdfAnnotateTypeEnum;

public class PdfAnnotate {
    public PDColor color;
    public PDColor backgroundColor;
    public int lineWidth;
    public int x;
    public int y;
    public int x2;
    public int y2;
    public int width;
    public int height;
    public PdfAnnotateTypeEnum type;
    public boolean fill;
    public String data;
    public PDFont font;
    public float fontSize;
    public int page;
    public PdfAnnotate(){

    }
    public PdfAnnotate clone(){
        PdfAnnotate annotate = new PdfAnnotate();
       annotate.color=this.color;
       annotate.lineWidth=this.lineWidth;
       annotate.x=this.x;
       annotate.y=this.y;
       annotate.x2=this.x2;
       annotate.y2=this.y2;
       annotate.height=this.height;
       annotate.width=this.width;
       annotate.type=this.type;
       annotate.backgroundColor=backgroundColor;
       annotate.fill=fill;
       annotate.data=data;
       annotate.font=font;
       annotate.page=page;
       annotate.fontSize=fontSize;
       return annotate;
    }
    public static class Builder{
        private PdfAnnotate annotate;
        public Builder(){
            annotate=new PdfAnnotate();
        }
        public Builder setColor(PDColor color){
            this.annotate.color=color;
            return this;
        }
        public Builder setPage(int page){
            this.annotate.page=page;
            return this;
        }
        public Builder setFill(boolean fill){
            this.annotate.fill=fill;
            return this;
        }
        public Builder setFont(PDFont font,float fontSize){
            this.annotate.font=font;
            this.annotate.fontSize=fontSize;
            return this;
        }
        public Builder setBackgroundColor(PDColor backgroundColor){
            this.annotate.backgroundColor=backgroundColor;
            return this;
        }
        public Builder setLineWidth(int lineWidth){
            this.annotate.lineWidth=lineWidth;
            return this;
        }
        public Builder setData(String data){
            this.annotate.data=data;
            return this;
        }
        public Builder setX(int x){
            this.annotate.x=x;
            return this;
        }
        public Builder setY(int y){
            this.annotate.y=y;
            return this;
        }
        public Builder setX2(int x2){
            this.annotate.x2=x2;
            return this;
        }
        public Builder setY2(int y2){
            this.annotate.y2=y2;
            return this;
        }
        public Builder setWidth(int width){
            this.annotate.width=width;
            return this;
        }
        public Builder setHeight(int height){
            this.annotate.height=height;
            return this;
        }
        public Builder setType(PdfAnnotateTypeEnum type){
            this.annotate.type=type;
            return this;
        }
        public PdfAnnotate build(){
            PdfAnnotate newAnnotate=this.annotate.clone();
            return newAnnotate;
        }
    }
}
