package me.noxiuam.noxlib.command;

import lombok.*;
import me.noxiuam.noxlib.command.util.CommandContext;

@Getter @AllArgsConstructor
public abstract class GenericCommand
{
    private String name;
    private String description;
    private String usage;

    public abstract void execute(CommandContext ctx);
}
