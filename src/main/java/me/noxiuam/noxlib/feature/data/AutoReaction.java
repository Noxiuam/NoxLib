package me.noxiuam.noxlib.feature.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AutoReaction {
    private String trigger;
    private String[] emoteIds;
}
