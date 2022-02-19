package me.noxiuam.noxlib.command.impl.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.services.Tier;
import me.noxiuam.noxlib.util.audio.GuildMusicManager;
import me.noxiuam.noxlib.util.audio.PlayerManager;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class Loop extends GenericCommand
{
    public Loop()
    {
        super("loop", "Loops through the currently playing track.", NoxLib.getInstance().getPrefix() + "loop", Tier.PLATINUM);
    }

    @Override
    public void execute(CommandContext ctx)
    {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final AudioPlayer audioPlayer = musicManager.audioPlayer;

        if (!selfVoiceState.inAudioChannel())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Looping Music", "I am not in a Voice Channel!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!memberVoiceState.inAudioChannel())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Looping Music", "You need to be in a Voice Channel first!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel()))
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Looping Music", "You need to be in the same Voice Channel as me first!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (audioPlayer.getPlayingTrack() == null)
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Looping Music", "There is no track playing currently!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        final boolean looping = !musicManager.scheduler.repeating;
        musicManager.scheduler.repeating = looping;
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Music Looping Toggled", "The currently playing track will " + (looping ? " **now loop**" : "**no longer loop**") + " after it is over.", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
    }
}
