package me.noxiuam.noxlib.command.ticket;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;

public class CloseTicket extends Command
{

    public CloseTicket()
    {
        super("close", "Closes a ticket.", NoxLib.getInstance().getPrefix() + "close");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (!ctx.getChannel().getName().startsWith("ticket-"))
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error closing ticket", "This channel is not a ticket!", NoxLib.getInstance().getDefaultImage()).build()).queue();
            return;
        }

        ctx.getChannel().delete().queue();
        NoxLib.getInstance().getOpenTickets().remove(ctx.getAuthor().getAsTag());
    }
}
