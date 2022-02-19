package me.noxiuam.noxlib.services;

import lombok.Getter;

@Getter
public enum Tier
{
    GOLD("gold", 50),
    SILVER("silver", 35),
    PLATINUM("platinum", 25),
    BRONZE("bronze", 15);

    private final String name;
    private final int price;
    Tier(String tierName, int price)
    {
        this.name = tierName;
        this.price = price;
    }

    public static Tier getByName(String name)
    {
        for (Tier value : Tier.values())
        {
            if (!value.getName().equalsIgnoreCase(name)) continue;
            return value;
        }
        return BRONZE;
    }

    public boolean isAboveOrEqual(Tier rank)
    {
        return rank.ordinal() >= this.ordinal();
    }
}