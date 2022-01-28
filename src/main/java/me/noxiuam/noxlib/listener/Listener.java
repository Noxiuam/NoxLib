package me.noxiuam.noxlib.listener;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.flow.moderation.DeletedMessage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


/*
 * This class will contain all features every bot will contain.
 * This will only include simple functionality.
 */
public class Listener extends ListenerAdapter
{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if (event.isWebhookMessage()) return;

        NoxLib.getInstance().getMessageCache().put(event.getMessageIdLong(), new DeletedMessage(event.getMessage(), event.getAuthor()));
        NoxLib.getInstance().getCommandManager().handle(event);
    }
}
