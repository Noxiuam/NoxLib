package me.noxiuam.noxlib.command.normal.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class Leave extends Command
{
    public Leave()
    {
        super("leave", "Leaves the current Voice Channel.", NoxLib.getInstance().getPrefix() + "leave");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        if (!selfVoiceState.inAudioChannel())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Leaving " + memberChannel.getName(), "I am not in a Voice Channel, so I can not leave!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Leaving " + memberChannel.getName(), "Left the Voice Channel successfully!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
        ctx.getGuild().getAudioManager().closeAudioConnection();
    }
}