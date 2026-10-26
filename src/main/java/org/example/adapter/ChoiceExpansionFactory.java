package org.example.adapter;

import org.example.writer.FileWriter;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.security.cert.CertPathParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoiceExpansionFactory {
   private final Map<String, FileSource> adapterExtension;

   public ChoiceExpansionFactory(){
       adapterExtension = new HashMap<>();
       adapterExtension.put(".txt", new TxtFileAdapter());
       adapterExtension.put("", new GridFileAdapter());
   }
   public String getExtension(Path path){
       String fileName = path.getFileName().toString();
       int index = fileName.lastIndexOf(".");
       if(index <= 0 || index == fileName.length() -1){
           return "";
       }
       return fileName.substring(index);
   }

    public FileSource getAdapter(Path path){
       String key = getExtension(path);

       FileSource adapter = adapterExtension.get(key);
     return adapter;
    }

}
