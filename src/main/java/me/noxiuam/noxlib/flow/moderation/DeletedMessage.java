package me.noxiuam.noxlib.flow.moderation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class DeletedMessage
{
    private String message;
    private String author;
}
