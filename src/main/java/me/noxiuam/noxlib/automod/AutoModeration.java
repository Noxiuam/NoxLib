package me.noxiuam.noxlib.automod;

import lombok.*;
import java.util.*;

@Getter
public class AutoModeration
{
    public List<String> blacklistedWords = new ArrayList<>();

    public AutoModeration()
    {
        this.blacklistedWords.add("testgay");
    }
}
