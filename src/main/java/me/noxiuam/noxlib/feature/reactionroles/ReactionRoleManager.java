package me.noxiuam.noxlib.feature.reactionroles;

import lombok.*;
import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.entities.Member;

import java.util.*;

@Getter
public class ReactionRoleManager
{
    private final List<ReactionRole> reactionRoles = new ArrayList<>();

    public ReactionRoleManager()
    {
        System.out.println("[NoxLib] Created Reaction Role Manager!");
    }

    public void register(ReactionRole role)
    {
        this.reactionRoles.add(role);
    }

    public void addRoleToMember(ReactionRole reactionRole, Member member)
    {
        NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId()).addRoleToMember(member, reactionRole.getRole()).queue();
    }

    public void removeRoleFromMember(ReactionRole reactionRole, Member member)
    {
        NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId()).removeRoleFromMember(member, reactionRole.getRole()).queue();
    }

}
