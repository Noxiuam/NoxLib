package me.noxiuam.noxlib.event;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.feature.message.AutoReponseMessage;
import me.noxiuam.noxlib.services.Tier;
import me.noxiuam.noxlib.util.data.user.DeletedMessage;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/*
 * This class will contain all features every bot will contain.
 * This will only include simple functionality.
 */
public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.isWebhookMessage() || event.getMember().getUser().isBot()) return;

        if (NoxLib.getInstance().getConfiguration().getBotTier().isAboveOrEqual(Tier.getByName("silver"))) {
            for (AutoReponseMessage msg : NoxLib.getInstance().getAutoResponseHandler().getAutoResponses()) {
                if (msg.getTrigger().equalsIgnoreCase(event.getMessage().getContentRaw())) {
                    event.getChannel().sendMessage(NoxLib.getInstance().getAutoResponseHandler().getAutoResponse(event.getMessage().getContentRaw()).getResponse()).queue();
                }
            }
        }

        if (!event.getMessage().getAttachments().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Message.Attachment image : event.getMessage().getAttachments()) {
                sb.append(image.getUrl()).append("\n");
            }

            if (event.getMessage().getContentRaw().length() != 0) {
                NoxLib.getInstance().getMessageCache().put(event.getMessageIdLong(), new DeletedMessage(event.getAuthor(), event.getMessage(), sb.toString()));
            } else {
                NoxLib.getInstance().getMessageCache().put(event.getMessageIdLong(), new DeletedMessage(event.getAuthor(), sb.toString()));
            }
        } else {
            NoxLib.getInstance().getMessageCache().put(event.getMessageIdLong(), new DeletedMessage(event.getMessage(), event.getAuthor()));
        }

        NoxLib.getInstance().getCommandManager().handle(event);
    }
}
