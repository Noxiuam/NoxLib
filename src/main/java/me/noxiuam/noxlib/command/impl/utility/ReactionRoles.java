package me.noxiuam.noxlib.command.impl.utility;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import net.dv8tion.jda.api.entities.MessageChannel;

public class ReactionRoles extends GenericCommand
{
    public ReactionRoles()
    {
        super("reactionrole", "All reaction role related things can be found within this command.", NoxLib.getInstance().getPrefix() + "reactionrole <subcommand> <value>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        MessageChannel channel = ctx.getChannel();
        if (ctx.getArgs().isEmpty())
        {
            channel.sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Reaction Roles Error", "You did not provide a subcommand!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
            return;
        }

        if (ctx.getArgs().get(0).equalsIgnoreCase("createmenu"))
        {
            channel.sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(ctx.getArgs().get(1), ctx.getArgs().get(2), NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
        }

    }
}
