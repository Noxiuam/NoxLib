package me.noxiuam.noxlib.listener;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.StringUtils;
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

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Join",
                        "**Member:** " + event.getMember().getAsMention() + "\n"
                                + "**Channel:** `" + event.getChannelJoined().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event)
    {
        if (NoxLib.getInstance().getMessageCache().containsKey(event.getMessageIdLong()))
        {
            Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                    NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                            "Message Deleted",
                            "**Author:** " + NoxLib.getInstance().getMessageCache().get(event.getMessageIdLong()).getAuthor() + "\n"
                                    + "**Channel:** " + event.getChannel().getAsMention() + "\n"
                                    + "**Message:** `" + NoxLib.getInstance().getMessageCache().get(event.getMessageIdLong()).getMessage() + "`\n"
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

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Change",
                        "**Member:** " + event.getMember().getAsMention() + "\n" +
                                "**Channels:** `" + event.getChannelLeft().getName() + "` **->** `" + event.getChannelJoined().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasBasicLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Voice Leave",
                        "**Member:** " + event.getMember().getAsMention() + "\n"
                                + "**Channel:** `" + event.getChannelLeft().getName() + "`\n" +
                                "**Timestamp:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildBan(GuildBanEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Banned",
                        "**Member:** " + event.getUser().getAsMention(),
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Member Nickname Changed",
                        "**Member:** " + event.getUser().getAsMention() +
                        "\n**Change:** `" + event.getOldNickname() + "` **->** `" + event.getNewNickname() + "`"
                        + "\n**Time:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Voice Channel Created",
                        "**Channel Name:** `" + event.getChannel().getName() + "`" +
                                "\n**Time:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Voice Channel Deleted",
                        "**Channel Name:** `" + event.getChannel().getName() + "`" +
                                "\n**Time:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onTextChannelCreate(TextChannelCreateEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Text Channel Created",
                        "**Channel Name:** `" + event.getChannel().getName() + "`" +
                                "\n**Time:** `" + NoxLib.getInstance().getTimeUtil().getCurrentTime("hh:mm a MM/dd/yyyy") + "`",
                        NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()
        ).queue();
    }

    @Override
    public void onTextChannelDelete(TextChannelDeleteEvent event) {
        if (NoxLib.getInstance().getConfig() == null || !NoxLib.getInstance().getTierHandler().hasAdvancedLogging(NoxLib.getInstance().getConfig().getBotTier())) return;

        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
                NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                        "Text Channel Created",
                        "**Channel Name:** `" + event.getChannel().getName() + "`" +
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
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getLogChannelId())).sendMessage(
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
