package me.noxiuam.noxlib.command.impl.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.util.audio.PlayerManager;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class Play extends GenericCommand
{
    public Play()
    {
        super("play", "Plays a song.", NoxLib.getInstance().getPrefix() + "play <song>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        final MessageChannel channel = ctx.getChannel();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (ctx.getArgs().isEmpty()) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Playing Music", "Usage: " + this.getUsage(), NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!memberVoiceState.inAudioChannel()) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Joining Voice Channel", "You're not in a voice channel, so I have nowhere to connect to!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (!selfVoiceState.inAudioChannel()) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Joining " + memberVoiceState.getChannel().getName(), "Joined the Voice Channel successfully!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
            ctx.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());
        }

        String link = String.join(" ", ctx.getArgs());
        if (!isUrl(link)) {
            link = "ytsearch:" + link;
        }

        PlayerManager.getInstance().loadAndPlay(channel, link);
    }

    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
