package me.noxiuam.noxlib;

import lombok.*;
import me.noxiuam.noxlib.automod.AutoModeration;
import me.noxiuam.noxlib.command.*;
import me.noxiuam.noxlib.command.moderation.Ban;
import me.noxiuam.noxlib.command.moderation.Kick;
import me.noxiuam.noxlib.command.moderation.Unban;
import me.noxiuam.noxlib.command.ticket.AddUser;
import me.noxiuam.noxlib.command.ticket.CloseTicket;
import me.noxiuam.noxlib.command.ticket.RemoveUser;
import me.noxiuam.noxlib.services.Tier;
import me.noxiuam.noxlib.ticket.TicketHandler;
import me.noxiuam.noxlib.util.*;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NoxLib
{
    // Priorities
    @Getter public static NoxLib instance;
    @Setter public JDA botJda;

    // Data
    public List<String> openTickets = new ArrayList<>();
    @Setter public String prefix, logChannelId, guildId, ticketCategoryId, ticketReactChannelId, welcomeChannelId;
    @Setter public Tier tier;
    @Setter public String defaultImage = "https://i.imgur.com/3CWMDif.gif"/*https://officialpsds.com/imageview/7v/90/7v90vz_large.png"*/;
    public String toolImage = "https://www.freeiconspng.com/uploads/tool-icon-12.png";
    private final long startTime;

    // Utilities
    public MessageUtil messageUtil;
    public CommandManager commandManager;
    public ProcessUtil processUtil;
    public CodecUtil codecUtil;
    public TimeUtil timeUtil;
    public TicketHandler ticketHandler;

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

        this.ticketHandler = new TicketHandler();
        System.out.println("[NoxLib] Created Ticket Handler!");

        // Register Built-in Commands
        this.commandManager.register(new CloseTicket(), new AddUser(), new RemoveUser(), new Kick(), new Ban(), new Unban());

        System.out.println("[NoxLib] Loaded in " + (System.currentTimeMillis() - startTime) + "ms!");
    }
}
