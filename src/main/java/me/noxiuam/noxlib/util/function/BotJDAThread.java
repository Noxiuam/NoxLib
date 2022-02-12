package me.noxiuam.noxlib.util.function;

import lombok.SneakyThrows;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.SlashCommand;

import java.util.TimerTask;

public class BotJDAThread extends TimerTask
{
    private boolean hasRegistered = false;

    @Override @SneakyThrows
    public void run()
    {
        Thread.sleep(5000L);
        if (NoxLib.getInstance().getBotJda() != null && !hasRegistered)
        {
            System.out.println("[NoxLib] Registering Slash Commands!");
            this.hasRegistered = true;
            for (SlashCommand cmd : NoxLib.getInstance().getCommandManager().slashCommands)
            {
                NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId()).upsertCommand(cmd.getName().toLowerCase(), cmd.getDescription()).queue();
            }
        }
    }
}
