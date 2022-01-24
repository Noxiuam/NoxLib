package me.noxiuam.noxlib.command.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.entities.Member;

public class Kick extends Command
{

    public Kick()
    {
        super("kick", "Kicks a member.", "$kick <user> <reason>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        Member user = ctx.getMessage().getMentionedMembers().get(0);
        if (ctx.getArgs().size() > 2 || ctx.getArgs().isEmpty() || user == null)
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to kick member", "You did not mention anyone to kick! - " + this.getUsage(), NoxLib.getInstance().defaultImage).build()).queue();
            return;
        }

        if (ctx.getArgs().size() == 1)
        {
            user.kick("No reason provided.").queue();
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Kicked", user.getAsMention() + " has been kicked!", NoxLib.getInstance().defaultImage).build()).queue();
            return;
        }

        user.kick(ctx.getArgs().get(1)).queue();
        ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Kicked", user.getAsMention() + " has been kicked for `" + ctx.getArgs().get(1) + "`!", NoxLib.getInstance().defaultImage).build()).queue();
    }
}
