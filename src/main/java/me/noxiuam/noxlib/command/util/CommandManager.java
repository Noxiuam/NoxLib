package me.noxiuam.noxlib.command.util;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.SlashCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager
{
    public List<GenericCommand> commands = new ArrayList<>();
    public List<SlashCommand> slashCommands = new ArrayList<>();

    public void register(GenericCommand... commands)
    {
        Collections.addAll(this.commands, commands);
    }

    public void unregister(GenericCommand... commands)
    {
        this.commands.removeAll(Arrays.asList(commands));
    }

    public void unregisterSlashCommands(SlashCommand... slashCommands)
    {
        this.slashCommands.removeAll(Arrays.asList(slashCommands));
    }

    public void registerSlashCommands(SlashCommand... slashCommands)
    {
        Collections.addAll(this.slashCommands, slashCommands);
    }

    public void handle(MessageReceivedEvent event)
    {
        if (NoxLib.getInstance().getConfiguration() == null)
        {
            return;
        }

        String msg = event.getMessage().getContentRaw();
        String[] split = msg
                .replaceFirst("(?i)" + Pattern.quote(NoxLib.getInstance().getPrefix()),"")
                .split("\\s+");

        for (GenericCommand cmd : this.commands)
        {
            if (cmd != null && msg.startsWith(NoxLib.getInstance().getPrefix() + cmd.getName()))
            {
                CommandContext ctx = new CommandContext(event, Arrays.asList(split).subList(1, split.length));
                cmd.execute(ctx);
            }
        }
    }
}
