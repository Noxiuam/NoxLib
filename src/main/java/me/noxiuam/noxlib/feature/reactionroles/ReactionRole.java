package me.noxiuam.noxlib.feature.reactionroles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Role;

@Getter
@AllArgsConstructor
public class ReactionRole {
    private String unicode;
    private Role role;
}
