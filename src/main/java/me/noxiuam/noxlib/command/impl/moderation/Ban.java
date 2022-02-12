package me.noxiuam.noxlib.command.impl.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.*;
import me.noxiuam.noxlib.command.util.CommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class Ban extends GenericCommand
{

    public Ban()
    {
        super("ban", "Bans a member.", NoxLib.getInstance().getPrefix() + "ban <user> <reason>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (!ctx.getMember().hasPermission(Permission.BAN_MEMBERS))
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to ban member", "You do not have permission for this!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().isEmpty())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to ban member", "You did not mention anyone to ban! - `" + this.getUsage() + "`", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        Member user = ctx.getMessage().getMentionedMembers().get(0);
        if (ctx.getArgs().size() > 2 || user == null)
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to ban member", "You did not mention anyone to ban! - `" + this.getUsage() + "`", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().size() == 1)
        {
            user.ban(0, "No reason provided.").queue();
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Banned", user.getAsMention() + " has been banned!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        user.ban(0, ctx.getArgs().get(1)).queue();
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Banned", user.getAsMention() + " has been banned for `" + ctx.getArgs().get(1) + "`!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
    }
}
