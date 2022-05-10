package me.noxiuam.noxlib.util.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

@Getter
@AllArgsConstructor
public class DeletedMessage {
    private final User author;
    private Message message;
    private String attachmentLinks;

    public DeletedMessage(Message message, User author) {
        this.message = message;
        this.author = author;
    }

    public DeletedMessage(User author, String attachmentLinks) {
        this.author = author;
        this.attachmentLinks = attachmentLinks;
    }
}
