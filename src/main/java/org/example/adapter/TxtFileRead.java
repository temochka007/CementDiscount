package org.example.adapter;

import java.nio.file.Path;

public class TxtFileRead implements ChoiceExpansion {

    @Override
    public boolean canRead(Path path) {
        String fileName = path.getFileName().toString();
        return fileName.endsWith(".txt");
    }

    @Override
    public FileSource create(Path path) {
        return new TxtFileAdapter(path);
    }
}
