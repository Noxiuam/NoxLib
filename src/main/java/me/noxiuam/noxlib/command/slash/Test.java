package me.noxiuam.noxlib.command.slash;

import me.noxiuam.noxlib.command.SlashCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class Test extends SlashCommand
{
    public Test()
    {
        super("testSlash", "Slash command for testing purposes", "/testSlash");
    }

    @Override
    public void execute(SlashCommandEvent ctx) {
        ctx.reply("test").queue();
    }
}
