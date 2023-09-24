package com.core.office.Abstructions;

import java.util.List;

public interface IExcelManager {
    public boolean createFileFromObject(String path,String sheetTitle, List<Object> data);
    public boolean createEmptyFile(String path);
}
