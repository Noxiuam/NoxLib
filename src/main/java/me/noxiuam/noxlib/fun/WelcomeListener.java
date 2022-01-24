package me.noxiuam.noxlib.fun;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class WelcomeListener extends ListenerAdapter
{

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event)
    {
        Objects.requireNonNull(event.getGuild().getTextChannelById(NoxLib.getInstance().getWelcomeChannelId()))
                .sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Welcome to " + event.getGuild().getName() + "!", "Welcome <@" + event.getMember().getId() + ">, enjoy your stay!", NoxLib.getInstance().getDefaultImage()).build()).queue();
    }
}
