package me.noxiuam.noxlib.feature;

import lombok.*;
import java.util.*;

@Getter
public class AutoModerationHandler
{
    public List<String> blacklistedWords = new ArrayList<>();

    public AutoModerationHandler()
    {
        System.out.println("[NoxLib] Created Auto Moderation!");
    }

    public void register(String word)
    {
        this.blacklistedWords.add(word);
    }
}