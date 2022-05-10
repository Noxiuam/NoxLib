package me.noxiuam.noxlib;

import lombok.Getter;
import lombok.Setter;
import me.noxiuam.noxlib.command.impl.fun.game.Game;
import me.noxiuam.noxlib.command.impl.moderation.*;
import me.noxiuam.noxlib.command.impl.music.*;
import me.noxiuam.noxlib.command.impl.ticket.AddUser;
import me.noxiuam.noxlib.command.impl.ticket.CloseTicket;
import me.noxiuam.noxlib.command.impl.ticket.RemoveUser;
import me.noxiuam.noxlib.command.impl.utility.Announcement;
import me.noxiuam.noxlib.command.util.CommandManager;
import me.noxiuam.noxlib.config.Config;
import me.noxiuam.noxlib.feature.AutoModerationHandler;
import me.noxiuam.noxlib.feature.AutoReactionHandler;
import me.noxiuam.noxlib.feature.TicketHandler;
import me.noxiuam.noxlib.feature.VerificationHandler;
import me.noxiuam.noxlib.feature.games.GameFramework;
import me.noxiuam.noxlib.feature.message.AutoResponseHandler;
import me.noxiuam.noxlib.feature.reactionroles.ReactionRoleManager;
import me.noxiuam.noxlib.media.ImageDatabase;
import me.noxiuam.noxlib.util.custom.*;
import me.noxiuam.noxlib.util.data.user.DeletedMessage;
import me.noxiuam.noxlib.util.function.ConfigThread;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class NoxLib {
    // Priorities
    @Getter
    public static NoxLib instance;
    private final long startTime;
    @Setter
    public JDA botJda;
    // Data
    public List<String> openTickets = new ArrayList<>();
    public Map<Long, DeletedMessage> messageCache = new HashMap<>();
    @Setter
    public String prefix = "$", logChannelId, guildId, ticketCategoryId,
            ticketReactChannelId, welcomeChannelId, reportsChannelId, roleMenuChannelId;

    public Config configuration;

    public MessageUtil messageUtil;
    public ProcessUtil processUtil;
    public CodecUtil codecUtil;
    public TimeUtil timeUtil;
    public MathUtil mathUtil;
    public ThreadUtil threadUtil;

    public TicketHandler ticketHandler;
    public CommandManager commandManager;
    public VerificationHandler verificationHandler;
    public ImageDatabase imageDatabase;
    public ReactionRoleManager reactionRoleManager;
    public AutoResponseHandler autoResponseHandler;
    public AutoModerationHandler autoModerationHandler;
    public AutoReactionHandler autoReactionHandler;

    public GameFramework gameFramework;

    public NoxLib() {
        instance = this;
        this.startTime = System.currentTimeMillis();

        {
            this.commandManager = new CommandManager();
        }

        {
            this.messageUtil = new MessageUtil();
            this.processUtil = new ProcessUtil();
            this.codecUtil = new CodecUtil();
            this.timeUtil = new TimeUtil();
            this.mathUtil = new MathUtil();
            this.threadUtil = new ThreadUtil();
        }

        {
            this.autoResponseHandler = new AutoResponseHandler();
            this.autoReactionHandler = new AutoReactionHandler();
            this.autoModerationHandler = new AutoModerationHandler();
            this.ticketHandler = new TicketHandler();
            this.verificationHandler = new VerificationHandler();
            this.imageDatabase = new ImageDatabase();
            this.reactionRoleManager = new ReactionRoleManager();
            this.gameFramework = new GameFramework();
        }

        this.threadUtil.createThread(new ConfigThread(), 5L);

        // Register Built-In Generic Commands
        this.commandManager.register(
                new CloseTicket(), new AddUser(), new RemoveUser(), new Kick(), new Ban(),
                new Unban(), new Purge(), new Join(), new Play(), new Stop(), new Leave(),
                new Skip(), new Loop(), new Queue(), new Game(), new Report(), new Announcement(),
                new Mute()
        );

        System.err.println("[NoxLib] Loaded in " + (System.currentTimeMillis() - startTime) + "ms!");
    }
}
