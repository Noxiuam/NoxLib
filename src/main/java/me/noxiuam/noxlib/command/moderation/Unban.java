package me.noxiuam.noxlib.command.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;

public class Unban extends Command
{
    public Unban()
    {
        super("unban", "Unbans a member from their ID.", NoxLib.getInstance().getPrefix() + "unban <user id>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (ctx.getArgs().isEmpty())
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to unban member", "You did not specify anyone to unban! - " + this.getUsage(), NoxLib.getInstance().DBNImage).build()).queue();
            return;
        }

        ctx.getGuild().unban(ctx.getArgs().get(0)).queue();
        ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Unbanned", "<@" + ctx.getArgs().get(0) + "> has been unbanned!", NoxLib.instance.getDBNImage()).build()).queue();
    }
}
