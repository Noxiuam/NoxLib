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
            return;
        }

        ctx.getChannel().delete().queue();
        NoxLib.getInstance().getOpenTickets().remove(ctx.getAuthor().getAsTag());
    }
}
