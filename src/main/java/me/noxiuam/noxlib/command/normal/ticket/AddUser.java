package me.noxiuam.noxlib.command.normal.ticket;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.TimeUnit;

public class AddUser extends Command
{

    public AddUser()
    {
        super("adduser", "Adds a user to the ticket.", NoxLib.getInstance().getPrefix() + "adduser <user id>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        TextChannel channel = NoxLib.getInstance().getBotJda().getTextChannelById(ctx.getChannel().getIdLong());

        if (!ctx.getChannel().getName().startsWith("ticket-"))
        {
            return;
        }

        if (ctx.getArgs().isEmpty())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Incorrect Usage!", "Usage: " + this.getDescription(), NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        assert channel != null;
        channel.createPermissionOverride(ctx.getMessage().getMentionedMembers().get(0)).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("User Added to Ticket", "Added " + ctx.getMessage().getMentionedMembers().get(0).getAsMention() + " to the ticket.", NoxLib.getInstance().getImageDatabase().getToolImage()).build()).queue();
        ctx.getMessage().reply(ctx.getMessage().getMentionedMembers().get(0).getAsMention()).queue(ping -> {
            ping.delete().queue();
        });
    }
}
