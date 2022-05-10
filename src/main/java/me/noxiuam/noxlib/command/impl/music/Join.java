package me.noxiuam.noxlib.command.impl.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.concurrent.TimeUnit;

public class Join extends GenericCommand {
    public Join() {
        super("join", "Makes the bot join the current channel.", NoxLib.getInstance().getPrefix() + "join", Tier.PLATINUM);
    }

    @Override
    public void execute(CommandContext ctx) {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        if (selfVoiceState.inAudioChannel()) {
            ctx.getMessage().replyEmbeds(
                    NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                            "Command Failed",
                            "I'm already in use! Please wait or get a moderator to move me.", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));

            return;
        }

        if (!memberVoiceState.inAudioChannel()) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Command Failed", "You need to be in a Voice Channel first!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        audioManager.openAudioConnection(memberChannel);
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Joining " + memberChannel.getName(), "Joined the Voice Channel successfully!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();

    }
}
