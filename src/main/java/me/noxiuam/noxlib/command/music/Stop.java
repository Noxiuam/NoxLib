package me.noxiuam.noxlib.command.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.audio.GuildMusicManager;
import me.noxiuam.noxlib.audio.PlayerManager;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Stop extends Command
{
    public Stop()
    {
        super("stop", "Stops playing music and clears the queue.", NoxLib.getInstance().getPrefix() + "stop");
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

        if (!selfVoiceState.inVoiceChannel())
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Stopping Music", "I am not in a Voice Channel!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!memberVoiceState.inVoiceChannel())
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Stopping Music", "You need to be in a Voice Channel first!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!Objects.equals(memberVoiceState.getChannel(), selfVoiceState.getChannel()))
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Stopping Music", "You need to be in the same Voice Channel as me first!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        musicManager.scheduler.player.stopTrack();
        musicManager.scheduler.queue.clear();
        channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Stopping Music", "Stopped playing music and cleared the queue!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
    }
}
