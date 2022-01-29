package me.noxiuam.noxlib.listener;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
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
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasBasicLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Join",
                        "**Member:** " + event.getMember().getAsMention() + "\n"
                                + "**Channel:** `" + event.getChannelJoined().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onMessageDelete(@NotNull MessageDeleteEvent event)
    {
        if (NoxLib.getInstance().getMessageCache().containsKey(event.getMessageIdLong()))
        {
            Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                    NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                            "Message Deleted",
                            "**Author:** " + NoxLib.getInstance().getMessageCache().get(event.getMessageIdLong()).getAuthor().getAsMention() + "\n"
                                    + "**Channel:** " + event.getChannel().getAsMention() + "\n"
                                    + "**Message:** `" + NoxLib.getInstance().getMessageCache().get(event.getMessageIdLong()).getMessage().getContentRaw() + "`\n"
                                    + "**Message ID:** `" + event.getMessageIdLong() + "`\n"
                                    + "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                            NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
            ).queue();
        }
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event)
    {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasBasicLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Change",
                        "**Member:** " + event.getMember().getAsMention() + "\n" +
                                "**Channels:** " + event.getChannelLeft().getAsMention() + " **->** " + event.getChannelJoined().getAsMention() + "\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasBasicLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Leave",
                        "**Member:** " + event.getMember().getAsMention() + "\n"
                                + "**Channel:** " + event.getChannelLeft().getAsMention() + "\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildBan(GuildBanEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Banned",
                        "**Member:** " + event.getUser().getAsMention(),
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Nickname Changed",
                        "**Member:** " + event.getUser().getAsMention() +
                        "\n**Change:** `" + event.getOldNickname() + "` **->** `" + event.getNewNickname() + "`"
                        + "\n**Time:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onChannelCreate(@NotNull ChannelCreateEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Channel Created",
                        "**Channel Name:** " + event.getChannel().getAsMention() +
                                "\n**Channel Type:** `" + event.getChannel().getType().name() + "`" +
                                "\n**Time:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onChannelDelete(@NotNull ChannelDeleteEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Voice Channel Created",
                        "**Channel Name:** `" + event.getChannel().getName() + "`" +
                                "\n**Channel Type:** `" + event.getChannel().getType().name() + "`" +
                                "\n**Time:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        StringBuilder sb = new StringBuilder();
        boolean muted = event.getMember().getVoiceState().isGuildMuted();

        for (Member member : event.getVoiceState().getChannel().getMembers())
        {
            if (member.hasPermission(Permission.VOICE_MUTE_OTHERS))
            {
                sb.append(member.getUser().getAsMention() + ", ");
            }
        }

        sb.deleteCharAt(sb.length() - 2);
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessageEmbeds(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member " + (muted ? "S" : "Uns") + "erver Muted",
                        "**Member:** " + event.getMember().getAsMention() + "" +
                                "\n**Moderators in channel:** " + sb +
                                "\n**Channel:** `" + event.getVoiceState().getChannel().getName() + "`" +
                                "\n**Time:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }
}
