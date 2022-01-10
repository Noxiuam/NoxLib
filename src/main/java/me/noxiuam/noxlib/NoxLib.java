package me.noxiuam.noxlib;

import lombok.*;
import me.noxiuam.noxlib.command.CommandManager;
import me.noxiuam.noxlib.util.CodecUtil;
import me.noxiuam.noxlib.util.MessageUtil;
import me.noxiuam.noxlib.util.ProcessUtil;

@Getter
public class NoxLib
{
    @Getter public static NoxLib instance;

    public MessageUtil messageUtil;
    public CommandManager commandManager;
    public ProcessUtil processUtil;
    public CodecUtil codecUtil;

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

        System.out.println("[NoxLib] Loaded in " + (System.currentTimeMillis() - startTime) + "ms!");
    }
}
