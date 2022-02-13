package me.noxiuam.noxlib.feature;

import lombok.*;
import java.util.*;

@Getter
public class AutoModerationHandler
{
    public List<String> blacklistedWords = new ArrayList<>();

    public void register(String word)
    {
        this.blacklistedWords.add(word);
    }
}