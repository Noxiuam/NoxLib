package me.noxiuam.noxlib;

import lombok.*;
import me.noxiuam.noxlib.command.CommandManager;
import me.noxiuam.noxlib.util.MessageUtil;

@Data
public class NoxLib {
    @Getter public static NoxLib instance;
    public MessageUtil messageUtil;
    public CommandManager cmdManager;

    private final long startTime;

    public NoxLib() {
        this.startTime = System.currentTimeMillis();
        this.messageUtil = new MessageUtil();
        System.out.println("[NoxLib] Created Message Utility!");
        this.cmdManager = new CommandManager();
        System.out.println("[NoxLib] Created Command Manager!");
        System.out.println("[NoxLib] Loaded in " + (System.currentTimeMillis() - startTime) + "ms!");
    }
}
