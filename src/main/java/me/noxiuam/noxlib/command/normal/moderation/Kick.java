package me.noxiuam.noxlib.command.normal.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class Kick extends Command
{

    public Kick()
    {
        super("kick", "Kicks a member.", NoxLib.getInstance().getPrefix() + "kick <user> <reason>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (!ctx.getMember().hasPermission(Permission.KICK_MEMBERS))
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to kick member", "You do not have permission for this!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().isEmpty())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to kick member", "You did not mention anyone to kick! - `" + this.getUsage() + "`", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        Member user = ctx.getMessage().getMentionedMembers().get(0);
        if (ctx.getArgs().size() > 2 || ctx.getArgs().isEmpty() || user == null)
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to kick member", "You did not mention anyone to kick! - " + this.getUsage(), NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().size() == 1)
        {
            user.kick("No reason provided.").queue();
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Kicked", user.getAsMention() + " has been kicked!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        user.kick(ctx.getArgs().get(1)).queue();
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Kicked", user.getAsMention() + " has been kicked for `" + ctx.getArgs().get(1) + "`!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
    }
}
