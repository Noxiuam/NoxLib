package me.noxiuam.noxlib.services;

import lombok.*;


@Getter @AllArgsConstructor
public abstract class CustomerGuild {

    private String serverName;
    private Tier botTier;

}
