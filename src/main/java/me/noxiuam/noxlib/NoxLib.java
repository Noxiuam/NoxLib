package me.noxiuam.noxlib;

import me.noxiuam.noxlib.command.fun.RandomImage;
import me.noxiuam.noxlib.command.fun.game.Game;
import me.noxiuam.noxlib.command.moderation.Purge;
import me.noxiuam.noxlib.command.music.Queue;
import me.noxiuam.noxlib.flow.ConfigThread;
import me.noxiuam.noxlib.flow.moderation.DeletedMessage;
import me.noxiuam.noxlib.fun.games.GameFramework;
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

import java.util.*;
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
    public MathUtil mathUtil;

    // Handlers and Data Holders
    public TicketHandler ticketHandler;
    public CommandManager commandManager;
    public VerificationHandler verificationHandler;
    public ImageDatabase imageDatabase;

    // AutoMod
    public AutoModeration autoModeration;

    // Games
    public GameFramework gameFramework;

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
        this.mathUtil = new MathUtil();
        System.out.println("[NoxLib] Created Math Utility");

        this.autoModeration = new AutoModeration();
        System.out.println("[NoxLib] Created Auto Mod!");
        this.ticketHandler = new TicketHandler();
        System.out.println("[NoxLib] Created Ticket Handler!");
        this.verificationHandler = new VerificationHandler();
        System.out.println("[NoxLib] Created Verification Handler!");
        this.imageDatabase = new ImageDatabase();
        System.out.println("[NoxLib] Created Image Database!");

        this.gameFramework = new GameFramework();
        System.out.println("[NoxLib] Created Game Framework!");

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new ConfigThread(), 0L, 5L, TimeUnit.SECONDS);

        // Register Built-in Commands
        this.commandManager.register(new CloseTicket(), new AddUser(), new RemoveUser(), new Kick(), new Ban(), new Unban(), new Purge(),
                new Join(), new Play(), new Stop(), new Leave(), new Skip(), new Loop(), new Queue(), new RandomImage(), new Game());

        System.err.println("[NoxLib] Loaded in " + (System.currentTimeMillis() - startTime) + "ms!");
    }
}
