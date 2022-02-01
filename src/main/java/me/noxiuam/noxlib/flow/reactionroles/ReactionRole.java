package me.noxiuam.noxlib.flow.reactionroles;

import lombok.*;
import net.dv8tion.jda.api.entities.Role;

@Getter @AllArgsConstructor
public class ReactionRole
{
    private String unicode;
    private Role role;
}
