package me.noxiuam.noxlib.command.impl.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Report extends GenericCommand
{
    public Report()
    {
        super("report", "Reports a user for a given reason.", NoxLib.getInstance().getPrefix() + "report <user id> <reason>", Tier.BRONZE);
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (!NoxLib.getInstance().getConfiguration().getBotTier().isAboveOrEqual(Tier.getByName("gold")) && NoxLib.getInstance().getReportsChannelId() == null)
        {
            return;
        }

        if (ctx.getArgs().isEmpty() || (ctx.getMessage().getMentionedMembers().isEmpty() && ctx.getArgs().get(0).length() > 18))
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to file report", "You did not specify anyone to report!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        boolean usingId = ctx.getArgs().get(0).length() == 18 && ctx.getMessage().getMentionedMembers().isEmpty();

        if (ctx.getMessage().getMentionedMembers().size() != 1 && !usingId)
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to file report", "You can only report one user at a time!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().size() == 1)
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to file report", "You did not specify a reason to report this user for!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        String reason = ctx.getArgs().get(1);
        Objects.requireNonNull(
                NoxLib.getInstance().getBotJda().getTextChannelById(NoxLib.getInstance().getReportsChannelId()))
                .sendMessageEmbeds
                        (NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(
                                "User Report",
                                "**User Reported:** " + (usingId ? "<@" + ctx.getArgs().get(0) + ">" : ctx.getMessage().getMentionedMembers().get(0).getAsMention()) +
                                "\n**Reason:** " + reason +
                                "\n**Reported by:** " + ctx.getMember().getAsMention(),
                                NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
        ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Successfully reported user", "The user has been reported to staff, thanks for letting us know!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));

    }
}
