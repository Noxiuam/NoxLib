package me.noxiuam.noxlib;

import lombok.*;
import me.noxiuam.noxlib.command.*;
import me.noxiuam.noxlib.util.*;

@Getter
public class NoxLib
{
    @Getter public static NoxLib instance;

    public MessageUtil messageUtil;
    public CommandManager commandManager;
    public ProcessUtil processUtil;
    public CodecUtil codecUtil;
    public TimeUtil timeUtil;

    private final long startTime;
    
    public NoxLib()
    {
        instance = this;
        this.startTime = System.currentTimeMillis();
        this.messageUtil = new MessageUtil();
        System.out.println("[NoxLib] Created Message Utility!");
        this.commandManager = new CommandManager();
        System.out.println("[NoxLib] Created Command Base!");
        this.processUtil = new ProcessUtil();
        System.out.println("[NoxLib] Created Process Utility!");
        this.codecUtil = new CodecUtil();
        System.out.println("[NoxLib] Created Codec Utility!");
        this.timeUtil = new TimeUtil();
        System.out.println("[NoxLib] Created Time Utility!");

        System.out.println("[NoxLib] Loaded in " + (System.currentTimeMillis() - startTime) + "ms!");
    }
}
