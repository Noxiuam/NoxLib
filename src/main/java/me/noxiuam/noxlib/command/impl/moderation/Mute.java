package me.noxiuam.noxlib.command.impl.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class Mute extends GenericCommand {

    public Mute() {
        super("mute", "Mutes a member.", NoxLib.getInstance().getPrefix() + "mute <user>", Tier.BRONZE);
    }

    @Override
    public void execute(CommandContext ctx) {
        if (!ctx.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to mute member", "You do not have permission for mute!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().isEmpty()) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to mute member", "You did not mention anyone to mute! - `" + this.getUsage() + "`", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        Member user = ctx.getMessage().getMentionedMembers().get(0);
        if (ctx.getArgs().size() > 2 || user == null) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to mute member", "You did not mention anyone to mute! - `" + this.getUsage() + "`", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        // temporary
        user.timeoutFor(5000L, TimeUnit.MINUTES).queue();
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Member Muted", user.getAsMention() + " has been muted!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
    }
}
