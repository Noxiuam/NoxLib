package me.noxiuam.noxlib.command.impl.moderation;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;
import me.noxiuam.noxlib.util.data.user.DeletedMessage;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MessageSearchUp extends GenericCommand {
    public MessageSearchUp() {
        super("reverseSearch", "Searches the bot message cache.", NoxLib.getInstance().prefix + "reverseSearch <user> <query>", Tier.SILVER);
    }

    @Override
    public void execute(CommandContext ctx) {
        MessageEmbed error = NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to search", "You may only search from one user at a time.", NoxLib.getInstance().getImageDatabase().getErrorImage()).build();
        MessageEmbed noStringToSearch = NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Unable to search", "You did not provide anything to search.", NoxLib.getInstance().getImageDatabase().getErrorImage()).build();
        MessageEmbed noResults = NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Search results", "Could not find anything matching that search...", NoxLib.getInstance().getImageDatabase().getToolImage()).build();

        if (ctx.getMessage().getMentionedMembers().size() > 1) {
            ctx.getMessage().replyEmbeds(error).queue(message -> {
                ctx.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
                message.delete().queueAfter(5, TimeUnit.SECONDS);
            });
            return;
        }

        String memberId = ctx.getMessage().getMentionedMembers().get(0).getId();
        StringBuilder query = new StringBuilder();

        for (int i = 1; i < ctx.getArgs().size(); i++) {
            query.append(ctx.getArgs().get(i));
        }

        if (query.length() == 0) {
            ctx.getMessage().replyEmbeds(noStringToSearch).queue(message -> {
                ctx.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
                message.delete().queueAfter(5, TimeUnit.SECONDS);
            });
            return;
        }

        StringBuilder results = new StringBuilder();
        int resultsFound = 0;
        for (DeletedMessage dm : NoxLib.getInstance().getMessageCache().values()) {
            String match = dm.getMessage().getContentRaw();

            if (dm.getAuthor().getId().equalsIgnoreCase(memberId) && match.contains(query)) {
                results.append(dm.getAuthor().getAsMention())
                        .append(" said `")
                        .append(match)
                        .append("` on **")
                        .append(dm.getTimeStamp())
                        .append("**\n");
                resultsFound++;
            }
        }

        if (resultsFound > 0) {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Results Found!", results.toString(), NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
        } else {
            ctx.getMessage().replyEmbeds(noResults).queue(message -> {
                ctx.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
                message.delete().queueAfter(5, TimeUnit.SECONDS);
            });
        }
    }
}
