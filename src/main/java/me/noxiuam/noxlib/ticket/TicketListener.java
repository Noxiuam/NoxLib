package me.noxiuam.noxlib.ticket;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TicketListener extends ListenerAdapter
{
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event)
    {
        if (event.getChannel().getId().equalsIgnoreCase(NoxLib.getInstance().getTicketReactChannelId()))
        {
            NoxLib.getInstance().getTicketHandler().createNewTicket(Objects.requireNonNull(event.getMember()));
            event.getReaction().removeReaction(Objects.requireNonNull(event.getUser())).queue();
        }
    }

    @Override
    public void onTextChannelCreate(@NotNull TextChannelCreateEvent event)
    {
        if (event.getChannel().getName().startsWith("ticket-"))
        {
            event.getChannel().sendMessage(
                    NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("__Welcome to your ticket!__",
                            "Here you can get assistance from one of our staff members.\n\n Please be patient, as not all staff members can be online, we have lives too!", "https://www.freeiconspng.com/uploads/tool-icon-12.png").build()
            ).queue();
        }
    }
}
