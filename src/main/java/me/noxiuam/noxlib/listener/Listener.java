package me.noxiuam.noxlib.listener;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.SlashCommand;
import me.noxiuam.noxlib.flow.moderation.DeletedMessage;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


/*
 * This class will contain all features every bot will contain.
 * This will only include simple functionality.
 */
public class Listener extends ListenerAdapter
{

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {

        for (SlashCommand cmd : NoxLib.getInstance().getCommandManager().slashCommands)
        {
            if (event.getName().equalsIgnoreCase(cmd.getName()))
            {
                cmd.execute(event);
            }
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.isWebhookMessage() || event.getMessage().getContentRaw().equalsIgnoreCase("")) return;

        NoxLib.getInstance().getMessageCache().put(event.getMessageIdLong(), new DeletedMessage(event.getMessage(), event.getAuthor()));
        NoxLib.getInstance().getCommandManager().handle(event);
    }
}
