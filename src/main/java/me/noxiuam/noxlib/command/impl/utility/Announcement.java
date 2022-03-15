package me.noxiuam.noxlib.command.impl.utility;

import lombok.*;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.command.util.data.GenericAnnouncement;
import me.noxiuam.noxlib.services.Tier;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.util.*;

@Getter
public class Announcement extends GenericCommand
{
    private final Map<String, GenericAnnouncement> runningAnnouncements = new HashMap<>();

    public static Announcement INSTANCE;

    public Announcement()
    {
        super("announcement", "A few commands to announce a message.", NoxLib.getInstance().getPrefix() + "announcement <command>", Tier.SILVER);
        INSTANCE = this;
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (!ctx.getMember().hasPermission(Permission.MESSAGE_MENTION_EVERYONE)) return;

        EmbedBuilder titleEmbed = NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Let's get started, shall we?",
                "Firstly, give your announcement a title.", NoxLib.getInstance().getImageDatabase().getToolImage());

        if (ctx.getArgs().isEmpty())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Command Failed", "You did not specify a valid sub-command to run!\nUsable Commands: `list, start, stop`", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
            return;
        }

        if (ctx.getArgs().get(0).equalsIgnoreCase("cancel"))
        {
            if (!this.runningAnnouncements.containsKey(ctx.getMember().getId()))
            {
                ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Command Failed", "You do not have an announcement running!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
                return;
            }

            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Announcement Canceled", "Okay, canceled your announcement.", NoxLib.getInstance().getImageDatabase().getToolImage()).build()).queue();
            return;
        }

        if (this.runningAnnouncements.containsKey(ctx.getMember().getId()))
        {
            ctx.getChannel().sendMessageEmbeds(
                    NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Command Failed",
                            "You can only have one announcement running at a time!",
                            NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
            return;
        }

        if (ctx.getArgs().get(0).equalsIgnoreCase("start"))
        {
            ctx.getMessage().replyEmbeds(titleEmbed.build()).queue();
            this.setup(ctx.getMember().getId(), ctx.getChannel().getId());
        }
    }

    private void setup(String id, String channelId)
    {
        this.runningAnnouncements.put(id, new GenericAnnouncement(channelId, 0, "", ""));
    }
}
