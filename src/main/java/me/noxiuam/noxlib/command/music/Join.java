package me.noxiuam.noxlib.command.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class Join extends Command
{
    public Join()
    {
        super("join", "Makes the bot join the current channel.", NoxLib.getInstance().getPrefix() + "join");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final VoiceChannel memberChannel = memberVoiceState.getChannel();

        if (selfVoiceState.inVoiceChannel())
        {
            ctx.getChannel().sendMessage(
                    NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                            "Error Joining Voice Channel",
                            "I'm already in use! Please wait or get a moderator to move me.", NoxLib.getInstance().getDefaultImage()).build()).queue();

            return;
        }

        if (!memberVoiceState.inVoiceChannel())
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Joining Voice Channel", "You need to be in a Voice Channel first!", NoxLib.getInstance().getDefaultImage()).build()).queue();
            return;
        }

        audioManager.openAudioConnection(memberChannel);
        ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Joining " + memberChannel.getName(), "Joined the Voice Channel successfully!", NoxLib.getInstance().getDefaultImage()).build()).queue();

    }
}
