package me.noxiuam.noxlib.command;

import lombok.*;

@Data @AllArgsConstructor
public abstract class Command {
    private String name;

    public abstract void execute(CommandContext ctx);
}
