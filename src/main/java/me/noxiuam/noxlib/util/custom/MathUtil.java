package me.noxiuam.noxlib.util.custom;

import java.util.Random;

public class MathUtil
{
    public String getRandomPercent(int max)
    {
        return new Random().nextInt(max) + "%";
    }

    public int getRandomNumberBetween(int min, int max)
    {
        int value = new Random().nextInt(max);

        if (value > min + 3)
        {
            value = this.getRandomNumberBetween(min, max);
        }

        return value;
    }

}
