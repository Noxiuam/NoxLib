package me.noxiuam.noxlib.command.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.*;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class Ban extends Command
{

    public Ban()
    {
        super("ban", "Bans a member.", NoxLib.getInstance().getPrefix() + "ban <user> <reason>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        Member user = ctx.getMessage().getMentionedMembers().get(0);
        if (ctx.getArgs().size() > 2 || ctx.getArgs().isEmpty() || user == null)
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to ban member", "You did not mention anyone to ban! - `" + this.getUsage() + "`", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().size() == 1)
        {
            user.ban(0, "No reason provided.").queue();
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Banned", user.getAsMention() + " has been banned!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        user.ban(0, ctx.getArgs().get(1)).queue();
        ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Banned", user.getAsMention() + " has been banned for `" + ctx.getArgs().get(1) + "`!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
    }
}
