package org.example.adapter;

import java.nio.file.Path;

public class GridFileRead implements ChoiceExpansion{

    @Override
    public boolean canRead(Path path) {
        String fileName = path.getFileName().toString();
        return fileName.endsWith("without_ext");
    }

    @Override
    public FileSource create(Path path) {
        return new GridFileAdapter(path);
    }
}
