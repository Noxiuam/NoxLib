package me.noxiuam.noxlib.command;

import lombok.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

@Getter @AllArgsConstructor
public abstract class SlashCommand
{
    private String name;
    private String description;
    private String usage;

    public abstract void execute(SlashCommandEvent ctx);

}
