package com.core.office.Abstructions;

import java.util.List;

import com.core.office.Classes.ImageAnnotate;

public interface IImageManager {
    public boolean burnAnnotats(String inputPath,String outputPath,List<ImageAnnotate> annotates);
    public boolean rotate(String inputPath,String outputPath,int angle);
    public boolean scale(String inputPath,String outputPath,int x,int y);

}
