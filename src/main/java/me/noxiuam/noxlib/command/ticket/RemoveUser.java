package me.noxiuam.noxlib.command.ticket;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.Permission;

public class RemoveUser extends Command
{

    public RemoveUser()
    {
        super("removeuser", "Removes a user to the ticket.", NoxLib.getInstance().getPrefix() + "removeuser <user id>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (ctx.getArgs().isEmpty())
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Incorrect Usage!", "Usage: " + this.getDescription(), NoxLib.getInstance().defaultImage).build()).queue();
            return;
        }

        ctx.getChannel().createPermissionOverride(ctx.getMessage().getMentionedMembers().get(0)).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE).queue();
        ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("User Added to Ticket", "Added " + ctx.getMessage().getMentionedMembers().get(0).getAsMention() + " to the ticket.", "https://www.freeiconspng.com/uploads/tool-icon-12.png").build()).queue();
        ctx.getChannel().sendMessage(ctx.getMessage().getMentionedMembers().get(0).getAsMention()).queue(ping -> {
            ping.delete().queue();
        });
    }
}
