package me.noxiuam.noxlib.util;

import java.util.Random;

public class MathUtil
{
    public String getRandomPercent(int max)
    {
        return new Random().nextInt(max) + "%";
    }
}
