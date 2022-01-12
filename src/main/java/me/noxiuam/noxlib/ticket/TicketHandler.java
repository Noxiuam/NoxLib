package me.noxiuam.noxlib.ticket;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.entities.Member;

public class TicketHandler
{

    public void createNewTicket(Member member)
    {
        if (!NoxLib.getInstance().getOpenTickets().contains(member.getUser().getAsTag()))
        {
            NoxLib.getInstance().getOpenTickets().add(member.getUser().getAsTag());
            NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId()).createTextChannel("ticket-" + member.getUser().getAsTag(), NoxLib.getInstance().getBotJda().getCategoryById(NoxLib.getInstance().getTicketCategoryId())).queue();
        }
    }
}
