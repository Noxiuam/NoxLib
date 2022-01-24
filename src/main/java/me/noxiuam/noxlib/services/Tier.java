package me.noxiuam.noxlib.services;

public enum Tier {
    BRONZE("bronze", 15),
    PLATINUM("platinum", 25),
    SILVER("silver", 35),
    GOLD("gold", 50),
    DIAMOND("diamond", 50),
    LEGENDARY("legendary", 100);

    Tier(String tierName, int price) {
    }
}