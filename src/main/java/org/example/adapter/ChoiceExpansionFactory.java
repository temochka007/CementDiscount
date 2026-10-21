package org.example.adapter;

import java.nio.file.Path;
import java.util.List;

public class ChoiceExpansionFactory {
    private final List<ChoiceExpansion> strategies;

    public ChoiceExpansionFactory(List<ChoiceExpansion> strategies) {
        this.strategies = strategies;
    }

    public FileSource create(Path path){
        for (ChoiceExpansion strategy : strategies){
            if (strategy.canRead(path)){
                return strategy.create(path);
            }
        }
        return null;
    }
}
