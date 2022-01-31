package me.noxiuam.noxlib.command.normal.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Purge extends Command
{
    public Purge()
    {
     super("purge", "Deletes a certain amount of messages.", NoxLib.getInstance().getPrefix() + "purge <amount>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (!ctx.getMember().hasPermission(Permission.MESSAGE_MANAGE))
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Deleting Messages", "You do not have permission for this!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (ctx.getArgs().isEmpty())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Deleting Messages", "You did not specify any messages to delete! - " + this.getUsage(), NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (Integer.parseInt(ctx.getArgs().get(0)) > 100 || Integer.parseInt(ctx.getArgs().get(0)) < 1)
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Deleting Messages", "Only 1-100 messages can be deleted at a time!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m ->
                    m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        int values = Integer.parseInt(ctx.getArgs().get(0));
        ctx.getMessage().delete().queue();
        List<Message> messages = ctx.getChannel().getHistory().retrievePast(values).complete();
        ctx.getChannel().purgeMessages(messages);
        ctx.getChannel().sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Messages Successfully Deleted", "✅ Successfully deleted " + ctx.getArgs().get(0) + " messages!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(3, TimeUnit.SECONDS));

        StringBuilder sb = new StringBuilder();
        for (Message msg : messages)
        {
            if (NoxLib.getInstance().getMessageCache().get(msg.getIdLong()) != null)
            {
                sb.append("`" + msg.getContentRaw() + "` (sent by: " + NoxLib.getInstance().getMessageCache().get(msg.getIdLong()).getAuthor().getAsMention() + ")\n");
            }
        }

        if (sb.length() < 1)
        {
            Objects.requireNonNull(NoxLib.getInstance().getBotJda().getTextChannelById(NoxLib.getInstance().getLogChannelId()))
                    .sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Messages Bulk Deleted", "**Messages:**\n" + sb, NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
        }
    }
}
