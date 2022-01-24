package me.noxiuam.noxlib.services;

import lombok.*;


@Getter @AllArgsConstructor
public abstract class DiscordGuild {

    private String serverName;
    private Tier botTier;

}
