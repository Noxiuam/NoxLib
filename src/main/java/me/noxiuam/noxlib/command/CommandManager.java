package me.noxiuam.noxlib.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager
{
    public List<Command> commands = new ArrayList<>();

    public void register(Command cmd)
    {
        this.commands.add(cmd);
    }

    public void unregister(Command cmd)
    {
        this.commands.remove(cmd);
    }

    public void handle(GuildMessageReceivedEvent event)
    {
        String msg = event.getMessage().getContentRaw();
        String[] split = msg
                .replaceFirst("(?i)" + Pattern.quote("$"),"")
                .split("\\s+");

        for (Command cmd : this.commands){
            if (cmd != null && msg.startsWith("$" + cmd.getName())) {
                CommandContext ctx = new CommandContext(event, Arrays.asList(split).subList(1, split.length));
                cmd.execute(ctx);
            }
        }
    }
}
