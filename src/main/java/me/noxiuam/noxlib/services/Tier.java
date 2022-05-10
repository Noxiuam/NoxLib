package me.noxiuam.noxlib.services;

import lombok.Getter;

@Getter
public enum Tier {
    GOLD("gold"),
    SILVER("silver"),
    PLATINUM("platinum"),
    BRONZE("bronze");

    private final String name;

    Tier(String tierName) {
        this.name = tierName;
    }

    public static Tier getByName(String name) {
        for (Tier value : Tier.values()) {
            if (!value.getName().equalsIgnoreCase(name)) continue;
            return value;
        }
        return BRONZE;
    }

    public boolean isAboveOrEqual(Tier tier) {
        return tier.ordinal() >= this.ordinal();
    }
}