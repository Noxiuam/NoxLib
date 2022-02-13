package me.noxiuam.noxlib.feature;

import lombok.*;
import me.noxiuam.noxlib.NoxLib;
import net.dv8tion.jda.api.entities.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Setter @Getter
public class VerificationHandler
{
    public boolean reactionVerificationEnabled;
    public String verificationEmoteId;

    public String verificationChannelId;
    public String verificationKeyword;
    public String verifiedRoleId;

    // Customizable Message
    public String verificationTitle;
    public String verificationDescription;
    public String verificationImage;

    public void makeVerificationRequest(String keyword, Member member)
    {
        if (keyword.equalsIgnoreCase(this.verificationKeyword))
        {
            this.verifyMember(member);
            return;
        }

        member.getUser().openPrivateChannel().queue(m -> m.sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Failed Verification", "You've failed verification, and were kicked from the server.\n\nPlease try again.", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue());
        member.kick("Failed Verification").queue();
    }

    private void verifyMember(Member member)
    {
        Role role = Objects.requireNonNull(NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId())).getRoleById(this.verifiedRoleId);
        NoxLib.getInstance().getBotJda().getGuildById(NoxLib.getInstance().getGuildId()).addRoleToMember(member, role).queue();

        if (!this.isReactionVerificationEnabled())
        {
            NoxLib.getInstance().getBotJda().getTextChannelById(NoxLib.getInstance().getVerificationHandler().getVerificationChannelId()).sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail(this.verificationTitle, this.verificationDescription, this.verificationImage).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
        }
    }

    public void makeVerificationRequest(Member member)
    {
        this.verifyMember(member);
    }

    // Emote Verification Only
    public void setup(boolean reactionVerificationEnabled, String verificationEmoteId)
    {
        this.setReactionVerificationEnabled(reactionVerificationEnabled);
        this.setVerificationEmoteId(verificationEmoteId);
    }

    // Word Verification Only
    public void setup(boolean reactionVerificationEnabled, String title, String desc, String image)
    {
        this.setReactionVerificationEnabled(reactionVerificationEnabled);
        this.setVerificationTitle(title);
        this.setVerificationDescription(desc);
        this.setVerificationImage(image);
    }
}
