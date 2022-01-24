package me.noxiuam.noxlib.listener;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

/*
 * This class should only be registered if the Bot is going to have a logging system.
 * This will later be updated to use another library that will control what tier has what within NoxLib.
 */
public class LogListener extends ListenerAdapter
{

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Join",
                        "**Member:** " + event.getMember().getAsMention() + "\n"
                                + "**Channel:** `" + event.getChannelJoined().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event)
    {
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Change",
                        "**Member:** " + event.getMember().getAsMention() + "\n" +
                                "**Channels:** `" + event.getChannelLeft().getName() + "` **->** `" + event.getChannelJoined().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Leave",
                        "**Member:** " + event.getMember().getAsMention() + "\n"
                                + "**Channel:** `" + event.getChannelLeft().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildBan(GuildBanEvent event) {
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Banned",
                        "**Member:** " + event.getUser().getAsMention() + "",
                        NoxLib.getInstance().getDefaultImage()).build()
        ).queue();
    }
}
