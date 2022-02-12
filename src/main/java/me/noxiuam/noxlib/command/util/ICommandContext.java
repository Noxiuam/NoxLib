package me.noxiuam.noxlib.command.util;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

public interface ICommandContext
{
    Guild getGuild();

    MessageReceivedEvent getEvent();

    default MessageChannel getChannel()
    {
        return this.getEvent().getChannel();
    }

    default Message getMessage()
    {
        return this.getEvent().getMessage();
    }

    default User getAuthor()
    {
        return this.getEvent().getAuthor();
    }

    default Member getMember()
    {
        return this.getEvent().getMember();
    }

    default JDA getJDA()
    {
        return this.getEvent().getJDA();
    }

    default ShardManager getShardManager()
    {
        return this.getJDA().getShardManager();
    }

    default User getSelfUser()
    {
        return this.getJDA().getSelfUser();
    }

    default Member getSelfMember()
    {
        return this.getGuild().getSelfMember();
    }
}
