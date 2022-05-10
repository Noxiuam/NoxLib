package me.noxiuam.noxlib.event;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.feature.data.AutoReaction;
import me.noxiuam.noxlib.services.Tier;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AutoReactionListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage() || NoxLib.getInstance().getConfiguration() == null) {
            return;
        }

        if (NoxLib.getInstance().getConfiguration().getBotTier().isAboveOrEqual(Tier.getByName("silver"))) {
            for (AutoReaction ar : NoxLib.getInstance().getAutoReactionHandler().getAutoReactions()) {
                if (event.getMessage().getContentRaw().contains(ar.getTrigger())) {
                    for (String str : NoxLib.getInstance().getAutoReactionHandler().getAutoReaction(event.getMessage().getContentRaw()).getEmoteIds()) {
                        event.getMessage().addReaction(Objects.requireNonNull(NoxLib.getInstance().getBotJda().getEmoteById(str))).queue();
                    }
                }
            }
        }
    }
}
