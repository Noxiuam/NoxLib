package me.noxiuam.noxlib.util.custom;

import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ThreadChannel;
import net.dv8tion.jda.api.requests.restaction.ThreadChannelAction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MessageUtil
{

    /*
     * Creates an embed message with all features.
     */
    public EmbedBuilder createFullEmbed(String title, String description, String author, String thumbnail, String image)
    {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle(title)
                .setDescription(description)
                .setThumbnail(thumbnail)
                .setAuthor(author)
                .setFooter("Posted on " + DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()))
                .setImage(image);
        return b;
    }

    /*
     * Creates an embed message with a thumbnail.
     */
    public EmbedBuilder createEmbed(String title, String description)
    {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle(title).setDescription(description);
        return b;
    }

    /*
     * Creates an embed message with a thumbnail.
     */
    public EmbedBuilder createEmbedWithThumbnail(String title, String description, String thumbnail)
    {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle(title).setDescription(description).setThumbnail(thumbnail);
        return b;
    }

    /*
     * Creates an embed message with an author.
     */
    public EmbedBuilder createEmbedWithAuthor(String title, String description, String author)
    {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle(title).setDescription(description).setAuthor(author);
        return b;
    }

    /*
     * Creates an embed message with a thumbnail, and an author.
     */
    public EmbedBuilder createEmbedWithAuthorAndThumbnail(String title, String description, String author)
    {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle(title).setDescription(description).setAuthor(author);
        return b;
    }

    /*
     * Creates an embed message with a thumbnail, an author, and an me.noxiuam.noxlib.image.
     */
    public EmbedBuilder createEmbedWithAuthorThumbnailAndImage(String title, String description, String author, String thumbnail, String image)
    {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle(title).setDescription(description).setAuthor(author).setThumbnail(thumbnail).setImage(image);
        return b;
    }


    /*
     * Creates a thread with a name and title.
     */
    public void createThread(String title, String messageId, String channelId)
    {
        Objects.requireNonNull(NoxLib.getInstance().getBotJda().getTextChannelById(channelId)).createThreadChannel(title, messageId).setAutoArchiveDuration(ThreadChannel.AutoArchiveDuration.TIME_3_DAYS).queue();
    }

    /*
     * Creates a thread with a name, and sends a message in it.
     */
    public void createThreadAndSendMessage(String title, String messageId, String channelId, String message)
    {
        Objects.requireNonNull(NoxLib.getInstance().getBotJda().getTextChannelById(channelId)).createThreadChannel(title, messageId).setAutoArchiveDuration(ThreadChannel.AutoArchiveDuration.TIME_3_DAYS).queue(m -> {
            m.sendMessage(message).queue();
        });
    }

    /*
     * Creates a thread with a name, and sends a message in it.
     */
    public ThreadChannelAction createThreadAndGet(String title, String messageId, String channelId)
    {
        return Objects.requireNonNull(NoxLib.getInstance().getBotJda().getTextChannelById(channelId)).createThreadChannel(title, messageId).setAutoArchiveDuration(ThreadChannel.AutoArchiveDuration.TIME_3_DAYS);
    }

}
