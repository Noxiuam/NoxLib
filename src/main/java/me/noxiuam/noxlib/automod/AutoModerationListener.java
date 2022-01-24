package me.noxiuam.noxlib.automod;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class AutoModerationListener extends ListenerAdapter
{

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
    {
        if (event.getAuthor().isBot() || event.isWebhookMessage())
        {
            return;
        }

        final String message = event.getMessage().getContentRaw();

        for (String word : NoxLib.getInstance().getAutoModeration().getBlacklistedWords())
        {
            if (message.contains(word))
            {
                event.getMessage().delete().queue();
                event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId()).sendMessage(
                        NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                                "Blacklisted Word Deleted",
                                "**Message:** `" + message + "`\n" +
                                        "**Channel:** `" + event.getChannel().getName() + "`\n" + "**Sent by:** `" + event.getMember().getUser().getAsTag() + "`",
                                NoxLib.getInstance().getDBNImage()).build()
                ).queue();
            }
        }
    }
}
