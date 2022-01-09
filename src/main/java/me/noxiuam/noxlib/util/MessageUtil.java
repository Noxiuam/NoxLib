package me.noxiuam.noxlib.util;

import net.dv8tion.jda.api.EmbedBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     * Creates an embed message with a thumbnail, an author, and an image.
     */
    public EmbedBuilder createEmbedWithAuthorThumbnailAndImage(String title, String description, String author, String thumbnail, String image)
    {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle(title).setDescription(description).setAuthor(author).setThumbnail(thumbnail).setImage(image);
        return b;
    }

}
