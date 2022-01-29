package me.noxiuam.noxlib.ticket;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.config.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TicketHandler
{

    public void createNewTicket(Member member)
    {
        if (!NoxLib.getInstance().getOpenTickets().contains(member.getUser().getAsTag()))
        {
            NoxLib.getInstance().getOpenTickets().add(member.getUser().getAsTag());
            Objects.requireNonNull(NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId())).createTextChannel("ticket-" + member.getUser().getAsTag(), NoxLib.getInstance().getBotJda().getCategoryById(NoxLib.getInstance().getTicketCategoryId()))
                    .queue(ticket -> {
                        ticket.createPermissionOverride(member).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                        ticket.sendMessageEmbeds(
                                    NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(Config.defaultTicketTitle,
                                            Config.defaultTicketMessage,
                                            Config.defaultTicketImage).build()
                            ).queue();

                            ticket.sendMessage("<@" + TicketListener.memberId + ">").queue(ping -> ping.delete().queueAfter(50, TimeUnit.MILLISECONDS));
                    });
        }
    }
}
