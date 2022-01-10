package me.noxiuam.noxlib.command;

import lombok.*;

@Getter @AllArgsConstructor
public abstract class Command
{
    private String name;
    private String description;

    public abstract void execute(CommandContext ctx);
}
