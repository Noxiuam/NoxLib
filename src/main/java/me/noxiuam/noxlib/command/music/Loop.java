package me.noxiuam.noxlib.command.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.audio.GuildMusicManager;
import me.noxiuam.noxlib.audio.PlayerManager;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class Loop extends Command
{
    public Loop()
    {
        super("loop", "Loops through the currently playing track.", NoxLib.getInstance().getPrefix() + "loop");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final AudioPlayer audioPlayer = musicManager.audioPlayer;

        if (!selfVoiceState.inVoiceChannel())
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Looping Music", "I am not in a Voice Channel!", NoxLib.getInstance().getDefaultImage()).build()).queue();
            return;
        }

        if (!memberVoiceState.inVoiceChannel())
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Looping Music", "You need to be in a Voice Channel first!", NoxLib.getInstance().getDefaultImage()).build()).queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel()))
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Looping Music", "You need to be in the same Voice Channel as me first!", NoxLib.getInstance().getDefaultImage()).build()).queue();
            return;
        }

        if (audioPlayer.getPlayingTrack() == null)
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Looping Music", "There is no track playing currently!", NoxLib.getInstance().getDefaultImage()).build()).queue();
            return;
        }

        final boolean looping = !musicManager.scheduler.repeating;
        musicManager.scheduler.repeating = looping;
        channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Music Looping Toggled", "The currently playing track will " + (looping ? " **now loop**" : "**no longer loop**") + " after it is over.", NoxLib.getInstance().getDefaultImage()).build()).queue();
    }
}
