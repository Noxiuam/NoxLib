package me.noxiuam.noxlib.command.util.data;

import lombok.*;

@AllArgsConstructor @Getter @Setter
public class GenericAnnouncement
{
    private String channelId;
    private int index;
    private String title;
    private String description;
}
