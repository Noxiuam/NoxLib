package me.noxiuam.noxlib.ticket;

import lombok.Setter;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.config.Config;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TicketListener extends ListenerAdapter
{
    @Setter private String memberId = "";

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event)
    {

        if (Objects.requireNonNull(event.getUser()).isBot())
        {
            return;
        }

        if (event.getChannel().getId().equalsIgnoreCase(NoxLib.getInstance().getTicketReactChannelId()) && event.getReactionEmote().getEmote().getId().equalsIgnoreCase(Config.defaultTicketEmoteId))
        {

            this.setMemberId(Objects.requireNonNull(event.getMember()).getId());
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
            event.getMessage().addReaction(NoxLib.getInstance().getBotJda().getEmoteById(Config.defaultTicketEmoteId)).queue();
        }
    }

    @Override
    public void onTextChannelCreate(@NotNull TextChannelCreateEvent event)
    {
        if (event.getChannel().getName().startsWith("ticket-"))
        {
            event.getChannel().sendMessage(
                    NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(Config.defaultTicketTitle,
                            Config.defaultTicketMessage,
                            Config.defaultTicketImage).build()
            ).queue();

            event.getChannel().sendMessage("<@" + this.memberId + ">").queue(ping -> ping.delete().queueAfter(50, TimeUnit.MILLISECONDS));
        }
    }
}
