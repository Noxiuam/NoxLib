package me.noxiuam.noxlib.config;

import lombok.*;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.services.Tier;

/*
 * All of these values should be set in the bot.
 */
@Getter @Setter
public class Config
{
    // Absolutely required to be set, this is not automatically set yet!
    public Tier botTier;

    // Ticket
    public String defaultTicketTitle;
    public String defaultTicketMessage;
    public String defaultTicketImage;
    public String defaultTicketEmoteId;

    public void setupTicketValues(String defaultTicketTitle, String defaultTicketMessage, String defaultTicketImage, String defaultTicketEmoteId)
    {
        this.defaultTicketTitle = defaultTicketTitle;
        this.defaultTicketMessage = defaultTicketMessage;
        this.defaultTicketImage = defaultTicketImage;
        this.defaultTicketEmoteId = defaultTicketEmoteId;
    }

    // Bronze
    public Config(Tier tier, String prefix, String guildId)
    {
        if (!tier.getName().equalsIgnoreCase("bronze")) return;

        this.setBotTier(tier);

        NoxLib.getInstance().setPrefix(prefix);
        NoxLib.getInstance().setGuildId(guildId);

        System.out.println("[NoxLib] Tier has been set to Bronze!");
    }

    // Platinum
    public Config(Tier tier, String prefix, String guildId, String logChannelId)
    {
        if (!tier.getName().equalsIgnoreCase("platinum")) return;

        this.setBotTier(tier);

        NoxLib.getInstance().setPrefix(prefix);
        NoxLib.getInstance().setGuildId(guildId);
        NoxLib.getInstance().setLogChannelId(logChannelId);

        System.out.println("[NoxLib] Tier has been set to Platinum, this includes basic logging!");
    }

    // Silver
    public Config(Tier tier, String prefix, String logChannelId, String ticketCategoryId, String ticketReactChannelId)
    {
        if (!tier.getName().equalsIgnoreCase("silver")) return;

        this.setBotTier(tier);

        NoxLib.getInstance().setPrefix(prefix);
        NoxLib.getInstance().setLogChannelId(logChannelId);
        NoxLib.getInstance().setTicketCategoryId(ticketCategoryId);
        NoxLib.getInstance().setTicketReactChannelId(ticketReactChannelId);

        System.out.println("[NoxLib] Tier has been set to Silver, this includes advanced logging & tickets!");
    }

    // Gold
    public Config(Tier tier, String prefix, String logChannelId, String guildId, String ticketCategoryId, String ticketReactChannelId, String verificationKeyword, String verifiedRoleId, String verificationChannelId)
    {
        if (!tier.isAboveOrEqual(Tier.getByName("gold"))) return;

        this.setBotTier(tier);

        NoxLib.getInstance().setPrefix(prefix);
        NoxLib.getInstance().setLogChannelId(logChannelId);
        NoxLib.getInstance().setGuildId(guildId);
        NoxLib.getInstance().setTicketCategoryId(ticketCategoryId);
        NoxLib.getInstance().setTicketReactChannelId(ticketReactChannelId);
        NoxLib.getInstance().getVerificationHandler().setVerificationKeyword(verificationKeyword);
        NoxLib.getInstance().getVerificationHandler().setVerifiedRoleId(verifiedRoleId);
        NoxLib.getInstance().getVerificationHandler().setVerificationChannelId(verificationChannelId);

        System.out.println("[NoxLib] Tier has been set to " + tier.getName().substring(0, 1).toUpperCase() + tier.getName().substring(1) + ", this is a high tier!");
    }

    // Gold with Reports
    public Config(Tier tier, String prefix, String logChannelId, String guildId, String ticketCategoryId, String ticketReactChannelId, String verificationKeyword, String verifiedRoleId, String verificationChannelId, String reportsChannelId)
    {
        if (!tier.isAboveOrEqual(Tier.getByName("gold"))) return;

        this.setBotTier(tier);

        NoxLib.getInstance().setPrefix(prefix);
        NoxLib.getInstance().setLogChannelId(logChannelId);
        NoxLib.getInstance().setGuildId(guildId);
        NoxLib.getInstance().setTicketCategoryId(ticketCategoryId);
        NoxLib.getInstance().setTicketReactChannelId(ticketReactChannelId);
        NoxLib.getInstance().getVerificationHandler().setVerificationKeyword(verificationKeyword);
        NoxLib.getInstance().getVerificationHandler().setVerifiedRoleId(verifiedRoleId);
        NoxLib.getInstance().getVerificationHandler().setVerificationChannelId(verificationChannelId);
        NoxLib.getInstance().setReportsChannelId(reportsChannelId);

        System.out.println("[NoxLib] Tier has been set to " + tier.getName().substring(0, 1).toUpperCase() + tier.getName().substring(1) + ", this is a high tier with reports!");
    }
}
