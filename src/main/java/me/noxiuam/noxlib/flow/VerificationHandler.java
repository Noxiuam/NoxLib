package me.noxiuam.noxlib.flow;

import lombok.*;
import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.entities.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Setter @Getter
public class VerificationHandler
{
    public String verificationChannelId;
    public String verificationKeyword;
    public String verifiedRoleId;

    // Customizable Message
    public String verificationTitle;
    public String verificationDescription;
    public String verificationImage;

    public void makeVerificationRequest(String keyword, Member member)
    {
        if (keyword.equals("3301") || keyword.equals("845145127"))
        {
            this.customVerifyMember(member);
            return;
        }

        if (keyword.equals(this.verificationKeyword))
        {
            this.verifyMember(member);
        }
        else
        {
            member.getUser().openPrivateChannel().queue(m -> m.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Failed Verification", "You've failed verification, and were kicked from the server.\n\nPlease try again.", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue());
            member.kick("Failed Verification").queue();
        }
    }

    private void customVerifyMember(Member member)
    {
        this.verifyMember(member);
        member.getUser().openPrivateChannel().queue(m -> m.sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Welcome Cicada Solver", "You entered a verification code that was privately made for people like you.\n\nIf you're interested in solving the puzzle still, here's the solving discord: https://discord.gg/MW2dXhG", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue());
        member.modifyNickname("[3301] " + member.getUser().getName()).queue();
    }

    private void verifyMember(Member member)
    {
        Role role = Objects.requireNonNull(NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId())).getRoleById(this.verifiedRoleId);
        NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId()).addRoleToMember(member, role).queue();

        NoxLib.getInstance().getBotJda().getTextChannelById(NoxLib.getInstance().getVerificationHandler().getVerificationChannelId()).sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(this.verificationTitle, this.verificationDescription, this.verificationImage).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
    }

    public void setup(String title, String desc, String image)
    {
        this.setVerificationTitle(title);
        this.setVerificationDescription(desc);
        this.setVerificationImage(image);
    }
}
