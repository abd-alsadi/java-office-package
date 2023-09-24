package com.core.office.Classes;

import java.awt.Font;

import com.core.office.Enums.ImageAnnotateTypeEnum;

public class ImageAnnotate {
    public java.awt.Color color;
    public java.awt.Color backgroundColor;
    public int lineWidth;
    public int x;
    public int y;
    public int x2;
    public int y2;
    public int width;
    public int height;
    public ImageAnnotateTypeEnum type;
    public boolean fill;
    public String data;
    public Font font;
    public ImageAnnotate(){

    }
    public ImageAnnotate clone(){
       ImageAnnotate annotate = new ImageAnnotate();
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
       return annotate;
    }
    public static class Builder{
        private ImageAnnotate annotate;
        public Builder(){
            annotate=new ImageAnnotate();
        }
        public Builder setColor(java.awt.Color color){
            this.annotate.color=color;
            return this;
        }
        public Builder setFill(boolean fill){
            this.annotate.fill=fill;
            return this;
        }
        public Builder setFont(Font font){
            this.annotate.font=font;
            return this;
        }
        public Builder setBackgroundColor(java.awt.Color backgroundColor){
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
        public Builder setType(ImageAnnotateTypeEnum type){
            this.annotate.type=type;
            return this;
        }
        public ImageAnnotate build(){
            ImageAnnotate newAnnotate=this.annotate.clone();
            return newAnnotate;
        }
    }
}
