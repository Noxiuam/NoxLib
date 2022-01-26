package me.noxiuam.noxlib;

import me.noxiuam.noxlib.flow.ConfigThread;
import me.noxiuam.noxlib.flow.moderation.DeletedMessage;
import me.noxiuam.noxlib.image.ImageDatabase;
import lombok.*;
import me.noxiuam.noxlib.automod.AutoModeration;
import me.noxiuam.noxlib.command.*;
import me.noxiuam.noxlib.command.moderation.Ban;
import me.noxiuam.noxlib.command.moderation.Kick;
import me.noxiuam.noxlib.command.moderation.Unban;
import me.noxiuam.noxlib.command.music.*;
import me.noxiuam.noxlib.command.ticket.AddUser;
import me.noxiuam.noxlib.command.ticket.CloseTicket;
import me.noxiuam.noxlib.command.ticket.RemoveUser;
import me.noxiuam.noxlib.config.Config;
import me.noxiuam.noxlib.flow.VerificationHandler;
import me.noxiuam.noxlib.services.TierHandler;
import me.noxiuam.noxlib.ticket.TicketHandler;
import me.noxiuam.noxlib.util.*;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
public class NoxLib
{
    // Priorities
    @Getter public static NoxLib instance;
    @Setter public JDA botJda;

    // Data
    public List<String> openTickets = new ArrayList<>();
    public Map<Long, DeletedMessage> messageCache = new HashMap<>();
    private final long startTime;

    @Setter public String prefix = "$", logChannelId, guildId, ticketCategoryId, ticketReactChannelId, welcomeChannelId;
    public Config config;

    // Utilities
    public TierHandler tierHandler;
    public MessageUtil messageUtil;
    public ProcessUtil processUtil;
    public CodecUtil codecUtil;
    public TimeUtil timeUtil;

    // Handlers and Data Holders
    public TicketHandler ticketHandler;
    public CommandManager commandManager;
    public VerificationHandler verificationHandler;
    public ImageDatabase imageDatabase;

    // AutoMod
    public AutoModeration autoModeration;

    public NoxLib()
    {
        instance = this;
        this.startTime = System.currentTimeMillis();

        this.tierHandler = new TierHandler();
        System.out.println("[NoxLib Services] Created Tier Handler!");
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
        this.ticketHandler = new TicketHandler();
        System.out.println("[NoxLib] Created Ticket Handler!");
        this.verificationHandler = new VerificationHandler();
        System.out.println("[NoxLib] Created Verification Handler!");
        this.imageDatabase = new ImageDatabase();
        System.out.println("[NoxLib] Created Image Database!");

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        ConfigThread sessionPinger = new ConfigThread();
        scheduler.scheduleAtFixedRate(sessionPinger, 0L, 5L, TimeUnit.SECONDS);

        // Register Built-in Commands
        this.commandManager.register(new CloseTicket(), new AddUser(), new RemoveUser(), new Kick(), new Ban(), new Unban(),
                new Join(), new Play(), new Stop(), new Leave(), new Skip(), new Loop(), new Queue());

        System.err.println("[NoxLib] Loaded in " + (System.currentTimeMillis() - startTime) + "ms!");
    }
}
