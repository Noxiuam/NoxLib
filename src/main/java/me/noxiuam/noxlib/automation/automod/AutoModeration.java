package me.noxiuam.noxlib.automation.automod;

import lombok.*;
import java.util.*;

@Getter
public class AutoModeration
{
    public List<String> blacklistedWords = new ArrayList<>();

    public void register(String word)
    {
        this.blacklistedWords.add(word);
    }
}