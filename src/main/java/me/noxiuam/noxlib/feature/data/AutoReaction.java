package me.noxiuam.noxlib.feature.data;

import lombok.*;

@Getter @AllArgsConstructor
public class AutoReaction
{
    private String trigger;
    private String[] emoteIds;
}
