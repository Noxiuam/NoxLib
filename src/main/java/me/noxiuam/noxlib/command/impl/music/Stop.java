package me.noxiuam.noxlib.command.impl.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.services.Tier;
import me.noxiuam.noxlib.util.audio.GuildMusicManager;
import me.noxiuam.noxlib.util.audio.PlayerManager;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Stop extends GenericCommand
{
    public Stop()
    {
        super("stop", "Stops playing music and clears the queue.", NoxLib.getInstance().getPrefix() + "stop", Tier.PLATINUM);
    }

    @Override
    public void execute(CommandContext ctx)
    {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());

        if (!selfVoiceState.inAudioChannel())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Stopping Music", "I am not in a Voice Channel!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!memberVoiceState.inAudioChannel())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Stopping Music", "You need to be in a Voice Channel first!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!Objects.equals(memberVoiceState.getChannel(), selfVoiceState.getChannel()))
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Stopping Music", "You need to be in the same Voice Channel as me first!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        musicManager.scheduler.player.stopTrack();
        musicManager.scheduler.queue.clear();
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Stopping Music", "Stopped playing music and cleared the queue!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
    }
}
