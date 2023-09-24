package com.core.office.Classes;

import java.io.FileInputStream;
import java.util.List;
import com.core.office.Abstructions.IImageManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

public class ImageManager implements IImageManager{
    private ILoggerManager logger;
    public ImageManager(ILoggerManager logger){
        this.logger=logger;
    }
    public boolean burnAnnotats(String inputPath,String outputPath,List<ImageAnnotate> annotates){
        try{
            if(annotates==null || annotates.size()==0)
            return false;
    
            ImagePlus imp = IJ.openImage(inputPath);
            ImageProcessor processor = imp.getProcessor();
            for (ImageAnnotate imageAnnotate : annotates) {
                switch(imageAnnotate.type){
                    case RECT :
                    processor= BurnRectangle(processor, imageAnnotate);
                    break;
                    case CIRCLE :
                    processor= BurnCircle(processor, imageAnnotate);
                    break;
                    case LINE :
                    processor= BurnLine(processor, imageAnnotate);
                    break;
                    case TEXT :
                    processor= BurnText(processor, imageAnnotate);
                    break;
                    case DOT :
                    processor= BurnDot(processor, imageAnnotate);
                    break;
                }
            }
            IJ.save(imp, outputPath);
            return true;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));
        }
        return false;
    }
    @Override
    public boolean rotate(String inputPath,String outputPath,int angle){
        try{
           
            ImagePlus imp = IJ.openImage(inputPath);
            ImageProcessor processor = imp.getProcessor();
            processor.rotate(angle);
            IJ.save(imp, outputPath);
            return true;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));
        }
        return false;
    }
    @Override
    public boolean scale(String inputPath,String outputPath,int x,int y){
        try{
           
            ImagePlus imp = IJ.openImage(inputPath);
            ImageProcessor processor = imp.getProcessor();
            processor.scale(x,y);
            IJ.save(imp, outputPath);
            return true;
        }catch(Exception e){
            if(logger!=null)
            logger.log(new LoggerMessage(null,e.getMessage(),LoggerMessageTypeEnum.EXCEPTION));
        }
        return false;
    }
    private ImageProcessor BurnRectangle(ImageProcessor processor,ImageAnnotate imageAnnotate){
        if(imageAnnotate.backgroundColor!=null)
            processor.setBackgroundColor(imageAnnotate.backgroundColor);

        
        if(imageAnnotate.color!=null)
            processor.setColor(imageAnnotate.color);

        if(imageAnnotate.lineWidth!=0)
            processor.setLineWidth(imageAnnotate.lineWidth);
        if(imageAnnotate.fill)
            processor.fillRect(imageAnnotate.x, imageAnnotate.y,imageAnnotate.width, imageAnnotate.height);
        else
            processor.drawRect(imageAnnotate.x, imageAnnotate.y,imageAnnotate.width, imageAnnotate.height);

        
         return processor;
    }
    private ImageProcessor BurnCircle(ImageProcessor processor,ImageAnnotate imageAnnotate){
        if(imageAnnotate.backgroundColor!=null)
            processor.setBackgroundColor(imageAnnotate.backgroundColor);

        
        if(imageAnnotate.color!=null)
            processor.setColor(imageAnnotate.color);

        if(imageAnnotate.fill)
            processor.fillOval(imageAnnotate.x, imageAnnotate.y,imageAnnotate.width, imageAnnotate.height);
        else
            processor.drawOval(imageAnnotate.x, imageAnnotate.y,imageAnnotate.width, imageAnnotate.height);

        
         return processor;
    }
    private ImageProcessor BurnLine(ImageProcessor processor,ImageAnnotate imageAnnotate){
        if(imageAnnotate.backgroundColor!=null)
            processor.setBackgroundColor(imageAnnotate.backgroundColor);

        
        if(imageAnnotate.color!=null)
            processor.setColor(imageAnnotate.color);

        if(imageAnnotate.lineWidth!=0)
            processor.setLineWidth(imageAnnotate.lineWidth);

            processor.drawLine(imageAnnotate.x, imageAnnotate.y,imageAnnotate.x2, imageAnnotate.y2);

        
         return processor;
    }
    private ImageProcessor BurnText(ImageProcessor processor,ImageAnnotate imageAnnotate){
        if(imageAnnotate.backgroundColor!=null)
            processor.setBackgroundColor(imageAnnotate.backgroundColor);
        
        if(imageAnnotate.color!=null)
            processor.setColor(imageAnnotate.color);

        if(imageAnnotate.lineWidth!=0)
            processor.setLineWidth(imageAnnotate.lineWidth);

        if(imageAnnotate.font!=null)
            processor.setFont(imageAnnotate.font);
            
        processor.drawString(imageAnnotate.data,imageAnnotate.x,imageAnnotate.y);
         return processor;
    }

    private ImageProcessor BurnDot(ImageProcessor processor,ImageAnnotate imageAnnotate){
        if(imageAnnotate.backgroundColor!=null)
            processor.setBackgroundColor(imageAnnotate.backgroundColor);
        
        if(imageAnnotate.color!=null)
            processor.setColor(imageAnnotate.color);

        if(imageAnnotate.lineWidth!=0)
            processor.setLineWidth(imageAnnotate.lineWidth);

        processor.drawDot(imageAnnotate.x,imageAnnotate.y);
         return processor;
    }
    
}
