package me.noxiuam.noxlib.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class CommandContext implements ICommandContext
{

    private MessageReceivedEvent event;
    private SlashCommandEvent slashEvent;
    private final List<String> args;

    public CommandContext(MessageReceivedEvent event, List<String> args)
    {
        this.event = event;
        this.args = args;
    }

    @Override
    public Guild getGuild()
    {
        return this.getEvent().getGuild();
    }

    @Override
    public MessageReceivedEvent getEvent()
    {
        return this.event;
    }

    public List<String> getArgs()
    {
        return this.args;
    }

}
