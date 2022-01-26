package me.noxiuam.noxlib.listener;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.flow.moderation.DeletedMessage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


/*
 * This class will contain all features every bot will contain.
 * This will only include simple functionality.
 */
public class Listener extends ListenerAdapter
{
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
    {
        if (event.getAuthor().isBot() || event.isWebhookMessage()) return;
        NoxLib.getInstance().getMessageCache().put(event.getMessageIdLong(), new DeletedMessage(event.getMessage().getContentRaw(), event.getAuthor().getAsMention()));
        NoxLib.getInstance().getCommandManager().handle(event);
    }
}
