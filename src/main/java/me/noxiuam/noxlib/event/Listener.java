package me.noxiuam.noxlib.event;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.feature.message.AutoReponseMessage;
import me.noxiuam.noxlib.command.SlashCommand;
import me.noxiuam.noxlib.util.data.user.DeletedMessage;
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
    public void onSlashCommand(@NotNull SlashCommandEvent event)
    {
        for (SlashCommand cmd : NoxLib.getInstance().getCommandManager().slashCommands)
        {
            if (event.getName().equalsIgnoreCase(cmd.getName()))
            {
                cmd.execute(event);
            }
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event)
    {
        if (event.isWebhookMessage() || event.getMessage().getContentRaw().equalsIgnoreCase("")) return;

        if (NoxLib.getInstance().getConfiguration().getBotTier().getName().equalsIgnoreCase("silver") || NoxLib.getInstance().getTierHandler().isTopTier(NoxLib.getInstance().getConfiguration().getBotTier()))
        {
            for (AutoReponseMessage msg : NoxLib.getInstance().getAutoReponseHandler().getAutoResponses())
            {
                if (msg.getTrigger().equalsIgnoreCase(event.getMessage().getContentRaw()))
                {
                    event.getMessage().reply(NoxLib.getInstance().getAutoReponseHandler().getAutoResponse(event.getMessage().getContentRaw()).getResponse()).queue();
                }
            }
        }

        NoxLib.getInstance().getMessageCache().put(event.getMessageIdLong(), new DeletedMessage(event.getMessage(), event.getAuthor()));
        NoxLib.getInstance().getCommandManager().handle(event);
    }
}
