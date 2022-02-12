package me.noxiuam.noxlib.event;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class VerificationListener extends ListenerAdapter
{

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if (event.getMember().getUser().isBot() || !NoxLib.getInstance().getVerificationHandler().isReactionVerificationEnabled()) return;

        if (event.getChannel().getId().equalsIgnoreCase(NoxLib.getInstance().getVerificationHandler().getVerificationChannelId()) && event.getReactionEmote().getEmote().getId().equalsIgnoreCase(NoxLib.getInstance().getVerificationHandler().verificationEmoteId))
        {
            event.getReaction().removeReaction(Objects.requireNonNull(event.getUser())).queue();
            NoxLib.getInstance().getVerificationHandler().makeVerificationRequest(event.getMember());
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage() && NoxLib.getInstance().getVerificationHandler().isReactionVerificationEnabled()) return;

        if (event.getChannel().getId().equalsIgnoreCase(NoxLib.getInstance().getVerificationHandler().getVerificationChannelId()))
        {
            event.getMessage().delete().queue();
            if (event.getMessage().getContentRaw().equalsIgnoreCase(NoxLib.getInstance().getPrefix() + "setupVerification") && Objects.requireNonNull(event.getMember()).hasPermission(Permission.MANAGE_SERVER))
            {
                if (NoxLib.getInstance().getVerificationHandler().isReactionVerificationEnabled())
                {
                    event.getChannel().sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Verification", "To enter the server, you're required to verify yourself.\n\nTo verify yourself, react to the message below.", NoxLib.getInstance().getImageDatabase().getToolImage()).build()).queue(m -> {
                        m.addReaction(Objects.requireNonNull(NoxLib.getInstance().getBotJda().getEmoteById(NoxLib.getInstance().getVerificationHandler().getVerificationEmoteId()))).queue();
                    });
                }
                else
                {
                    event.getChannel().sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Verification", "To enter the server, you're required to verify yourself.\n\nPlease read the rules, when you're done, type `" + NoxLib.getInstance().getVerificationHandler().getVerificationKeyword() + "`.", NoxLib.getInstance().getImageDatabase().getToolImage()).build()).queue(m -> {
                        if (NoxLib.getInstance().getVerificationHandler().isReactionVerificationEnabled())
                        {
                            m.addReaction(Objects.requireNonNull(NoxLib.getInstance().getBotJda().getEmoteById(NoxLib.getInstance().getVerificationHandler().getVerificationEmoteId()))).queue();
                        }
                    });
                }
                return;
            }

            /*if (event.getMessage().getContentRaw().equalsIgnoreCase(NoxLib.getInstance().getPrefix() + "setupCustomVerification") && event.getAuthor().getId().equals("915231800056479745"))
            {
                event.getChannel().sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Verification", "To enter the server, you're required to verify yourself.\n\nPlease read the rules, when you're done, listen to the audio file, and type the given number for the product.\n\nIf you know the final product being referenced, you will be given a special nickname when verified.", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
                event.getChannel().sendFile(new File("C:\\Users\\bsloa\\OneDrive\\Documents\\Noxiuam\\Bots\\3301 Bot\\3301.mp3"), "verify.mp3").queue();
                return;
            }*/

            NoxLib.getInstance().getVerificationHandler().makeVerificationRequest(event.getMessage().getContentRaw(), event.getMember());
        }
    }
}
