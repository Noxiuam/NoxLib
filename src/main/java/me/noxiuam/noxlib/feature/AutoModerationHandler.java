package me.noxiuam.noxlib.feature;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AutoModerationHandler {
    public List<String> blacklistedWords = new ArrayList<>();

    public void register(String word) {
        this.blacklistedWords.add(word);
    }
}