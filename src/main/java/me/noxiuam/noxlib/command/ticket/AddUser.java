package me.noxiuam.noxlib.command.ticket;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.Permission;

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
        if (!ctx.getChannel().getName().startsWith("ticket-"))
        {
            return;
        }

        if (ctx.getArgs().isEmpty())
        {
            ctx.getMessage().reply(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Incorrect Usage!", "Usage: " + this.getDescription(), NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        ctx.getChannel().createPermissionOverride(ctx.getMessage().getMentionedMembers().get(0)).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE).queue();
        ctx.getMessage().reply(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("User Added to Ticket", "Added " + ctx.getMessage().getMentionedMembers().get(0).getAsMention() + " to the ticket.", NoxLib.getInstance().getImageDatabase().getToolImage()).build()).queue();
        ctx.getMessage().reply(ctx.getMessage().getMentionedMembers().get(0).getAsMention()).queue(ping -> {
            ping.delete().queue();
        });
    }
}
