package org.example.adapter;

import org.example.exceptions.UnknowFileAdapter;

import java.nio.file.Path;
import java.util.Map;

public class ChoiceExpansionFactory {
   private final Map<String, FileSource> adapterExtension;

    public ChoiceExpansionFactory(Map<String, FileSource> adapterExtension) {
        this.adapterExtension = adapterExtension;
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
       if (adapter == null){
           throw new UnknowFileAdapter("Не найден адаптер для файла");
       }
     return adapter;
    }

}
