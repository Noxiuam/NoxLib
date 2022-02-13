package me.noxiuam.noxlib.util.custom;

import java.util.concurrent.*;

public class ThreadUtil
{

    public void createThread(Runnable clazz, long period)
    {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(clazz, 0L, period, TimeUnit.SECONDS);
    }

    public void createThread(Runnable clazz, long period, TimeUnit timeUnit)
    {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(clazz, 0L, period, timeUnit);
    }

}
