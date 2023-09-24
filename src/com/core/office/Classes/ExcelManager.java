package com.core.office.Classes;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.core.office.Abstructions.IExcelManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;

public class ExcelManager implements IExcelManager {
    private ILoggerManager logger;
    public ExcelManager(ILoggerManager logger){
        this.logger=logger;
    }

    @Override
    public boolean createFileFromObject(String path,String sheetTitle, List<Object> data){
		try{
            Workbook workbook = null;
		
            if(path.endsWith("xlsx")){
                //workbook = new XSSFWorkbook();
            }else if(path.endsWith("xls")){
                workbook = new HSSFWorkbook();
            }
            
            Sheet sheet = workbook.createSheet(sheetTitle);
            
            Iterator<Object> iterator = data.iterator();
            
            int rowIndex = 0;
            while(iterator.hasNext()){
                Object obj = iterator.next();
                Field[] props = obj.getClass().getDeclaredFields();
                if(props!=null){
                    Row row = sheet.createRow(rowIndex++);
                    int colIndex=0;
                    for (Field field : props) {
                        if(field.isAccessible()){
                            Cell cell = row.createCell(colIndex);
                            cell.setCellValue(field.get(obj).toString());
                            colIndex++;
                        }
                    }
                }
            }
            
            //lets write the excel data to file now
            FileOutputStream fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
            return true;
        }catch(Exception e){
            if(logger!=null)
                logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return false;
	}
	
    @Override
    public boolean createEmptyFile(String path){
		try{
            Workbook workbook = null;
		
            if(path.endsWith("xlsx")){
                //workbook = new XSSFWorkbook();
            }else if(path.endsWith("xls")){
                workbook = new HSSFWorkbook();
            }
            
            //lets write the excel data to file now
            FileOutputStream fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
            return true;
        }catch(Exception e){
            if(logger!=null)
                logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return false;
	}
}
