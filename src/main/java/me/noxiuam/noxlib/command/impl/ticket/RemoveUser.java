package me.noxiuam.noxlib.command.impl.ticket;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;

public class RemoveUser extends GenericCommand
{
    public RemoveUser()
    {
        super("removeuser", "Removes a user from a ticket.", NoxLib.getInstance().getPrefix() + "removeuser <user id>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        TextChannel channel = NoxLib.getInstance().getBotJda().getTextChannelById(ctx.getChannel().getId());

        if (!ctx.getChannel().getName().startsWith("ticket-"))
        {
            return;
        }

        PermissionOverride permissionOverride = channel.getPermissionOverride(ctx.getMessage().getMentionedMembers().get(0));
        permissionOverride.getChannel().getManager().removePermissionOverride(ctx.getMessage().getMentionedMembers().get(0)).queue();
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("User Added to Ticket", "Removed " + ctx.getMessage().getMentionedMembers().get(0).getAsMention() + " from the ticket.", NoxLib.getInstance().getImageDatabase().getToolImage()).build()).queue();
    }
}
