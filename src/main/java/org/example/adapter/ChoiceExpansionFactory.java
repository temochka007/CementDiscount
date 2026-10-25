package org.example.adapter;

import org.example.writer.FileWriter;

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
       adapterExtension.put("_ext", new GridFileAdapter());
   }
    public FileSource getAdapter(Path path){
       String fileName = path.getFileName().toString();
       int index = fileName.lastIndexOf(".");

       String key;
       if (index <= 0 || index == fileName.length() -1){
           key = "_ext";
       }else {
           key = ".txt";
       }
       FileSource adapter = adapterExtension.get(key);

       return adapter;
    }
}
