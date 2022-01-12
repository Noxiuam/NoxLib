package me.noxiuam.noxlib.listener;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/*
 * This class should only be registered if the Bot is going to have a logging system.
 * This will later be updated to use another library that will control what tier has what within NoxLib.
 */
public class LogListener extends ListenerAdapter
{

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
    {
        if (event.getAuthor().isBot() || event.isWebhookMessage())
        {
            return;
        }

        String message = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        String tag = event.getMember().getUser().getAsTag();

        for (String word : NoxLib.getInstance().getAutoModeration().getBlacklistedWords())
        {
            if (message.contains(word))
            {
                event.getMessage().delete().queue();
                event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId()).sendMessage(
                        NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                                "Blacklisted Word Deleted",
                                "**Message:** `" + message + "`\n" +
                                        "**Channel:** `" + channel.getName() + "`\n" + "**Sent by:** `" + tag + "`",
                                NoxLib.getInstance().getDBNImage()).build()
                ).queue();
            }
        }
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Join",
                        "**Member:** `" + event.getMember().getUser().getAsTag() + "`\n"
                                + "**Channel:** `" + event.getChannelJoined().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getDBNImage()).build()
        ).queue();
    }

    @Override
    public void onGuildVoiceMove(@NotNull GuildVoiceMoveEvent event)
    {
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Change",
                        "**Member:** `" + event.getMember().getUser().getAsTag() + "`\n" +
                                "**Channels:** `" + event.getChannelLeft().getName() + "` **->** `" + event.getChannelJoined().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getDBNImage()).build()
        ).queue();
    }

    @Override
    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event)
    {
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Leave",
                        "**Member:** `" + event.getMember().getUser().getAsTag() + "`\n"
                                + "**Channel:** `" + event.getChannelLeft().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getDBNImage()).build()
        ).queue();
    }
}
