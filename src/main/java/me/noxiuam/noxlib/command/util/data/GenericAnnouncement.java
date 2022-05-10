package me.noxiuam.noxlib.command.util.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenericAnnouncement {
    private String channelId;
    private int index;
    private String title;
    private String description;
}
