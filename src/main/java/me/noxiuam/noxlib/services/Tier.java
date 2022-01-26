package me.noxiuam.noxlib.services;

import lombok.Getter;

@Getter
public enum Tier
{
    BRONZE("bronze", 15),
    PLATINUM("platinum", 25),
    SILVER("silver", 35),
    GOLD("gold", 50);

    private final String name;
    private final int price;
    Tier(String tierName, int price)
    {
        this.name = tierName;
        this.price = price;
    }
}