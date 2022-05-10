package me.noxiuam.noxlib.command.util.listener;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.impl.utility.Announcement;
import me.noxiuam.noxlib.command.util.data.GenericAnnouncement;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AnnouncementListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        int index = Integer.MAX_VALUE;
        if (Announcement.INSTANCE.getRunningAnnouncements().containsKey(Objects.requireNonNull(event.getMember()).getId()))
            index = Announcement.INSTANCE.getRunningAnnouncements().get(event.getMember().getId()).getIndex();

        if (Announcement.INSTANCE.getRunningAnnouncements().containsKey(Objects.requireNonNull(event.getMember()).getId())) {
            if (event.getMessage().getContentRaw().equalsIgnoreCase("$announcement start")) return;

            if (index == 0) {
                Announcement.INSTANCE.getRunningAnnouncements().put(event.getMember().getId(), new GenericAnnouncement(event.getChannel().getId(), 1, event.getMessage().getContentRaw(), ""));

                EmbedBuilder descriptionEmbed = NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("And for the final part of it.",
                        "Give the announcement a nice description, the announcement will send once you've sent it!", NoxLib.getInstance().getImageDatabase().getToolImage());
                event.getMessage().replyEmbeds(descriptionEmbed.build()).queue();
            } else if (index == 1) {
                String title = Announcement.INSTANCE.getRunningAnnouncements().get(event.getMember().getId()).getTitle();

                Announcement.INSTANCE.getRunningAnnouncements().put(event.getMember().getId(), new GenericAnnouncement(event.getChannel().getId(), 2, title, event.getMessage().getContentRaw()));

                String finalTitle = Announcement.INSTANCE.getRunningAnnouncements().get(event.getMember().getId()).getTitle();
                String finalDescription = Announcement.INSTANCE.getRunningAnnouncements().get(event.getMember().getId()).getDescription();

                event.getChannel().sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbed(finalTitle, finalDescription).build()).queue();
                Announcement.INSTANCE.getRunningAnnouncements().remove(event.getMember().getId());
            }
        }
    }
}
