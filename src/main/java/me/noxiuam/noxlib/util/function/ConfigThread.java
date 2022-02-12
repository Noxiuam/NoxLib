package me.noxiuam.noxlib.util.function;

import lombok.SneakyThrows;
import me.noxiuam.noxlib.NoxLib;

import java.util.TimerTask;

public class ConfigThread extends TimerTask {

    @Override @SneakyThrows
    public void run() {
        Thread.sleep(5000L);
        if (NoxLib.getInstance().getConfiguration() == null)
        {
            System.err.println("[NoxLib] Your config is not set! No features will work until you have this.");
        }
    }
}
