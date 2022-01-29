package me.noxiuam.noxlib.command;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager
{
    public List<Command> commands = new ArrayList<>();
    public List<SlashCommand> slashCommands = new ArrayList<>();

    public void register(Command... commands)
    {
        Collections.addAll(this.commands, commands);
    }

    public void registerSlashCommands(SlashCommand... slashCommands)
    {
        Collections.addAll(this.slashCommands, slashCommands);
    }

    public void handle(MessageReceivedEvent event)
    {
        if (NoxLib.getInstance().getConfig() == null)
        {
            return;
        }

        String msg = event.getMessage().getContentRaw();
        String[] split = msg
                .replaceFirst("(?i)" + Pattern.quote(NoxLib.getInstance().getPrefix()),"")
                .split("\\s+");

        for (Command cmd : this.commands)
        {
            if (cmd != null && msg.startsWith(NoxLib.getInstance().getPrefix() + cmd.getName()))
            {
                CommandContext ctx = new CommandContext(event, Arrays.asList(split).subList(1, split.length));
                cmd.execute(ctx);
            }
        }
    }
}
