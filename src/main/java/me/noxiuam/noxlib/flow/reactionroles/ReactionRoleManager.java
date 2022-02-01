package me.noxiuam.noxlib.flow.reactionroles;

import lombok.*;

import java.util.*;

@Getter
public class ReactionRoleManager
{
    private final List<ReactionRole> reactionRoles = new ArrayList<>();

    public void register(ReactionRole role)
    {
        this.reactionRoles.add(role);
    }
}
