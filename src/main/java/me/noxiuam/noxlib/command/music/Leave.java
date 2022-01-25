package me.noxiuam.noxlib.command.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class Leave extends Command
{
    public Leave()
    {
        super("leave", "Leaves the current Voice Channel.", NoxLib.getInstance().getPrefix() + "leave");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final VoiceChannel memberChannel = memberVoiceState.getChannel();

        if (!selfVoiceState.inVoiceChannel())
        {
            channel.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Leaving " + memberChannel.getName(), "I am not in a Voice Channel, so I can not leave!", NoxLib.getInstance().getDefaultImage()).build()).queue();
            return;
        }

        ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Leaving " + memberChannel.getName(), "Left the Voice Channel successfully!", NoxLib.getInstance().getDefaultImage()).build()).queue();
        ctx.getGuild().getAudioManager().closeAudioConnection();
    }
}
