package me.noxiuam.noxlib.util.function;

import lombok.SneakyThrows;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.util.listener.AnnouncementListener;

import java.util.TimerTask;

public class ConfigThread extends TimerTask {

    private int count = 0;

    @Override
    @SneakyThrows
    public void run() {
        Thread.sleep(5000L);
        if (NoxLib.getInstance().getConfiguration() == null) {
            System.err.println("[NoxLib] Your config is not set! No features will work until you have this.");
            return;
        }

        if (count == 0) {
            NoxLib.getInstance().getBotJda().addEventListener(new AnnouncementListener());
            count++;
        }
    }
}
