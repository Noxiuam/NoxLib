package me.noxiuam.noxlib;

import lombok.*;
import me.noxiuam.noxlib.automod.AutoModeration;
import me.noxiuam.noxlib.command.*;
import me.noxiuam.noxlib.util.*;

@Getter
public class NoxLib
{
    // Priorities
    @Getter public static NoxLib instance;

    // Data
    @Setter public String logChannelId;
    public String DBNImage = "https://officialpsds.com/imageview/7v/90/7v90vz_large.png";
    private final long startTime;

    // Utilities
    public MessageUtil messageUtil;
    public CommandManager commandManager;
    public ProcessUtil processUtil;
    public CodecUtil codecUtil;
    public TimeUtil timeUtil;

    // AutoMod
    public AutoModeration autoModeration;

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
        this.autoModeration = new AutoModeration();
        System.out.println("[NoxLib] Created Auto Mod!");

        System.out.println("[NoxLib] Loaded in " + (System.currentTimeMillis() - startTime) + "ms!");
    }
}
