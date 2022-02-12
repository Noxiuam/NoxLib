package me.noxiuam.noxlib.util.data.user;

import lombok.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

@Getter @AllArgsConstructor
public class DeletedMessage
{
    private Message message;
    private User author;
}
