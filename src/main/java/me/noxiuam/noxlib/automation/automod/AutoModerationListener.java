package me.noxiuam.noxlib.automation.automod;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class AutoModerationListener extends ListenerAdapter
{

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage() || NoxLib.getInstance().getConfig() == null)
        {
            return;
        }

        final String message = event.getMessage().getContentRaw();

        for (String word : NoxLib.getInstance().getAutoModeration().getBlacklistedWords())
        {
            if (message.contains(word))
            {
                event.getMessage().delete().queue();
                event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId()).sendMessageEmbeds(
                        NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                                "Blacklisted Word Deleted",
                                "**Message:** `" + message + "`\n" +
                                        "**Channel:** `" + event.getChannel().getName() + "`\n" + "**Sent by:** `" + event.getMember().getUser().getAsTag() + "`",
                                NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
                ).queue();
            }
        }
    }
}
