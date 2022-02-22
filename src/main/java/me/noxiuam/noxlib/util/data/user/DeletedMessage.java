package me.noxiuam.noxlib.util.data.user;

import lombok.*;
import net.dv8tion.jda.api.entities.*;

@Getter @AllArgsConstructor
public class DeletedMessage
{
    private Message message;
    private final User author;
    private String attachmentLinks;

    public DeletedMessage(Message message, User author)
    {
        this.message = message;
        this.author = author;
    }

    public DeletedMessage(User author, String attachmentLinks)
    {
        this.author = author;
        this.attachmentLinks = attachmentLinks;
    }
}
