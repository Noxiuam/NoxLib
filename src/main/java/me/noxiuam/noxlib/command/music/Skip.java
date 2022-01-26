package me.noxiuam.noxlib.command.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.audio.GuildMusicManager;
import me.noxiuam.noxlib.audio.PlayerManager;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.TimeUnit;

public class Skip extends Command
{
    public Skip()
    {
        super("skip", "Skips the currently playing track.", NoxLib.getInstance().getPrefix() + "skip");
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
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Skipping Music", "I am not in a Voice Channel!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!memberVoiceState.inVoiceChannel())
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Skipping Music", "You need to be in a Voice Channel first!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel()))
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Skipping Music", "You need to be in the same Voice Channel as me first!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (audioPlayer.getPlayingTrack() == null)
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Skipping Music", "There is no track playing currently!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        musicManager.scheduler.nextTrack();
        channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Skipped Music", "Successfully skipped track.\n\n**Now Playing:** " + audioPlayer.getPlayingTrack().getInfo().title, NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
    }
}
