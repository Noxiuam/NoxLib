package me.noxiuam.noxlib.command.ticket;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;

public class CloseTicket extends Command {

    public CloseTicket() {
        super("close", "Closes a ticket.", "$close");
    }

    @Override
    public void execute(CommandContext ctx) {
        ctx.getChannel().delete().queue();
        NoxLib.getInstance().getOpenTickets().remove(ctx.getAuthor().getAsTag());
    }
}
