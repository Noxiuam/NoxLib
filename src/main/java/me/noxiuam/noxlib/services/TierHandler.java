package me.noxiuam.noxlib.services;

public class TierHandler
{

    public TierHandler()
    {
        System.out.println("[NoxLib Services] Created Tier Handler!");
    }

    public boolean hasBasicLogging(Tier tier)
    {
        return tier.getName().equalsIgnoreCase("platinum") || this.hasAdvancedLogging(tier);
    }

    public boolean hasAdvancedLogging(Tier tier)
    {
        return tier.getName().equalsIgnoreCase("silver") || tier.getName().equalsIgnoreCase("gold") || tier.getName().equalsIgnoreCase("diamond") || tier.getName().equalsIgnoreCase("legendary");
    }

    public boolean hasAccessToMusic(Tier tier)
    {
        return !tier.getName().equalsIgnoreCase("bronze");
    }

    public boolean isTopTier(Tier tier)
    {
        return tier.getName().equalsIgnoreCase("gold");
    }
}
