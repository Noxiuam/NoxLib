package me.noxiuam.noxlib.ticket;

import lombok.Setter;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.config.Config;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TicketListener extends ListenerAdapter
{
    @Setter public static String memberId = "";

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event)
    {

        if (Objects.requireNonNull(event.getUser()).isBot())
        {
            return;
        }

        if (event.getChannel().getId().equalsIgnoreCase(NoxLib.getInstance().getTicketReactChannelId()) && event.getReactionEmote().getEmote().getId().equalsIgnoreCase(Config.defaultTicketEmoteId))
        {
            setMemberId(Objects.requireNonNull(event.getMember()).getId());
            NoxLib.getInstance().getTicketHandler().createNewTicket(Objects.requireNonNull(event.getMember()));
            event.getReaction().removeReaction(Objects.requireNonNull(event.getUser())).queue();

            try
            {
                int temp = event.getReaction().getCount();
            }
            catch (IllegalStateException ignored)
            {
                Objects.requireNonNull(NoxLib.getInstance().getBotJda().getTextChannelById(event.getChannel().getId())).addReactionById(event.getMessageId(), Objects.requireNonNull(NoxLib.getInstance().getBotJda().getEmoteById(Config.defaultTicketEmoteId))).queue();
            }
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getChannel().getId().equalsIgnoreCase(NoxLib.getInstance().getTicketReactChannelId())) {
            event.getMessage().addReaction(Objects.requireNonNull(NoxLib.getInstance().getBotJda().getEmoteById(Config.defaultTicketEmoteId))).queue();
        }
    }
}
