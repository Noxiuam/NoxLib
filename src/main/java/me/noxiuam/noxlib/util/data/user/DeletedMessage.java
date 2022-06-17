package me.noxiuam.noxlib.util.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

@Getter
@AllArgsConstructor
public class DeletedMessage {
    private final User author;
    private Message message;
    private String attachmentLinks;
    private final String timeStamp = NoxLib.getInstance().getTimeUtil().getCurrentGenericTime();

    public DeletedMessage(Message message, User author) {
        this.message = message;
        this.author = author;
    }

    public DeletedMessage(User author, String attachmentLinks) {
        this.author = author;
        this.attachmentLinks = attachmentLinks;
    }
}
