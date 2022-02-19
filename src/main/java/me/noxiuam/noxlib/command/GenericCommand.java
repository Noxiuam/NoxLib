package me.noxiuam.noxlib.command;

import lombok.*;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;

@Getter @AllArgsConstructor
public abstract class GenericCommand
{
    private String name;
    private String description;
    private String usage;
    private Tier requiredTier;

    public abstract void execute(CommandContext ctx);
}
