package me.noxiuam.noxlib.command.impl.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;
import net.dv8tion.jda.api.Permission;

import java.util.concurrent.TimeUnit;

public class Unban extends GenericCommand {
    public Unban() {
        super("unban", "Unbans a member from their ID.", NoxLib.getInstance().getPrefix() + "unban <user id>", Tier.BRONZE);
    }

    @Override
    public void execute(CommandContext ctx) {
        if (!ctx.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to unban member", "You do not have permission for this!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().isEmpty()) {
            ctx.getChannel().sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to unban member", "You did not specify anyone to unban! - " + this.getUsage(), NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        ctx.getGuild().unban(ctx.getArgs().get(0)).queue();
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Unbanned", "<@" + ctx.getArgs().get(0) + "> has been unbanned!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
    }
}
