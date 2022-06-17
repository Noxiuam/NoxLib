package me.noxiuam.noxlib.command.impl;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;

public class Help extends GenericCommand {
    public Help() {
        super("help", "Displays all commands provided by the bot.", NoxLib.getInstance().prefix + "help", Tier.BRONZE);
    }

    @Override
    public void execute(CommandContext ctx) {
        StringBuilder cmds = new StringBuilder();

        for (GenericCommand command : NoxLib.getInstance().getCommandManager().commands) {
            if (NoxLib.getInstance().getConfiguration().getBotTier().isAboveOrEqual(command.getRequiredTier())) {
                cmds.append(NoxLib.getInstance().getPrefix()).append(command.getName()).append(": ").append(command.getDescription()).append("\n");
            }
        }

        ctx.getChannel().sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbed("Available Commands", cmds.toString()).build()).queue();
    }
}
