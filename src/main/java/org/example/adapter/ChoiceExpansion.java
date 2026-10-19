package org.example.adapter;

import java.nio.file.Path;

public class ChoiceExpansion {
    public static FileSource create(Path path){
        String fileName = path.getFileName().toString();
        if (fileName.endsWith(".txt")){
            return new TxtFileAdapter(path);
        }else {
            return new GridFileAdapter(path);
        }
    }
}