package me.noxiuam.noxlib.command.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;
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
        if (ctx.getArgs().isEmpty())
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Deleting Messages", "You did not specify any messages to delete! - " + this.getUsage(), NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        if (Integer.parseInt(ctx.getArgs().get(1)) > 100 || Integer.parseInt(ctx.getArgs().get(1)) < 1)
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Deleting Messages", "Only 1-100 messages can be deleted at a time!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m ->
                    m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        int values = Integer.parseInt(ctx.getArgs().get(1));
        ctx.getMessage().delete().queue();
        List<Message> messages = ctx.getChannel().getHistory().retrievePast(values).complete();
        ctx.getChannel().deleteMessages(messages).queue();
        ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Messages Successfully Deleted", "✅ Successfully deleted " + ctx.getArgs().get(1) + " messages!", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue(
                m -> m.delete().queueAfter(5, TimeUnit.SECONDS)
        );
    }
}
