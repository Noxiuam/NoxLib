package me.noxiuam.noxlib.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    public List<Command> commands = new ArrayList<>();

    public void register(Command cmd) {
        if (cmd.getName().length() > 0) {
            this.commands.add(cmd);
        }
    }

    public void handle(GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        String[] split = msg
                .replaceFirst("(?i)" + Pattern.quote("$"),"")
                .split("\\s+");

        for (Command cmd : this.commands) {
            if (cmd != null && msg.startsWith("$" + cmd.getName())) {
                List<String> args = Arrays.asList(split).subList(1, split.length);
                CommandContext ctx = new CommandContext(event, args);
                cmd.execute(ctx);
            }
        }
    }
}
