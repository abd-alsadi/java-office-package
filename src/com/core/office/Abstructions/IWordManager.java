package com.core.office.Abstructions;

import java.util.HashMap;
import java.util.List;

import com.core.office.Classes.WordBookmark;

public interface IWordManager {
    public boolean createEmptyFile(String path);
    public boolean fillBookmarks(String inputFile,String outputFile,List<WordBookmark> bookmarksList);

}
