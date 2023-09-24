package com.core.office.Classes;

import com.core.office.Enums.WordBookmarkTypeEnum;

public class WordBookmark {
    public String name;
    public Object value;
    public WordBookmarkTypeEnum type;
    public int imageWidth;
    public int imageHeight;
    public WordBookmark(String name,Object value,WordBookmarkTypeEnum type){
        this.type=type;
        this.value=value;
        this.name=name;
    }
    public WordBookmark(String name,Object value,WordBookmarkTypeEnum type,int imageWidth,int imageHeight){
        this.type=type;
        this.imageWidth=imageWidth;
        this.imageHeight=imageHeight;
        this.value=value;
        this.name=name;
    }
}
