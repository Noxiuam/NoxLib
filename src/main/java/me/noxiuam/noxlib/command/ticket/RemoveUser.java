package me.noxiuam.noxlib.command.ticket;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.Permission;

public class RemoveUser extends Command
{
    public RemoveUser()
    {
        super("removeuser", "Removes a user from a ticket.", NoxLib.getInstance().getPrefix() + "removeuser <user id>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (!ctx.getChannel().getName().startsWith("ticket-"))
        {
            return;
        }

        ctx.getChannel().createPermissionOverride(ctx.getMessage().getMentionedMembers().get(0)).setDeny(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE).queue();
        ctx.getMessage().reply(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("User Added to Ticket", "Removed " + ctx.getMessage().getMentionedMembers().get(0).getAsMention() + " from the ticket.", NoxLib.getInstance().getImageDatabase().getToolImage()).build()).queue();
    }
}
